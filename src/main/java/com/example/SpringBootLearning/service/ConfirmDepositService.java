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
public class ConfirmDepositService {
    CarRepository carRepository;
    BookingRepository bookingRepository;
    BookingMapper bookingMapper;
    UserRepository userRepository;

    @PreAuthorize("hasRole('CAROWNER')")
    public ApiResponse confirmDeposit(Integer idbooking){
        Booking booking = bookingRepository.findById(idbooking).orElseThrow(() -> new AppException(ErrorCode.BOOKING_NOTFOUND));
        User user = userRepository.findById(booking.getUserIduser()).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        Car car = carRepository.findById(booking.getCarIdcar()).orElseThrow(() -> new AppException(ErrorCode.CAR_NOTFOUND));
        if(booking.getStatus().equals(BookingStatus.PENDING_DEPOSIT.getStatus()))
        {
            User carOwner = userRepository.findById(booking.getCarIdcarowner()).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
            carOwner.setWallet(carOwner.getWallet() + car.getDeposite());
            car.setStatus("Booked");
            carRepository.save(car);
            userRepository.save(carOwner);
            booking.setStatus(BookingStatus.CONFIRMRED.getStatus());
            bookingRepository.save(booking);
        }else throw new AppException(ErrorCode.CAROWNER_CONFIRM);
        return new ApiResponse()
                .builder()
                .result(booking)
                .build();
    }
}
