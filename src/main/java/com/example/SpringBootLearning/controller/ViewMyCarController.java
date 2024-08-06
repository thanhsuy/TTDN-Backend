package com.example.SpringBootLearning.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringBootLearning.dto.respone.ApiResponse;
import com.example.SpringBootLearning.service.ViewMyCarService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/viewMyCar")
public class ViewMyCarController {
    ViewMyCarService viewMyCarService;

    @GetMapping()
    public ApiResponse viewMyCars() {
        return viewMyCarService.viewMyCars();
    }

    @GetMapping("/{idcar}")
    public ApiResponse viewMyCarById(@PathVariable("idcar") Integer idcar) {
        return viewMyCarService.viewMyCarById(idcar);
    }
}
