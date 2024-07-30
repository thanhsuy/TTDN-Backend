package com.haui.btl.demo.Service;

import com.haui.btl.demo.dto.response.ApiResponse;
import com.haui.btl.demo.Entity.Booking;
import com.haui.btl.demo.Enum.BookingStatus;
import com.haui.btl.demo.Exception.AppException;
import com.haui.btl.demo.Exception.ErrorCode;
import com.haui.btl.demo.Mapper.BookingMapper;
import com.haui.btl.demo.Repository.BookingRepository;
import com.haui.btl.demo.Repository.CarRepository;
import com.haui.btl.demo.Repository.UserRepository;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Builder
public class ConfirmPickUpService {

    @Autowired
    CarRepository carRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    BookingMapper bookingMapper;

    @Autowired
    UserRepository userRepository;

    @PreAuthorize("hasRole('CUSTOMER')")
    public ApiResponse confirmPickUpService(Integer idbooking){
        Booking booking = bookingRepository.findById(idbooking).orElseThrow(() -> new AppException(ErrorCode.BOOKING_NOTFOUND));
        if(booking.getStatus().equals(BookingStatus.CONFIRMRED.getStatus()))
        {
            booking.setStatus(BookingStatus.IN_PROGRESS.getStatus());
            bookingRepository.save(booking);
        }
        return new ApiResponse()
                .builder()
                .result(booking)
                .build();
    }
}
