package com.haui.btl.demo.Controller;

import com.haui.btl.demo.Service.ViewFeedbackReportService;
import com.haui.btl.demo.dto.request.ViewFeedbackReportRequest;
import com.haui.btl.demo.dto.response.ApiResponse;
import com.haui.btl.demo.dto.response.FeedbackResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public List<FeedbackResponse> getFeedbackReport() {
        return viewFeedbackReportService.getFeedbackReport();
    }

    @GetMapping("/thucApi")
    public ApiResponse getListFeedback(){
        return viewFeedbackReportService.getListFeedback();
    }
}