package com.videosTek.backend.entity.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data

public class UserProfileInfo {

    private String username;
    private String email;
    private Boolean includeAdult;
    private Boolean blurAdultContent;
    private String language;



}
