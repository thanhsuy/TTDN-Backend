package com.example.SpringBootLearning.service;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import com.example.SpringBootLearning.dto.respone.ApiResponse;
import com.example.SpringBootLearning.entity.Booking;
import com.example.SpringBootLearning.entity.User;
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
public class GetABookingService {
    CarRepository carRepository;
    BookingRepository bookingRepository;
    BookingMapper bookingMapper;
    UserRepository userRepository;

    public ApiResponse getABooking(Integer idbooking) {
        Booking booking =
                bookingRepository.findById(idbooking).orElseThrow(() -> new AppException(ErrorCode.BOOKING_NOTFOUND));
        return new ApiResponse().builder().result(booking).build();
    }

    public ApiResponse getListBooking() {
        var context = SecurityContextHolder.getContext();
        Jwt jwt = (Jwt) context.getAuthentication().getPrincipal();
        var claims = jwt.getClaims();
        Long longIdUser = (Long) claims.get("id");
        int idUser = longIdUser.intValue();
        User user = userRepository.findById(idUser).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        List<Booking> booking = bookingRepository.findBookingByIdCarOwner(idUser);
        return new ApiResponse<>().builder().result(booking).build();
    }

    public ApiResponse getListBookingUser() {
        var context = SecurityContextHolder.getContext();
        Jwt jwt = (Jwt) context.getAuthentication().getPrincipal();
        var claims = jwt.getClaims();
        Long longIdUser = (Long) claims.get("id");
        int idUser = longIdUser.intValue();
        User user = userRepository.findById(idUser).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        List<Booking> booking = bookingRepository.findBookingByIdUser(idUser);
        return new ApiResponse<>().builder().result(booking).build();
    }
}
