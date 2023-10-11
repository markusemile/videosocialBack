package com.videosTek.backend.auth;

import com.videosTek.backend.entity.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping(path="api/auth",consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)

@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("register")
    public ResponseEntity<ApiResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok().body(service.register(request));
    }

    @PostMapping("login")
    @ResponseBody
    public ResponseEntity<?> login(
            @RequestBody LoginRequest request    ) {
        ApiResponse res = ApiResponse.builder().build();
        res.setTime(LocalDateTime.now());
        res.setData(service.login(request));
       System.out.println(request.getEmail());
        return ResponseEntity.ok().body(res);
    }

    @PostMapping("refreshtoken")
    @ResponseBody
    public ResponseEntity<?> refreshToken(
            @RequestBody RefreshTokenRequest request) {
        System.out.println("REFRESSSShhhh");
        return service.refreshToken(request);
    }




}
