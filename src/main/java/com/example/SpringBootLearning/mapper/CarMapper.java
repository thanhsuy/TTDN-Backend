package com.example.SpringBootLearning.mapper;

import org.mapstruct.Mapper;

import com.example.SpringBootLearning.dto.request.AddCarRequest;
import com.example.SpringBootLearning.entity.Car;

@Mapper(componentModel = "spring")
public interface CarMapper {
    public Car toCar(AddCarRequest request);
}
