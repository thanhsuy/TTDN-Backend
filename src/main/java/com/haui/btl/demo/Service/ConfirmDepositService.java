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

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Builder
public class ConfirmDepositService {

    @Autowired
    CarRepository carRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    BookingMapper bookingMapper;

    @Autowired
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
