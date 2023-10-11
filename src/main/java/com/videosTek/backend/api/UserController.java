package com.videosTek.backend.api;

import com.videosTek.backend.entity.User;
import com.videosTek.backend.entity.dto.UserProfileInfo;
import com.videosTek.backend.entity.response.ApiResponse;
import com.videosTek.backend.entity.response.EnumStatus;
import com.videosTek.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping(path="api/user",consumes = MediaType.ALL_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class UserController {

    private final UserRepository userRepository;

    @PostMapping(path = "update")
    @ResponseBody
    public ResponseEntity<ApiResponse> updateUserProfile(
            @RequestBody UserProfileInfo userRequest
    ){


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

            log.warn(userRequest.getIncludeAdult().toString());

            this.userRepository.save(u);
            apiResponse.setStatus(EnumStatus.SUCCESS);
            apiResponse.setMessage("Profile updated successfully !");
        }else{
            apiResponse.setStatus(EnumStatus.ERROR);
            apiResponse.setMessage("user not found !");
        }

        return ResponseEntity.ok().body(apiResponse);
    }

}
