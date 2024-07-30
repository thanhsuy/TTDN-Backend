package com.example.SpringBootLearning.mapper;

import com.example.SpringBootLearning.dto.request.RentACarRequest;
import com.example.SpringBootLearning.entity.Booking;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    public Booking toBooking(RentACarRequest request);
}
