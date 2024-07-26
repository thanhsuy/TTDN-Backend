package com.haui.btl.demo.Controller;

import com.haui.btl.demo.Service.ViewHomePageasCustomerService;
import com.haui.btl.demo.dto.response.ApiResponse;
import com.haui.btl.demo.dto.response.AuthenResponse;
import com.haui.btl.demo.dto.response.FeedbackResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/viewhomeCustomer")
public class ViewHomePageasCustomerController {

    @Autowired
    ViewHomePageasCustomerService viewHomePageasCustomerService;

    @GetMapping("/feedback")
    public ApiResponse<List<FeedbackResponse>> getMyInfo() {
        return  viewHomePageasCustomerService.getFeedbackRes();
    }

    @GetMapping("/auth")
    public ApiResponse<AuthenResponse> authRole(){
        return ApiResponse.<AuthenResponse>builder()
                .result(ViewHomePageasCustomerService.getUserRole())
                .build();
    }
}
