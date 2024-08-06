package com.example.SpringBootLearning.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringBootLearning.dto.respone.ApiResponse;
import com.example.SpringBootLearning.service.ReturnCarService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/returncar")
public class ReturnCarController {
    ReturnCarService returnCarService;

    @PostMapping("/{idbooking}")
    public ApiResponse returnCar(@PathVariable("idbooking") Integer idbooking) {
        return returnCarService.returnCar(idbooking);
    }
}
