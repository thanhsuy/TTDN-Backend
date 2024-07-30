package com.haui.btl.demo.Service;

import com.haui.btl.demo.Enum.CarStatus;
import com.haui.btl.demo.Exception.AppException;
import com.haui.btl.demo.Exception.ErrorCode;
import com.haui.btl.demo.Repository.CarRepository;
import com.haui.btl.demo.dto.response.ApiResponse;
import com.haui.btl.demo.Entity.Car;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Builder
public class StopRentingCarService {

    @Autowired
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
