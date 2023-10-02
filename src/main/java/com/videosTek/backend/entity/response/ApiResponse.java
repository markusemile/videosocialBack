package com.videosTek.backend.entity.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class ApiResponse {

    private LocalDateTime time;
    private EnumStatus status;
    private String message;
    private Object data;


}
