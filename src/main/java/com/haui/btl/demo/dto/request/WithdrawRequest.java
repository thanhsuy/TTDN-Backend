package com.haui.btl.demo.dto.request;

import lombok.Data;

@Data
public class WithdrawRequest {
    private Integer userId;
    private Float amount;
}