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
        return new ApiResponse()
                .builder()
                .result(getABookingService.getABooking(idbooking))
                .build();
    }
}
