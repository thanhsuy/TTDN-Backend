package com.example.SpringBootLearning.controller;

import org.springframework.web.bind.annotation.*;

import com.example.SpringBootLearning.dto.request.RentACarRequest;
import com.example.SpringBootLearning.dto.respone.ApiResponse;
import com.example.SpringBootLearning.service.RentACarService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.UnsupportedEncodingException;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class RentACarController {
    RentACarService rentACarService;

    @PostMapping("/makeABooking/{carIdcar}")
    public ApiResponse makeABooking(@RequestBody RentACarRequest request, @PathVariable("carIdcar") int carIdcar) {
        return rentACarService.makeABooking(request, carIdcar);
    }

    @PostMapping("/paidDeposid/{idbooking}")
    public ApiResponse paidDeposid(@PathVariable("idbooking") Integer idbooking) throws UnsupportedEncodingException {
        return rentACarService.paidDeposid(idbooking);
    }

    @GetMapping("/getcar/{carIdcar}")
    public ApiResponse getCarById(@PathVariable("carIdcar") Integer carIdcar) {
        return rentACarService.getCarById(carIdcar);
    }

    @GetMapping("/getlistcar")
    public ApiResponse getListCar() {
        return rentACarService.getListCar();
    }
}
