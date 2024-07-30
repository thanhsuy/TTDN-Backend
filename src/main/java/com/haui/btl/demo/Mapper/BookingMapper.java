package com.haui.btl.demo.Mapper;


import com.haui.btl.demo.Entity.Booking;
import com.haui.btl.demo.dto.request.RentACarRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    public Booking toBooking(RentACarRequest request);
}
