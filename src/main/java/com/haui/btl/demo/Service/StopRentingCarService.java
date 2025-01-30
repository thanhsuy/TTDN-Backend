package com.haui.btl.demo.Service;

import com.haui.btl.demo.Entity.Car;
import com.haui.btl.demo.Entity.User;
import com.haui.btl.demo.Enum.CarStatus;
import com.haui.btl.demo.Exception.AppException;
import com.haui.btl.demo.Exception.ErrorCode;
import com.haui.btl.demo.Repository.CarRepository;
import com.haui.btl.demo.Repository.UserRepository;
import com.haui.btl.demo.dto.response.ApiResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Builder
public class StopRentingCarService {

    @Autowired
    CarRepository carRepository;

    @Autowired
    UserRepository userRepository;

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

    @PreAuthorize("hasRole('CAROWNER')")
    public ApiResponse getListCar() {
        var context = SecurityContextHolder.getContext();
//        Jwt jwt = (Jwt) context.getAuthentication().getPrincipal();
//        var claims = jwt.getClaims();
//        Long longIdUser = (Long) claims.get("id");
        String email = context.getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        List<Car> listCar = carRepository.findAllByidcarowner((long) user.getIduser());
        return new ApiResponse().builder().result(listCar).build();
    }
}
