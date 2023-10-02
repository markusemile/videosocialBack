package com.videosTek.backend.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private UUID id;
    private String username;
    private String email;
    private String language;
    private String token;
    private String refreshToken;
    private Boolean blurAdultContent;
    private Boolean includeAdult;

}
