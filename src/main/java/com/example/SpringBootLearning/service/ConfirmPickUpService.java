package com.example.SpringBootLearning.service;

import com.example.SpringBootLearning.dto.respone.ApiResponse;
import com.example.SpringBootLearning.entity.Booking;
import com.example.SpringBootLearning.entity.Car;
import com.example.SpringBootLearning.entity.User;
import com.example.SpringBootLearning.enums.BookingStatus;
import com.example.SpringBootLearning.exception.AppException;
import com.example.SpringBootLearning.exception.ErrorCode;
import com.example.SpringBootLearning.mapper.BookingMapper;
import com.example.SpringBootLearning.repository.BookingRepository;
import com.example.SpringBootLearning.repository.CarRepository;
import com.example.SpringBootLearning.repository.UserRepository;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Builder
public class ConfirmPickUpService {
    CarRepository carRepository;
    BookingRepository bookingRepository;
    BookingMapper bookingMapper;
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
