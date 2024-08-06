package com.example.SpringBootLearning.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

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

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Builder
public class CancelBookingService {
    CarRepository carRepository;
    BookingRepository bookingRepository;
    BookingMapper bookingMapper;
    UserRepository userRepository;

    @PreAuthorize("hasRole('CUSTOMER')")
    public ApiResponse cancelBooking(Integer idbooking) {
        System.out.println(idbooking);
        Booking booking =
                bookingRepository.findById(idbooking).orElseThrow(() -> new AppException(ErrorCode.BOOKING_NOTFOUND));
        User user = userRepository
                .findById(booking.getUserIduser())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        User carowner = userRepository
                .findById(booking.getCarIdcarowner())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        Car car = carRepository
                .findById(booking.getCarIdcar())
                .orElseThrow(() -> new AppException(ErrorCode.CAR_NOTFOUND));
        if (booking.getStatus().equals(BookingStatus.CONFIRMRED.getStatus())) {
            user.setWallet(user.getWallet() + car.getDeposite());
            System.out.println(user.getWallet());
            carowner.setWallet(carowner.getWallet() - car.getDeposite());
            userRepository.save(user);
            userRepository.save(carowner);
            booking.setStatus(BookingStatus.CANCELLED.getStatus());
            bookingRepository.save(booking);
        }
        List<User> resArray = new ArrayList<>();
        resArray.add(user);
        resArray.add(carowner);
        return new ApiResponse().builder().result(booking).build();
    }
}
