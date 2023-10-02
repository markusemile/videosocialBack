package com.videosTek.backend.api;

import com.videosTek.backend.entity.User;
import com.videosTek.backend.entity.dto.UserProfileInfo;
import com.videosTek.backend.entity.response.ApiResponse;
import com.videosTek.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path="api/user",consumes = MediaType.ALL_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserRepository userRepository;

    @PostMapping(path = "update")
    @ResponseBody
    public ResponseEntity<ApiResponse> updateUserProfile(
            @RequestBody UserProfileInfo userRequest
    ){
        System.out.println(userRequest.getUsername());
        System.out.println(userRequest.getLanguage());

        ApiResponse apiResponse = ApiResponse.builder().time(LocalDateTime.now()).build();
        Optional<User> optUser = this.userRepository.findByEmail(userRequest.getEmail());

        if(optUser.isPresent()){
            User  u = User.builder().build();
            u.setId(optUser.get().getId());
            u.setBlurAdultContent(userRequest.getBlurAdultContent());
            u.setEmail(userRequest.getEmail());
            u.setIncludeAdult(userRequest.getIncludeAdult());
            u.setLanguage(userRequest.getLanguage());
            u.setPassword(optUser.get().getPassword());
            u.setRefreshToken((optUser.get().getRefreshToken()));
            u.setUsername(userRequest.getUsername());
            u.setGroup(optUser.get().getGroup());
            u.setMediatek((optUser.get().getMediatek()));
            this.userRepository.save(u);
        }

        return ResponseEntity.ok().body(apiResponse);
    }

}
