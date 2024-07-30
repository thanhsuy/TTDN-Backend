package com.haui.btl.demo.Service;

import com.haui.btl.demo.Entity.Feedback;
import com.haui.btl.demo.Entity.User;
import com.haui.btl.demo.Mapper.FeedbackMapper;
import com.haui.btl.demo.Repository.FeedbackRepository;
import com.haui.btl.demo.Repository.UserRepository;
import com.haui.btl.demo.dto.request.ViewFeedbackReportRequest;
import com.haui.btl.demo.dto.response.FeedbackResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ViewFeedbackReportService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FeedbackMapper feedbackMapper;

//    public List<FeedbackResponse> viewFeedbackReport(ViewFeedbackReportRequest request) {
//        List<Feedback> feedbacks = feedbackRepository.findAllByBookingUserIduser(request.getUserId());
//
//        return feedbacks.stream()
//                .sorted((f1, f2) -> f2.getDatetime().compareTo(f1.getDatetime()))
//                .map(feedback -> {
//                    FeedbackResponse response = feedbackMapper.toFeedbackResponse(feedback);
//                    Optional<User> user = userRepository.findById(feedback.getBookingUserIduser());
//                    user.ifPresent(response::setUser);
//                    return response;
//                })
//                .collect(Collectors.toList());
//    }

    public List<FeedbackResponse> getFeedbackReport(int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        if (!"CAROWNER".equals(user.getRole())) {
            throw new RuntimeException("User does not have permission to view feedback reports");
        }
        List<Feedback> feedbackList = feedbackRepository.findAllByBookingCarIdcarowner(userId);
        return feedbackList.stream()
                .map(feedbackMapper::toFeedbackResponse)
                .collect(Collectors.toList());
    }
}
