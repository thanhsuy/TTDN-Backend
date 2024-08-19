package com.haui.btl.demo.Service;

import com.haui.btl.demo.Entity.Car;
import com.haui.btl.demo.Entity.User;
import com.haui.btl.demo.Exception.AppException;
import com.haui.btl.demo.Exception.ErrorCode;
import com.haui.btl.demo.Mapper.CarMapper;
import com.haui.btl.demo.Repository.AdditionalfunctionsRepository;
import com.haui.btl.demo.Repository.CarRepository;
import com.haui.btl.demo.Repository.TermofuseRepository;
import com.haui.btl.demo.Repository.UserRepository;
import com.haui.btl.demo.dto.request.AddCarRequest;
import com.haui.btl.demo.dto.request.EditCarRequest;
import com.haui.btl.demo.dto.response.ApiResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Builder
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EditCarService {
    @Autowired
    CarRepository carRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TermofuseRepository termofuseRepository;

    @Autowired
    AdditionalfunctionsRepository additionalfunctionsRepository;

    public ApiResponse editCar(Integer idCar, EditCarRequest request) {
        var oldcar = carRepository.findById(idCar).orElseThrow(()
                -> new AppException(ErrorCode.USER_NOTFOUND));
        if (Objects.equals(oldcar.getStatus(), "Booked")){
            throw new AppException(ErrorCode.CANNOT_EDIT);
        }
        Car car = new Car(idCar, request.getName(), request.getBrand(), request.getModel(), request.getColor(),
                request.getNumberofseats(), request.getProductionyears(), request.getTranmissiontype(), request.getFueltype(),
                request.getMileage(), request.getFuelconsumption(), request.getBaseprice(), request.getDeposite(),
                request.getAddress(), request.getDescripton(), request.getImages(), request.getStatus(), request.getIdcarowner());
        car.setStatus("Available");
        var context = SecurityContextHolder.getContext();
        String email = context.getAuthentication().getName();

        User user = userRepository.findByEmail(email).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        car.setIdcarowner(user.getIduser());
        carRepository.save(car);
        ApiResponse apiResponse = new ApiResponse()
                .builder()
                .result(car)
                .build();
        return apiResponse;
    }
}
