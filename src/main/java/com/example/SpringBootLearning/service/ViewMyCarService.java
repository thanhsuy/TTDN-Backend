package com.example.SpringBootLearning.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import com.example.SpringBootLearning.dto.respone.ApiResponse;
import com.example.SpringBootLearning.entity.Car;
import com.example.SpringBootLearning.exception.AppException;
import com.example.SpringBootLearning.exception.ErrorCode;
import com.example.SpringBootLearning.repository.CarRepository;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Builder
public class ViewMyCarService {
    CarRepository carRepository;

    @PreAuthorize("hasRole('CAROWNER')")
    public ApiResponse viewMyCars() {
        var context = SecurityContextHolder.getContext();
        Jwt jwt = (Jwt) context.getAuthentication().getPrincipal();
        var claims = jwt.getClaims();
        Long longIdUser = (Long) claims.get("id");

        List<Car> cars = carRepository.findAllByidcarowner(longIdUser);
        return new ApiResponse().builder().result(cars).build();
    }

    @PreAuthorize("hasRole('CAROWNER')")
    public ApiResponse viewMyCarById(Integer idcar) {
        Car car = carRepository.findById(idcar).orElseThrow(() -> new AppException(ErrorCode.CAR_NOTFOUND));
        return new ApiResponse().builder().result(car).build();
    }
}
