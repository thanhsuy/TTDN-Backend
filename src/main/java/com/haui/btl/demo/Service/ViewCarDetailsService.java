package com.haui.btl.demo.Service;

import com.haui.btl.demo.Entity.Additionalfunctions;
import com.haui.btl.demo.Entity.Car;
import com.haui.btl.demo.Entity.Termofuse;
import com.haui.btl.demo.Repository.AdditionalfunctionsRepository;
import com.haui.btl.demo.Repository.CarRepository;
import com.haui.btl.demo.Repository.TermofuseRepository;
import com.haui.btl.demo.dto.response.ViewCarDetailsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewCarDetailsService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private TermofuseRepository termofuseRepository;

    @Autowired
    private AdditionalfunctionsRepository additionalfunctionsRepository;

    public ViewCarDetailsResponse getCarDetails(int id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new RuntimeException("Car not found"));
        List<Termofuse> termsOfUse = termofuseRepository.findByIdcar(id);
        List<Additionalfunctions> additionalFunctions = additionalfunctionsRepository.findByIdcar(id);

        return ViewCarDetailsResponse.builder()
                .car(car)
                .termsOfUse(termsOfUse)
                .additionalFunctions(additionalFunctions)
                .build();
    }

}
