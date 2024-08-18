package com.haui.btl.demo.Controller;

import com.haui.btl.demo.Service.EmailService;
import com.haui.btl.demo.dto.request.EmailRequest;
import com.haui.btl.demo.dto.response.ApiResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class EmailController {
    @Autowired
    EmailService emailService;

    @PostMapping("/resetpassword")
    public ApiResponse resetPassword(@RequestBody EmailRequest request) throws IOException {
        emailService.postEmail("mailtrap@demomailtrap.com", request.getEmail(), "Doi mat khau", request.getUrl(), "Integration Test");
        return new ApiResponse().builder().result("Post email success").build();
    }
}
