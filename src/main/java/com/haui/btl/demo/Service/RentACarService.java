package com.haui.btl.demo.Service;

import com.haui.btl.demo.dto.request.RentACarRequest;
import com.haui.btl.demo.dto.response.ApiResponse;
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
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Builder
public class RentACarService {

    @Autowired
    CarRepository carRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    BookingMapper bookingMapper;

    @Autowired
    UserRepository userRepository;


    @PreAuthorize("hasRole('CUSTOMER')")
    public ApiResponse makeABooking(RentACarRequest request,int carIdcar){
        Booking booking = bookingMapper.toBooking(request);
        booking.setCarIdcar(carIdcar);
        Car car = carRepository.findById(carIdcar).orElseThrow(() -> new AppException(ErrorCode.CAR_NOTFOUND));
        int idCarOwner = car.getIdcarowner();
        booking.setCarIdcarowner(idCarOwner);
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long longIdUser = (Long) jwt.getClaims().get("id");
        int idUser = longIdUser.intValue();
        booking.setUserIduser(idUser);
        booking.setStatus(BookingStatus.PENDING_DEPOSIT.getStatus());
        bookingRepository.save(booking);
        return new ApiResponse()
                .builder()
                .result(booking)
                .build();
    }

    public ApiResponse paidDeposid(Integer idbooking){
        Booking booking = bookingRepository.findById(idbooking).orElseThrow(() -> new AppException(ErrorCode.BOOKING_NOTFOUND));
        User user = userRepository.findById(booking.getUserIduser()).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        Car car = carRepository.findById(booking.getCarIdcar()).orElseThrow(() -> new AppException(ErrorCode.CAR_NOTFOUND));
        System.out.println(user.getWallet());
        if(booking.getPaymentmethod().equals(PayMentMethod.WALLET.getName())){
            user.setWallet(user.getWallet() - car.getDeposite());
        }
        booking.setStatus(BookingStatus.PENDING_DEPOSIT.getStatus());
        System.out.println(user.getWallet());
        userRepository.save(user);
        return new ApiResponse()
                .builder()
                .result(booking)
                .build();
    }

    public ApiResponse getCarById(Integer carIdcar){
        Car car = carRepository.findById(carIdcar).orElseThrow(() -> new AppException(ErrorCode.CAR_NOTFOUND));
        return new ApiResponse()
                .builder()
                .result(car)
                .build();
    }

//    public ApiResponse cancelBooking(Integer idbooking){
//        Booking booking = bookingRepository.findById(idbooking).orElseThrow(() -> new AppException(ErrorCode.BOOKING_NOTFOUND));
//        booking.setStatus(BookingStatus.CANCELLED.getStatus());
//        return new ApiResponse()
//                .builder()
//                .result(booking)
//                .build();
//    }
//    public ApiResponse confirmPickUp(Integer idbooking){
//        Booking booking = bookingRepository.findById(idbooking).orElseThrow(() -> new AppException(ErrorCode.BOOKING_NOTFOUND));
//        booking.setStatus(BookingStatus.IN_PROGRESS.getStatus());
//        return new ApiResponse()
//                .builder()
//                .result(booking)
//                .build();
//    }
//    public ApiResponse returnCar(Integer idbooking){
//        Booking booking = bookingRepository.findById(idbooking).orElseThrow(() -> new AppException(ErrorCode.BOOKING_NOTFOUND));
//        booking.setStatus(BookingStatus.PENDING_PAYMENT.getStatus());
//        return new ApiResponse()
//                .builder()
//                .result(booking)
//                .build();
//    }
//
//
//    public ApiResponse returnAndPayCar(Integer idbooking){
//        Booking booking = bookingRepository.findById(idbooking).orElseThrow(() -> new AppException(ErrorCode.BOOKING_NOTFOUND));
//        User user = userRepository.findById(booking.getUserIduser()).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
//        Car car = carRepository.findById(booking.getCarIdcar()).orElseThrow(() -> new AppException(ErrorCode.CAR_NOTFOUND));
//        user.setWallet(user.getWallet() + car.getDeposite());
//        user.setWallet(user.getWallet() - car.getBaseprice());
//        booking.setStatus(BookingStatus.COMPLETE.getStatus());
//        return new ApiResponse()
//                .builder()
//                .result(booking)
//                .build();
//    }
}
