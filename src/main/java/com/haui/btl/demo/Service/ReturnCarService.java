package com.haui.btl.demo.Service;

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
import com.haui.btl.demo.dto.response.ApiResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Builder
public class ReturnCarService {

    @Autowired
    CarRepository carRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    BookingMapper bookingMapper;

    @Autowired
    UserRepository userRepository;

//    @PreAuthorize("hasRole('CUSTOMER')")
//    public ApiResponse returnCar(Integer idbooking){
//        Booking booking = bookingRepository.findById(idbooking).orElseThrow(() -> new AppException(ErrorCode.BOOKING_NOTFOUND));
//        User user = userRepository.findById(booking.getUserIduser()).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
//        User carOwner = userRepository.findById(booking.getCarIdcarowner()).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
//        Car car = carRepository.findById(booking.getCarIdcar()).orElseThrow(() -> new AppException(ErrorCode.CAR_NOTFOUND));
//        if(booking.getStatus().equals(BookingStatus.IN_PROGRESS.getStatus()))
//        {
//            float remaining = car.getBaseprice() - car.getDeposite();
//            if (user.getWallet() < remaining){
//                booking.setStatus(BookingStatus.PENDING_PAYMENT.getStatus());
//                bookingRepository.save(booking);
//                throw new AppException(ErrorCode.NOTENOUGH_WALLET);
//            }
//            booking.setStatus(BookingStatus.COMPLETE.getStatus());
//            user.setWallet(user.getWallet() - remaining);
//            carOwner.setWallet(carOwner.getWallet() + remaining);
//            userRepository.save(user);
//            userRepository.save(carOwner);
//            bookingRepository.save(booking);
//        }
//        return new ApiResponse()
//                .builder()
//                .result(booking)
//                .build();
//    }
@PreAuthorize("hasRole('CUSTOMER')")
public ApiResponse returnCar(Integer idbooking) {
    Booking booking =
            bookingRepository.findById(idbooking).orElseThrow(() -> new AppException(ErrorCode.BOOKING_NOTFOUND));
    User user = userRepository
            .findById(booking.getUserIduser())
            .orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
    User carOwner = userRepository
            .findById(booking.getCarIdcarowner())
            .orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
    Car car = carRepository
            .findById(booking.getCarIdcar())
            .orElseThrow(() -> new AppException(ErrorCode.CAR_NOTFOUND));
    if (booking.getStatus().equals(BookingStatus.IN_PROGRESS.getStatus())
            || booking.getStatus().equals(BookingStatus.PENDING_PAYMENT.getStatus())) {
        int dayBetween = (int) ChronoUnit.DAYS.between(booking.getStartdatetime(), booking.getEnddatetime());
        float remaining = car.getBaseprice() * dayBetween - car.getDeposite();
        if (user.getWallet() < remaining) {
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
    return new ApiResponse().builder().result(booking).build();
}
}
