package com.example.SpringBootLearning.controller;

import org.springframework.web.bind.annotation.*;

import com.example.SpringBootLearning.dto.respone.ApiResponse;
import com.example.SpringBootLearning.service.StopRentingCarService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/stoprentingcar")
public class StopRentingCarController {
    StopRentingCarService stopRentingCarService;

    @PostMapping("/{idcar}")
    public ApiResponse stopRentingCar(@PathVariable("idcar") Integer idcar) {
        return stopRentingCarService.stopRentingCar(idcar);
    }

    @GetMapping("/getlistcarbyidcarowner")
    public ApiResponse getListCar() {
        return stopRentingCarService.getListCar();
    }
}
