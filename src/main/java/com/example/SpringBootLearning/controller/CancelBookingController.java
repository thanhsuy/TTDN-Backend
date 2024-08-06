package com.example.SpringBootLearning.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringBootLearning.dto.respone.ApiResponse;
import com.example.SpringBootLearning.service.CancelBookingService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
public class CancelBookingController {
    CancelBookingService cancelBookingService;

    @PostMapping("/cancelbooking/{idbooking}")
    public ApiResponse cancelBooking(@PathVariable("idbooking") Integer idbooking) {

        return cancelBookingService.cancelBooking(idbooking);
    }
}
