package com.haui.btl.demo.Service;


import com.haui.btl.demo.Entity.Car;
import com.haui.btl.demo.Exception.AppException;
import com.haui.btl.demo.Exception.ErrorCode;
import com.haui.btl.demo.Repository.CarRepository;
import com.haui.btl.demo.dto.response.ApiResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Builder
public class ViewMyCarService {
    CarRepository carRepository;

    @PreAuthorize("hasRole('CAROWNER')")
    public ApiResponse viewMyCars(){
        var context = SecurityContextHolder.getContext();
        Jwt jwt = (Jwt) context.getAuthentication().getPrincipal();
        var claims = jwt.getClaims();
        Long longIdUser = (Long) claims.get("id");

        List<Car> cars = carRepository.findAllByidcarowner(longIdUser);
        return new ApiResponse()
                .builder()
                .result(cars)
                .build();
    }

    @PreAuthorize("hasRole('CAROWNER')")
    public ApiResponse viewMyCarById(Integer idcar){
        Car car = carRepository.findById(idcar).orElseThrow(() -> new AppException(ErrorCode.CAR_NOTFOUND));
        return new ApiResponse()
                .builder()
                .result(car)
                .build();
    }
}
