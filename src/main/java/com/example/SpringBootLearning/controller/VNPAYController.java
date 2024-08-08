package com.example.SpringBootLearning.controller;

import com.example.SpringBootLearning.configuration.VNPAYConfig;
import com.example.SpringBootLearning.dto.request.RentACarRequest;
import com.example.SpringBootLearning.dto.respone.ApiResponse;
import com.example.SpringBootLearning.service.VNPAYService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class VNPAYController {
    @Autowired
    private VNPAYService vnpayService;

//    @PostMapping("/create_payment")
//    public ApiResponse createPayment() throws UnsupportedEncodingException {
//        return new ApiResponse().builder().result(vnpayService.createPayment()).build();
//    }

//    @GetMapping("/returnurl")
//    public ApiResponse returnUrl(@RequestParam(value = "vnp_ResponseCode") String vnp_ResponseCode) {
//        System.out.println(vnp_ResponseCode);
//        if(vnp_ResponseCode.equals("00")){
//            return new ApiResponse()
//                    .builder()
//                    .result(true)
//                    .build();
//        }
//        return new ApiResponse()
//                .builder()
//                .result(false)
//                .build();
//    }
}
