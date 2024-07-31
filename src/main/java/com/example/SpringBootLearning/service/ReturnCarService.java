package com.example.SpringBootLearning.service;

import com.example.SpringBootLearning.dto.request.RentACarRequest;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.awt.print.Book;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Builder
public class ReturnCarService {
    CarRepository carRepository;
    BookingRepository bookingRepository;
    BookingMapper bookingMapper;
    UserRepository userRepository;

    @PreAuthorize("hasRole('CUSTOMER')")
    public ApiResponse returnCar(Integer idbooking){
        Booking booking = bookingRepository.findById(idbooking).orElseThrow(() -> new AppException(ErrorCode.BOOKING_NOTFOUND));
        User user = userRepository.findById(booking.getUserIduser()).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        User carOwner = userRepository.findById(booking.getCarIdcarowner()).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        Car car = carRepository.findById(booking.getCarIdcar()).orElseThrow(() -> new AppException(ErrorCode.CAR_NOTFOUND));
        if(booking.getStatus().equals(BookingStatus.IN_PROGRESS.getStatus()) || booking.getStatus().equals(BookingStatus.PENDING_PAYMENT.getStatus()))
        {
            float remaining = car.getBaseprice() - car.getDeposite();
            if (user.getWallet() < remaining){
                booking.setStatus(BookingStatus.PENDING_PAYMENT.getStatus());
                bookingRepository.save(booking);
                throw new AppException(ErrorCode.NOTENOUGH_WALLET);
            }
            booking.setStatus(BookingStatus.COMPLETE.getStatus());
            user.setWallet(user.getWallet() - remaining);
            carOwner.setWallet(carOwner.getWallet() + remaining);
            car.setStatus("Available");
            carRepository.save(car);
            userRepository.save(user);
            userRepository.save(carOwner);
            bookingRepository.save(booking);
        }
        return new ApiResponse()
                .builder()
                .result(booking)
                .build();
    }
}
