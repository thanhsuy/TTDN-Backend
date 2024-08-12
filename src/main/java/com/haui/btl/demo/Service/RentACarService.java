package com.haui.btl.demo.Service;

import com.haui.btl.demo.Entity.Booking;
import com.haui.btl.demo.Entity.Car;
import com.haui.btl.demo.Entity.User;
import com.haui.btl.demo.Enum.BookingStatus;
import com.haui.btl.demo.Enum.PayMentMethod;
import com.haui.btl.demo.Exception.AppException;
import com.haui.btl.demo.Exception.ErrorCode;
import com.haui.btl.demo.Mapper.BookingMapper;
import com.haui.btl.demo.Repository.BookingRepository;
import com.haui.btl.demo.Repository.CarRepository;
import com.haui.btl.demo.Repository.UserRepository;
import com.haui.btl.demo.dto.request.RentACarRequest;
import com.haui.btl.demo.dto.response.ApiResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Builder
public class RentACarService {
    CarRepository carRepository;
    BookingRepository bookingRepository;
    BookingMapper bookingMapper;
    UserRepository userRepository;
    VNPAYService vnpayService;

    @PreAuthorize("hasRole('CUSTOMER')")
    public ApiResponse makeABooking(RentACarRequest request, int carIdcar) {
        Booking booking = bookingMapper.toBooking(request);
        booking.setCarIdcar(carIdcar);
        Car car = carRepository.findById(carIdcar).orElseThrow(() -> new AppException(ErrorCode.CAR_NOTFOUND));
        int idCarOwner = car.getIdcarowner();

        if (carRepository.checkCarAvailable(request.getStartdatetime(), request.getEnddatetime(), carIdcar) == 1) {
            booking.setCarIdcarowner(idCarOwner);
            var context = SecurityContextHolder.getContext();
            String email = context.getAuthentication().getName();
            User user = userRepository.findByEmail(email).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
            booking.setUserIduser(user.getIduser());
            booking.setStatus(BookingStatus.PENDING_DEPOSIT.getStatus());
            booking.setEnddatetime(request.getEnddatetime().atStartOfDay());
            booking.setStartdatetime(request.getStartdatetime().atStartOfDay());
            bookingRepository.save(booking);

        } else throw new AppException(ErrorCode.CAR_NOT_AVAILABLE);

        return new ApiResponse()
                .builder()
                .result(booking)
                .build();
    }

    public ApiResponse paidDeposid(Integer idbooking) throws UnsupportedEncodingException {
        Booking booking =
                bookingRepository.findById(idbooking).orElseThrow(() -> new AppException(ErrorCode.BOOKING_NOTFOUND));
        User user = userRepository
                .findById(booking.getUserIduser())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        Car car = carRepository
                .findById(booking.getCarIdcar())
                .orElseThrow(() -> new AppException(ErrorCode.CAR_NOTFOUND));
        User carowner = userRepository
                .findById(booking.getCarIdcarowner())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));

        if (booking.getStatus().equals(BookingStatus.PENDING_DEPOSIT.getStatus())) {
            if (booking.getPaymentmethod().equals(PayMentMethod.WALLET.getName())) {
                user.setWallet(user.getWallet() - car.getDeposite());
                booking.setStatus(BookingStatus.CONFIRMRED.getStatus());
                car.setStatus("Booked");
                carowner.setWallet(carowner.getWallet() + car.getDeposite());
            }
        }else{
            booking.setStatus(BookingStatus.PENDING_DEPOSIT.getStatus());
        }

        userRepository.save(user);
        userRepository.save(carowner);
        bookingRepository.save(booking);
        return new ApiResponse().builder().result(booking).build();
    }

    public ApiResponse getCarById(Integer carIdcar) {
        Car car = carRepository.findById(carIdcar).orElseThrow(() -> new AppException(ErrorCode.CAR_NOTFOUND));
        return new ApiResponse().builder().result(car).build();
    }

    public ApiResponse getListCar() {
        return new ApiResponse<>().builder().result(carRepository.findAll()).build();
    }
}