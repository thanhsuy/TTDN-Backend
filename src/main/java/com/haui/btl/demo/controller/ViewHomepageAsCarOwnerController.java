package com.haui.btl.demo.controller;

import com.haui.btl.demo.Entity.Car;
import com.haui.btl.demo.dto.response.ApiResponse;
import com.haui.btl.demo.service.ViewHomepageAsCarOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/viewHomeOwner")
public class ViewHomepageAsCarOwnerController {
    @Autowired
    private ViewHomepageAsCarOwnerService viewHomepageAsCarOwnerService;

    // API để lấy tất cả các xe (nếu cần thiết)
//    @GetMapping
//    public ApiResponse<List<Car>> getAllCars() {
//        return viewHomepageAsCarOwnerService.getAllCars();
//    }

    // API để lấy các xe theo ID của chủ xe
    @GetMapping("/owner/{ownerId}")
    public ApiResponse<List<Car>> getCarsByOwnerId(@PathVariable int ownerId) {
        return viewHomepageAsCarOwnerService.getCarsByOwnerId(ownerId);
    }
}
