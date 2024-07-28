package com.haui.btl.demo.Service;

import com.haui.btl.demo.Entity.Feedback;
import com.haui.btl.demo.Entity.User;
import com.haui.btl.demo.Exception.AppException;
import com.haui.btl.demo.Exception.ErrorCode;
import com.haui.btl.demo.Handler.Sorted.FeedbackSorter;
import com.haui.btl.demo.Mapper.FeedbackMapper;
import com.haui.btl.demo.Repository.CarRepository;
import com.haui.btl.demo.Repository.FeedbackRepository;
import com.haui.btl.demo.Repository.UserRepository;
import com.haui.btl.demo.dto.response.ApiResponse;
import com.haui.btl.demo.dto.response.AuthenResponse;
import com.haui.btl.demo.dto.response.FeedbackResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ViewHomePageasCustomerService {
    @Autowired
    FeedbackRepository feedbackRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FeedbackMapper feedbackMapper;

    @Autowired
    CarRepository carRepository;

    public List<Feedback> getTopFeedBack(){
        List<Feedback> allFeedback = feedbackRepository.findAll();
        List<Feedback> sortedFeedback = FeedbackSorter.sortFeedback(allFeedback);
        return sortedFeedback.size() <= 5 ? sortedFeedback : sortedFeedback.subList(0, 4);
    }

    public ApiResponse<List<FeedbackResponse>> getFeedbackRes(){
        List<Feedback> feedbackList = getTopFeedBack();
        List<FeedbackResponse> feedbackResponseList = new ArrayList<>();
        for (Feedback i : feedbackList){
            User user = userRepository.findById(i.getBookingUserIduser()+"").orElse(null);
            FeedbackResponse feedbackResponse = feedbackMapper.toFeedbackResponse(i);
            feedbackResponse.setUser(user);
            feedbackResponseList.add(feedbackResponse);
        }
        ApiResponse<List<FeedbackResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(feedbackResponseList);
        return apiResponse;
    }

    public ApiResponse<List<Feedback>> getFeedBack(){
        List<Feedback> feedbackList = getTopFeedBack();
        ApiResponse<List<Feedback>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(feedbackList);
        return apiResponse;
    }

    @PostAuthorize("returnObject.email == authentication.name")
    public AuthenResponse getUserRole(){
        var context = SecurityContextHolder.getContext();
        String email = context.getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        return new AuthenResponse(user.getRole());
    }
    public ApiResponse<List<Object[]>> getTop5CitiesWithMostCars() {
        ApiResponse<List<Object[]>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(carRepository.findTop5CitiesWithMostCars());
        return apiResponse;
    }

}
