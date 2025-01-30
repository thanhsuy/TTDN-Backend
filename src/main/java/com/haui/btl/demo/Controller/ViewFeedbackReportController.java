package com.haui.btl.demo.Controller;

import com.haui.btl.demo.Service.ViewFeedbackReportService;
import com.haui.btl.demo.dto.response.FeedbackResponse;
import com.haui.btl.demo.dto.response.RatingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/viewFeedbackReport")
public class ViewFeedbackReportController {

    @Autowired
    private ViewFeedbackReportService viewFeedbackReportService;

//    @GetMapping("/{userId}")
//    public List<FeedbackResponse> viewFeedbackReport(@PathVariable int userId) {
//        ViewFeedbackReportRequest request = new ViewFeedbackReportRequest();
//        request.setUserId(userId);
//        return viewFeedbackReportService.viewFeedbackReport(request);
//    }

//    @GetMapping
//    public List<FeedbackResponse> getFeedbackReport(@RequestParam int userId) {
//        return viewFeedbackReportService.getFeedbackReport(userId);
//    }

    @GetMapping("/averageRatingByIdCar/{idCar}")
    public RatingResponse getAvgRating(@PathVariable int idCar) {
        return viewFeedbackReportService.getRatingAvg(idCar);
    }

    @GetMapping
    public List<FeedbackResponse> getFeedbackReport() {
        return viewFeedbackReportService.getFeedbackReport();
    }
}