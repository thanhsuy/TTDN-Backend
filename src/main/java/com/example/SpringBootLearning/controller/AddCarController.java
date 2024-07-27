package com.example.SpringBootLearning.controller;

import com.example.SpringBootLearning.dto.request.AddCarRequest;
import com.example.SpringBootLearning.dto.respone.ApiResponse;
import com.example.SpringBootLearning.service.AddCarService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/car")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AddCarController {
    AddCarService addCarService;

    @PostMapping("/addcar")
    @CrossOrigin(origins = "http://localhost:3000")
    public ApiResponse addCar(@RequestBody AddCarRequest request)
    {
        return addCarService.addCar(request);
    }

}
