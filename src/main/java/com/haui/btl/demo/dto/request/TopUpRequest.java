package com.haui.btl.demo.dto.request;

import lombok.Data;

@Data
public class TopUpRequest {
    private Integer userId;
    private Float amount;
}