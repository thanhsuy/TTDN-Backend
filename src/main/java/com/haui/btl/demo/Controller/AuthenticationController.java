package com.haui.btl.demo.Controller;


import com.haui.btl.demo.Service.AuthenticationService;
import com.haui.btl.demo.dto.request.AuthenticationRequest;
import com.haui.btl.demo.dto.request.IntrospectRequest;
import com.haui.btl.demo.dto.response.ApiResponse;
import com.haui.btl.demo.dto.response.AuthenticationRespone;
import com.haui.btl.demo.dto.response.IntrospectRespone;
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
        ApiResponse apiReponse = new ApiResponse();
        apiReponse.setResult(result);
        System.out.println(authenticationRequest.getEmail());
        System.out.println(authenticationRequest.getPassword());
        System.out.println("controller sucess");
        return apiReponse;
    }
    @PostMapping("/introspect")
    @CrossOrigin(origins = "http://localhost:3000")
    public ApiResponse<IntrospectRespone> instrospect(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        ApiResponse apiReponse = new ApiResponse();
        apiReponse.setResult(result);
        return apiReponse;
    }
}