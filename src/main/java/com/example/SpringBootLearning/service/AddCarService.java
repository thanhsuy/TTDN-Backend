package com.example.SpringBootLearning.service;

import com.example.SpringBootLearning.dto.request.AddCarRequest;
import com.example.SpringBootLearning.dto.respone.ApiResponse;
import com.example.SpringBootLearning.entity.Car;
import com.example.SpringBootLearning.mapper.CarMapper;
import com.example.SpringBootLearning.repository.CarRepository;
import com.example.SpringBootLearning.repository.UserRepository;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Builder
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AddCarService {
    CarRepository carRepository;
    UserRepository userRepository;
    CarMapper carMapper;

    @PreAuthorize("hasRole('CAROWNER')")
    public ApiResponse addCar(AddCarRequest request)
    {
        Car car = carMapper.toCar(request);
        car.setStatus("Available");
        var context = SecurityContextHolder.getContext();
        Jwt jwt = (Jwt) context.getAuthentication().getPrincipal();
        var claims = jwt.getClaims();
        Long longIdUser = (Long) claims.get("id");
        int idUser = longIdUser.intValue();
        car.setIdcarowner(idUser);
        carRepository.save(car);
        ApiResponse apiResponse = new ApiResponse()
                .builder()
                .result(car)
                .build();
        return apiResponse;
    }
}
