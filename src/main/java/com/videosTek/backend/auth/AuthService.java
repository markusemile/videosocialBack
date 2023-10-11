package com.videosTek.backend.auth;

import com.videosTek.backend.entity.Group;
import com.videosTek.backend.entity.Mediatek;
import com.videosTek.backend.entity.User;
import com.videosTek.backend.entity.response.ApiResponse;
import com.videosTek.backend.entity.response.EnumStatus;
import com.videosTek.backend.repository.MediatekRepository;
import com.videosTek.backend.repository.UserRepository;
import com.videosTek.backend.security.JwtService;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final MediatekRepository mediatekRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public ApiResponse register(RegisterRequest request) {

        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .group(Group.builder().id(1L).build()) //USER by default
                .build();

        var jwtToken = jwtService.generateToken(user,1800000);
        var refreshToken = jwtService.generateToken(user,86400000);

        user.setRefreshToken(refreshToken);
        // save new user
        User newUser = userRepository.save(user);
        // creation of the mediatek
        newUser.setMediatek(newUser.getId());

        Mediatek mediatek = Mediatek.builder()
                .id(user.getId())
                .movieIdList("")
                .build();

        // initialiation of new mediatek
        mediatekRepository.save(mediatek);

        ApiResponse res = ApiResponse.builder()
                .time(LocalDateTime.now())
                .status(EnumStatus.SUCCESS)
                .message("You're account is activate. Your can signIn !")
                .build();
        return res;
    }

    public Object login(LoginRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRepository.findByEmail(request.getEmail());
        if(user.isPresent()) {
            String refreshToken;
            var jwtToken = jwtService.generateToken(user.get(), 15*60*1000);
            // on verifie le refreshtoken de l'utilisateur
            Boolean rtExpired = jwtService.tokenExpired(user.get().getRefreshToken());
            if(rtExpired){
                refreshToken = jwtService.generateToken(user.get(), 24*60*60*1000);
                user.get().setRefreshToken(refreshToken);
            }else{
                Boolean rtValid = jwtService.isTokenValid(user.get().getRefreshToken(),user.get());
                if(rtValid) {
                    refreshToken = user.get().getRefreshToken();
                }else {
                    throw new RuntimeException("Error token validaton P86");
                }
            }
            User u = user.get();
            this.userRepository.save(u);
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword())
            );
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .username(u.getUsername())
                    .email(u.getEmail())
                    .language(u.getLanguage())
                    .id(u.getId())
                    .includeAdult(u.getIncludeAdult())
                    .blurAdultContent(u.getBlurAdultContent())
                    .refreshToken(refreshToken)
                    .build();

        }else{
            ErrorResponse errorM = ErrorResponse.builder()
                    .code(404)
                    .status("Error")
                    .message("User not found")
                    .build();
            return errorM;
        }

    }

    public ResponseEntity<?> refreshToken(RefreshTokenRequest request) {
        var username = jwtService.extractUsername(request.getRefreshToken());
        var expiration = jwtService.tokenExpired(request.getRefreshToken());
        Optional<User> u = userRepository.findByEmail(username);
        if(u.isPresent()){
            User  user = u.get();
            if(request.getRefreshToken().equals(u.get().getRefreshToken()) && !expiration){
               var jwt = jwtService.generateToken(u.get(),15*60*1000);
               var exp = jwtService.extractClaim(request.getRefreshToken(), Claims::getExpiration);
               var currDate = new Date();
               var limit = (exp.getTime()-currDate.getTime())/(60*60*1000);
               if(limit<=1){
                   var refreshToken = jwtService.generateToken(u.get(),24*60*60*1000);
                   user.setRefreshToken(refreshToken);
                   userRepository.save(user);
               }
               AuthenticationResponse res = AuthenticationResponse.builder()
                       .token(jwt)
                       .refreshToken(user.getRefreshToken())
                        .build();
               return ResponseEntity.ok().body(res);
            }else{
                ErrorResponse errorResponse = ErrorResponse.builder()
                        .status("error")
                        .message("Bad Refresh Token")
                        .code(400)
                        .build();
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
            }
        }else{
            // username not found
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .status("error")
                    .message("Username not found")
                    .code(404)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
}
