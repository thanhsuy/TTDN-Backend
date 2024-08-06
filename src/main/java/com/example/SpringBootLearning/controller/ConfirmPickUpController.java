package com.example.SpringBootLearning.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringBootLearning.dto.respone.ApiResponse;
import com.example.SpringBootLearning.service.ConfirmPickUpService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/confirmpickup")
public class ConfirmPickUpController {
    ConfirmPickUpService confirmPickUpService;

    @PostMapping("/{idbooking}")
    public ApiResponse confirmPickUpService(@PathVariable("idbooking") Integer idbooking) {
        return confirmPickUpService.confirmPickUpService(idbooking);
    }
}
