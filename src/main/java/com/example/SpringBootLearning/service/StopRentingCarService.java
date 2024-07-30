package com.example.SpringBootLearning.service;

import com.example.SpringBootLearning.dto.respone.ApiResponse;
import com.example.SpringBootLearning.entity.Car;
import com.example.SpringBootLearning.enums.CarStatus;
import com.example.SpringBootLearning.exception.AppException;
import com.example.SpringBootLearning.exception.ErrorCode;
import com.example.SpringBootLearning.repository.CarRepository;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Builder
public class StopRentingCarService {
    CarRepository carRepository;

    @PreAuthorize("hasRole('CAROWNER')")
    public ApiResponse stopRentingCar(Integer idcar){
        Car car = carRepository.findById(idcar).orElseThrow(() -> new AppException(ErrorCode.CAR_NOTFOUND));
        if (car.getStatus().equals(CarStatus.Available.name())){
            car.setStatus(CarStatus.Stopped.name());
            carRepository.save(car);
        }else throw new AppException(ErrorCode.STOP_CAR_FAIL);

        return new ApiResponse()
                .builder()
                .result(car)
                .build();
    }
}
