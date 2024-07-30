package com.example.SpringBootLearning.controller;

import com.example.SpringBootLearning.dto.respone.ApiResponse;
import com.example.SpringBootLearning.service.StopRentingCarService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/stoprentingcar")
public class StopRentingCarController {
    StopRentingCarService stopRentingCarService;

    @PostMapping("/{idcar}")
    public ApiResponse stopRentingCar(@PathVariable("idcar") Integer idcar){
        return new ApiResponse()
                .builder()
                .result(stopRentingCarService.stopRentingCar(idcar))
                .build();
    }

}
