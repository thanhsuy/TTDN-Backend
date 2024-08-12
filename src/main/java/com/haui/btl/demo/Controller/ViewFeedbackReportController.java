package com.haui.btl.demo.Controller;


import com.haui.btl.demo.Entity.User;
import com.haui.btl.demo.Exception.AppException;
import com.haui.btl.demo.Exception.ErrorCode;
import com.haui.btl.demo.Repository.UserRepository;
import com.haui.btl.demo.Service.ViewFeedbackReportService;
import com.haui.btl.demo.dto.request.ViewFeedbackReportRequest;
import com.haui.btl.demo.dto.response.FeedbackResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/viewFeedbackReport")
public class ViewFeedbackReportController {

    @Autowired
    private ViewFeedbackReportService viewFeedbackReportService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<FeedbackResponse> getFeedbackReport(@RequestParam(required = false) Integer rate) {
        return viewFeedbackReportService.getFeedbackReport(rate);
    }

    @GetMapping("/averageRating")
    public double getAverageRating() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));

        if (!"CAROWNER".equals(user.getRole())) {
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }

        return viewFeedbackReportService.getAverageRating(user.getIduser());
    }

}
