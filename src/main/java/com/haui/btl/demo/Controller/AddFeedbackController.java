package com.haui.btl.demo.Controller;

import com.haui.btl.demo.Entity.Feedback;
import com.haui.btl.demo.Service.AddFeedbackService;
import com.haui.btl.demo.dto.request.ThucFeedBackRequest;
import com.haui.btl.demo.dto.response.ApiResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/add_feedback")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AddFeedbackController {
    @Autowired
    AddFeedbackService addFeedbackService;
    @PostMapping("/{idBooking}")
    public ApiResponse addFeedback(@PathVariable("idBooking") int idBooking, @RequestBody Feedback feedback){
        return addFeedbackService.creatFeedback(idBooking, feedback);
    }

    @PostMapping("/thucApi/{idBooking}")
    public ApiResponse thucApiaddFeedback(@PathVariable("idBooking") int idBooking, @RequestBody ThucFeedBackRequest feedback){
        return addFeedbackService.thuccreatFeedback(idBooking, feedback);
    }
}
