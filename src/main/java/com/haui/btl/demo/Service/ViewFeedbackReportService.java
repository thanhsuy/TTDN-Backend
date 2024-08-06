package com.haui.btl.demo.Service;

import com.haui.btl.demo.Entity.Feedback;
import com.haui.btl.demo.Entity.User;
import com.haui.btl.demo.Exception.AppException;
import com.haui.btl.demo.Exception.ErrorCode;
import com.haui.btl.demo.Mapper.FeedbackMapper;
import com.haui.btl.demo.Repository.BookingRepository;
import com.haui.btl.demo.Repository.CarRepository;
import com.haui.btl.demo.Repository.FeedbackRepository;
import com.haui.btl.demo.Repository.UserRepository;
import com.haui.btl.demo.dto.response.FeedbackResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ViewFeedbackReportService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FeedbackMapper feedbackMapper;

    @Autowired
    private CarRepository carRepository; // New field

    @Autowired
    private BookingRepository bookingRepository; // New field

//    public List<FeedbackResponse> getFeedbackReport() {
//        var authentication = SecurityContextHolder.getContext().getAuthentication();
//        String email = authentication.getName();
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
//
//        if (!"CAROWNER".equals(user.getRole())) {
//            throw new AppException(ErrorCode.UNAUTHORIZED);
//        }
//
//        List<Feedback> feedbackList = feedbackRepository.findAllByBookingCarIdcarowner(user.getIduser());
//        return feedbackList.stream()
//                .map(feedbackMapper::toFeedbackResponse)
//                .collect(Collectors.toList());
//    }

    public List<FeedbackResponse> getFeedbackReport(Integer rate) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));

        if (!"CAROWNER".equals(user.getRole())) {
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }

        List<Feedback> feedbackList;
        if (rate == null) {
            feedbackList = feedbackRepository.findAllByBookingCarIdcarowner(user.getIduser());
        } else {
            feedbackList = feedbackRepository.findAllByBookingCarIdcarownerAndRate(user.getIduser(), rate);
        }

        return feedbackList.stream()
                .map(feedback -> {
                    var response = feedbackMapper.toFeedbackResponse(feedback);
                    var car = carRepository.findById(feedback.getBookingCarIdcar()).orElseThrow(() -> new AppException(ErrorCode.CAR_NOTFOUND));
                    var booking = bookingRepository.findById(feedback.getBookingIdbooking()).orElseThrow(() -> new AppException(ErrorCode.BOOKING_NOTFOUND));
                    response.setCarName(car.getName());
                    response.setCarModel(car.getModel());
                    response.setBookingStartDate(booking.getStartdatetime().toString());
                    response.setBookingEndDate(booking.getEnddatetime().toString());
                    return response;
                })
                .sorted((a, b) -> b.getDatetime().compareTo(a.getDatetime())) // Sort from newest to oldest
                .collect(Collectors.toList());
    }

    public double getAverageRating(Integer carOwnerId) {
        List<Feedback> feedbackList = feedbackRepository.findAllByBookingCarIdcarowner(carOwnerId);
        double averageRating = feedbackList.stream()
                .mapToDouble(Feedback::getRate)
                .average()
                .orElse(0.0);
        return Math.round(averageRating * 100.0) / 100.0; // Round to 2 decimal places
    }


}
