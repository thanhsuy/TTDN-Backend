package com.example.SpringBootLearning.controller;

import com.example.SpringBootLearning.dto.request.AuthenticationRequest;
import com.example.SpringBootLearning.dto.request.IntrospectRequest;
import com.example.SpringBootLearning.dto.respone.ApiReponse;
import com.example.SpringBootLearning.dto.respone.AuthenticationRespone;
import com.example.SpringBootLearning.dto.respone.IntrospectRespone;
import com.example.SpringBootLearning.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.Builder;
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
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    public ApiReponse<AuthenticationRespone> authenticate(@RequestBody AuthenticationRequest authenticationRequest){
        var result = authenticationService.authenticate(authenticationRequest);
        ApiReponse apiReponse = new ApiReponse();
        apiReponse.setResult(result);
        System.out.println(authenticationRequest.getUsername());
        System.out.println(authenticationRequest.getPassword());
        System.out.println("controller sucess");
        return apiReponse;
    }
    @PostMapping("/introspect")
    public ApiReponse<IntrospectRespone> instrospect(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        ApiReponse apiReponse = new ApiReponse();
        apiReponse.setResult(result);
        return apiReponse;
    }
}