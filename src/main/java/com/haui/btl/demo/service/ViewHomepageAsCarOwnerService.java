package com.haui.btl.demo.service;

import com.haui.btl.demo.Entity.Car;
import com.haui.btl.demo.dto.response.ApiResponse;
import com.haui.btl.demo.exception.AppException;
import com.haui.btl.demo.exception.ErrorCode;
import com.haui.btl.demo.Repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewHomepageAsCarOwnerService {

    @Autowired
    private CarRepository carRepository;

    // Lấy tất cả các xe
//    public ApiResponse<List<Car>> getAllCars() {
//        List<Car> cars = carRepository.findAll();
//        return ApiResponse.of(cars);
//    }

    // Lấy các xe theo ID của chủ xe
    public ApiResponse<List<Car>> getCarsByOwnerId(int ownerId) {
        List<Car> cars = carRepository.findAllByIdcarowner(ownerId);
        if (cars.isEmpty()) {
            throw new AppException(ErrorCode.CAR_NOTFOUND);
        }
        return ApiResponse.of(cars);
    }
}
