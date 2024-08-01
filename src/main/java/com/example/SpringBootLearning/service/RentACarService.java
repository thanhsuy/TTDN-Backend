package com.example.SpringBootLearning.service;

import com.example.SpringBootLearning.dto.request.RentACarRequest;
import com.example.SpringBootLearning.dto.respone.ApiResponse;
import com.example.SpringBootLearning.entity.Booking;
import com.example.SpringBootLearning.entity.Car;
import com.example.SpringBootLearning.entity.User;
import com.example.SpringBootLearning.enums.BookingStatus;
import com.example.SpringBootLearning.enums.PayMentMethod;
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
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Builder
public class RentACarService {
    CarRepository carRepository;
    BookingRepository bookingRepository;
    BookingMapper bookingMapper;
    UserRepository userRepository;


    @PreAuthorize("hasRole('CUSTOMER')")
    public ApiResponse makeABooking(RentACarRequest request, int carIdcar){
        Booking booking = bookingMapper.toBooking(request);
        booking.setCarIdcar(carIdcar);
        Car car = carRepository.findById(carIdcar).orElseThrow(() -> new AppException(ErrorCode.CAR_NOTFOUND));
        int idCarOwner = car.getIdcarowner();
        if(car.getStatus().equals("Available"))
        {
            booking.setCarIdcarowner(idCarOwner);
            Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Long longIdUser = (Long) jwt.getClaims().get("id");
            int idUser = longIdUser.intValue();
            booking.setUserIduser(idUser);
            booking.setStatus(BookingStatus.PENDING_DEPOSIT.getStatus());
            bookingRepository.save(booking);
        }else throw new AppException(ErrorCode.CAR_NOT_AVAILABLE);
        return new ApiResponse()
                .builder()
                .result(booking)
                .build();
    }

    public ApiResponse paidDeposid(Integer idbooking){
        Booking booking = bookingRepository.findById(idbooking).orElseThrow(() -> new AppException(ErrorCode.BOOKING_NOTFOUND));
        User user = userRepository.findById(booking.getUserIduser()).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        Car car = carRepository.findById(booking.getCarIdcar()).orElseThrow(() -> new AppException(ErrorCode.CAR_NOTFOUND));
        User carowner = userRepository.findById(booking.getCarIdcarowner()).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));

        System.out.println(user.getWallet());
        if(booking.getStatus().equals(BookingStatus.PENDING_DEPOSIT.getStatus()))
        {
            if(booking.getPaymentmethod().equals(PayMentMethod.WALLET.getName())){
                user.setWallet(user.getWallet() - car.getDeposite());
                booking.setStatus(BookingStatus.CONFIRMRED.getStatus());
                carowner.setWallet(carowner.getWallet() + car.getDeposite());
            } else {
                booking.setStatus(BookingStatus.PENDING_DEPOSIT.getStatus());
            }
        }

        userRepository.save(user);
        userRepository.save(carowner);
        bookingRepository.save(booking);
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

    public ApiResponse getListCar(){
        return new ApiResponse<>().builder()
                .result(carRepository.findAll()).build();
    }
}
