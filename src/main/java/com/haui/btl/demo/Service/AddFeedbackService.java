package com.haui.btl.demo.Service;

import com.haui.btl.demo.Entity.Booking;
import com.haui.btl.demo.Entity.Feedback;
import com.haui.btl.demo.Exception.AppException;
import com.haui.btl.demo.Exception.ErrorCode;
import com.haui.btl.demo.Repository.BookingRepository;
import com.haui.btl.demo.Repository.FeedbackRepository;
import com.haui.btl.demo.Repository.UserRepository;
import com.haui.btl.demo.dto.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddFeedbackService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
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
}
