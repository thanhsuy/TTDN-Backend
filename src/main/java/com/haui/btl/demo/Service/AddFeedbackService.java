package com.haui.btl.demo.Service;

import com.haui.btl.demo.Entity.Booking;
import com.haui.btl.demo.Entity.Feedback;
import com.haui.btl.demo.Exception.AppException;
import com.haui.btl.demo.Exception.ErrorCode;
import com.haui.btl.demo.Repository.BookingRepository;
import com.haui.btl.demo.Repository.FeedbackRepository;
import com.haui.btl.demo.Repository.UserRepository;
import com.haui.btl.demo.dto.request.ThucFeedBackRequest;
import com.haui.btl.demo.dto.response.ApiResponse;
import com.haui.btl.demo.dto.response.UserRespone;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Builder
public class AddFeedbackService {
    UserRepository userRepository;

    BookingRepository bookingRepository;

    FeedbackRepository feedbackRepository;

    public ApiResponse<Feedback> creatFeedback(int bookingId, Feedback feedback){
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new AppException(ErrorCode.BOOKING_NOTFOUND));
        feedback.setBookingCarIdcar(booking.getCarIdcar());
        feedback.setBookingCarIdcarowner(booking.getCarIdcarowner());
        feedback.setBookingIdbooking(booking.getIdbooking());
        feedback.setBookingUserIduser(booking.getUserIduser());
        feedbackRepository.save(feedback);
        return ApiResponse.<Feedback>builder()
                .result(feedback)
                .build();
    }

//    @PreAuthorize("hasRole('CUSTOMER')")
    public ApiResponse thuccreatFeedback(int bookingId, ThucFeedBackRequest request){
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new AppException(ErrorCode.BOOKING_NOTFOUND));
        Feedback feedbackCuaThuc = new Feedback();
        feedbackCuaThuc.setBookingIdbooking(bookingId);
        feedbackCuaThuc.setBookingCarIdcar(booking.getCarIdcar());
        feedbackCuaThuc.setBookingUserIduser(booking.getUserIduser());
        feedbackCuaThuc.setBookingCarIdcarowner(booking.getCarIdcarowner());
        feedbackCuaThuc.setContent(request.getContent());
        feedbackCuaThuc.setRate(request.getRate());
        feedbackCuaThuc.setDatetime(LocalDateTime.now());
        feedbackRepository.save(feedbackCuaThuc);
        return ApiResponse.<Feedback>builder()
                .result(feedbackCuaThuc)
                .build();
    }
}
