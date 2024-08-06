package com.example.SpringBootLearning.mapper;

import org.mapstruct.Mapper;

import com.example.SpringBootLearning.dto.request.RentACarRequest;
import com.example.SpringBootLearning.entity.Booking;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    public Booking toBooking(RentACarRequest request);
}
