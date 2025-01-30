package com.haui.btl.demo.Service;


import com.haui.btl.demo.Entity.Additionalfunctions;
import com.haui.btl.demo.Entity.Car;
import com.haui.btl.demo.Entity.Termofuse;
import com.haui.btl.demo.Entity.User;
import com.haui.btl.demo.Exception.AppException;
import com.haui.btl.demo.Exception.ErrorCode;
import com.haui.btl.demo.Mapper.CarMapper;
import com.haui.btl.demo.Repository.AdditionalfunctionsRepository;
import com.haui.btl.demo.Repository.CarRepository;
import com.haui.btl.demo.Repository.TermofuseRepository;
import com.haui.btl.demo.Repository.UserRepository;
import com.haui.btl.demo.dto.request.AddCarRequest;
import com.haui.btl.demo.dto.response.ApiResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Builder
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AddCarService {

    @Autowired
    CarRepository carRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CarMapper carMapper;

    @Autowired
    TermofuseRepository termofuseRepository;

    @Autowired
    AdditionalfunctionsRepository additionalfunctionsRepository;

    public ApiResponse addCar(AddCarRequest request)
    {
        Car car = carMapper.toCar(request);
        car.setStatus("Available");
        var context = SecurityContextHolder.getContext();
//        Jwt jwt = (Jwt) context.getAuthentication().getPrincipal();
//        var claims = jwt.getClaims();
//        Long longIdUser = (Long) claims.get("id");
//        int idUser = longIdUser.intValue();
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

    public ApiResponse addTerm(Termofuse termofuse){
        termofuseRepository.save(termofuse);
        ApiResponse apiResponse = new ApiResponse()
                .builder()
                .result(termofuse)
                .build();
        return apiResponse;
    }

    public ApiResponse addFunctions(Additionalfunctions additionalfunctions){
        additionalfunctionsRepository.save(additionalfunctions);
        ApiResponse apiResponse = new ApiResponse()
                .builder()
                .result(additionalfunctions)
                .build();
        return apiResponse;
    }

}
