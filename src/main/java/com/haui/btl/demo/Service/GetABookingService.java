package com.haui.btl.demo.Service;


import com.haui.btl.demo.Entity.User;
import com.haui.btl.demo.dto.response.ApiResponse;
import com.haui.btl.demo.Entity.Booking;
import com.haui.btl.demo.Exception.AppException;
import com.haui.btl.demo.Exception.ErrorCode;
import com.haui.btl.demo.Mapper.BookingMapper;
import com.haui.btl.demo.Repository.BookingRepository;
import com.haui.btl.demo.Repository.CarRepository;
import com.haui.btl.demo.Repository.UserRepository;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Builder
public class GetABookingService {
    @Autowired
    CarRepository carRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    BookingMapper bookingMapper;

    @Autowired
    UserRepository userRepository;

    public ApiResponse getABooking(Integer idbooking){
        Booking booking = bookingRepository.findById(idbooking).orElseThrow(() -> new AppException(ErrorCode.BOOKING_NOTFOUND));
        return new ApiResponse()
                .builder()
                .result(booking)
                .build();
    }

    public ApiResponse getListBooking() {
        var context = SecurityContextHolder.getContext();
//        Jwt jwt = (Jwt) context.getAuthentication().getPrincipal();
//        var claims = jwt.getClaims();
//        Long longIdUser = (Long) claims.get("id");
//        int idUser = longIdUser.intValue();
//        User user = userRepository.findById(idUser).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        String email = context.getAuthentication().getName();

        User user = userRepository.findByEmail(email).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        List<Booking> booking = bookingRepository.findBookingByIdCarOwner(user.getIduser());
        return new ApiResponse<>().builder().result(booking).build();
    }

    public ApiResponse getListBookingUser() {
        var context = SecurityContextHolder.getContext();
//        Jwt jwt = (Jwt) context.getAuthentication().getPrincipal();
//        var claims = jwt.getClaims();
//        Long longIdUser = (Long) claims.get("id");
//        int idUser = longIdUser.intValue();
//        User user = userRepository.findById(idUser).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        String email = context.getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        List<Booking> booking = bookingRepository.findBookingByIdUser(user.getIduser());
        return new ApiResponse<>().builder().result(booking).build();
    }

}
