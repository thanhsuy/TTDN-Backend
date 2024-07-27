package com.example.SpringBootLearning.controller;

import com.example.SpringBootLearning.dto.request.AuthenticationRequest;
import com.example.SpringBootLearning.dto.request.IntrospectRequest;
import com.example.SpringBootLearning.dto.respone.ApiResponse;
import com.example.SpringBootLearning.dto.respone.AuthenticationRespone;
import com.example.SpringBootLearning.dto.respone.IntrospectRespone;
import com.example.SpringBootLearning.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AuthenticationController {
    AuthenticationService authenticationService;
    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:3000")
    public ApiResponse<AuthenticationRespone> authenticate(@RequestBody AuthenticationRequest authenticationRequest){
        var result = authenticationService.authenticate(authenticationRequest);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(result);
        return apiResponse;
    }
    @PostMapping("/introspect")
    public ApiResponse<IntrospectRespone> instrospect(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(result);
        return apiResponse;
    }
}