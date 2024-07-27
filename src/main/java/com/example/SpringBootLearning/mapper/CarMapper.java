package com.example.SpringBootLearning.mapper;

import com.example.SpringBootLearning.dto.request.AddCarRequest;
import com.example.SpringBootLearning.entity.Car;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarMapper {
    public Car toCar(AddCarRequest request);
}
