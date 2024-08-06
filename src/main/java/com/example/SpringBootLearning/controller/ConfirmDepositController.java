package com.example.SpringBootLearning.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringBootLearning.dto.respone.ApiResponse;
import com.example.SpringBootLearning.service.ConfirmDepositService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ConfirmDepositController {
    ConfirmDepositService confirmDepositService;

    @PostMapping("/confirmdeposit/{idbooking}")
    public ApiResponse confirmdeposit(@PathVariable("idbooking") Integer idbooking) {
        return confirmDepositService.confirmDeposit(idbooking);
    }
}
