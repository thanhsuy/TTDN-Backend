package com.example.SpringBootLearning.controller;

import com.example.SpringBootLearning.dto.request.RentACarRequest;
import com.example.SpringBootLearning.dto.respone.ApiResponse;
import com.example.SpringBootLearning.service.RentACarService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class RentACarController {
    RentACarService rentACarService;

    @PostMapping("/makeABooking/{carIdcar}")
    public ApiResponse makeABooking(@RequestBody RentACarRequest request, @PathVariable("carIdcar") int carIdcar){
        return ApiResponse
                .builder()
                .result(rentACarService.makeABooking(request,carIdcar))
                .build();
    }
    @PostMapping("/paidDeposid/{idbooking}")
    public ApiResponse paidDeposid(@PathVariable("idbooking") Integer idbooking){
        return ApiResponse
                .builder()
                .result(rentACarService.paidDeposid(idbooking))
                .build();
    }

    @GetMapping("/getcar/{carIdcar}")
    public ApiResponse getCarById(@PathVariable("carIdcar") Integer carIdcar){
        return ApiResponse
                .builder()
                .result(rentACarService.getCarById(carIdcar))
                .build();
    }
}
