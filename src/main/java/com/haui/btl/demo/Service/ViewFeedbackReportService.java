package com.haui.btl.demo.Service;

import com.haui.btl.demo.Entity.Feedback;
import com.haui.btl.demo.Entity.User;
import com.haui.btl.demo.Exception.AppException;
import com.haui.btl.demo.Exception.ErrorCode;
import com.haui.btl.demo.Mapper.FeedbackMapper;
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

    public List<FeedbackResponse> getFeedbackReport() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));

        if (!"CAROWNER".equals(user.getRole())) {
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }

        List<Feedback> feedbackList = feedbackRepository.findAllByBookingCarIdcarowner(user.getIduser());
        return feedbackList.stream()
                .map(feedbackMapper::toFeedbackResponse)
                .collect(Collectors.toList());
    }
}