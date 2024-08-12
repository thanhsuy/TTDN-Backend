package com.haui.btl.demo.Mapper;

import com.haui.btl.demo.Entity.Car;
import com.haui.btl.demo.dto.request.AddCarRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarMapper {
    public Car toCar(AddCarRequest request);
}
