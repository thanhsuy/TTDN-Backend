package com.haui.btl.demo.Service;

import com.haui.btl.demo.dto.response.ApiResponse;
import com.haui.btl.demo.Entity.Booking;
import com.haui.btl.demo.Entity.Car;
import com.haui.btl.demo.Entity.User;
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

import java.util.ArrayList;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Builder
public class CancelBookingService {
    @Autowired
    CarRepository carRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    BookingMapper bookingMapper;

    @Autowired
    UserRepository userRepository;

    @PreAuthorize("hasRole('CUSTOMER')")
    public ApiResponse cancelBooking(Integer idbooking){
        System.out.println(idbooking);
        Booking booking = bookingRepository.findById(idbooking).orElseThrow(() -> new AppException(ErrorCode.BOOKING_NOTFOUND));
        User user = userRepository.findById(booking.getUserIduser()).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        User carowner = userRepository.findById(booking.getCarIdcarowner()).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        Car car = carRepository.findById(booking.getCarIdcar()).orElseThrow(() -> new AppException(ErrorCode.CAR_NOTFOUND));
        if(booking.getStatus().equals(BookingStatus.CONFIRMRED.getStatus()))
        {
            user.setWallet(user.getWallet() + car.getDeposite());
            carowner.setWallet(carowner.getWallet() - car.getDeposite());
            userRepository.save(user);
            userRepository.save(carowner);
            booking.setStatus(BookingStatus.CANCELLED.getStatus());
            bookingRepository.save(booking);
        }
        List<User> resArray = new ArrayList<>();
        resArray.add(user);
        resArray.add(carowner);
        return new ApiResponse()
                .builder()
                .result(booking)
                .build();
    }
}
