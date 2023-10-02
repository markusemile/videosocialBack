package com.videosTek.backend.entity.dto;

import com.videosTek.backend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private UUID id;
    private String username;
    private String email;
    private String group;

    public UserDto fromUser(User user){
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .group(user.getGroup().getId().toString())
                .build();
    }

}
