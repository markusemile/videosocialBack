package com.videosTek.backend.entity.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PaginateResponse {
    private int page;
    private Object results;
    private int total_pages;
    private int total_results;
}
