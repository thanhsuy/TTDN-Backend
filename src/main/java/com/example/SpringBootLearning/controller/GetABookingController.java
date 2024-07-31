package com.example.SpringBootLearning.controller;

import com.example.SpringBootLearning.dto.respone.ApiResponse;
import com.example.SpringBootLearning.service.GetABookingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/getbooking")

public class GetABookingController {
    GetABookingService getABookingService;

    @GetMapping("/{idbooking}")
    public ApiResponse getABooking(@PathVariable("idbooking") Integer idbooking){
        return getABookingService.getABooking(idbooking);
    }

    @GetMapping("/carowner")
    public ApiResponse getListBooking(){
        return getABookingService.getListBooking();
    }

    @GetMapping("/user")
    public ApiResponse getListBookingUser(){
        return getABookingService.getListBookingUser();
    }
}
