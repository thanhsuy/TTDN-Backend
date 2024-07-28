package com.example.SpringBootLearning.service;

import com.example.SpringBootLearning.dto.request.ForgotPasswordRequest;
import com.example.SpringBootLearning.dto.respone.UserRespone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import com.example.SpringBootLearning.entity.User;
import com.example.SpringBootLearning.exception.AppException;
import com.example.SpringBootLearning.exception.ErrorCode;
import com.example.SpringBootLearning.mapper.UserMapper;
import com.example.SpringBootLearning.repository.UserRepository;
import com.example.SpringBootLearning.dto.respone.ApiResponse;
import com.example.SpringBootLearning.dto.request.UserCreationRequest;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse getUsers() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(userRepository.findAll());
        apiResponse.setMessage("Sucess");
        return apiResponse;
    }

    public UserRespone getUserById(String userId) {
        return userMapper.toUserRespone(userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND)));
    }

    @PostAuthorize("returnObject.email == authentication.name")
    public UserRespone getMyInfo(){
        var context = SecurityContextHolder.getContext();
        String email = context.getAuthentication().getName();

        log.info("USERNAME: {}",context.getAuthentication().getName());
        log.info("ROLE {}", context.getAuthentication().getAuthorities());
        return userMapper.toUserRespone(userRepository.findByEmail(email).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND)));
    }

    public ApiResponse createUser(UserCreationRequest request) {
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        userRepository.save(user);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(user);
        return apiResponse;
    }

    public ApiResponse updateUser(String userId, UserCreationRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        ApiResponse apiResponse = new ApiResponse();
        user = userMapper.updateUser(user,request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        apiResponse.setResult(userRepository.save(user));
        return apiResponse;
    }

    public ApiResponse fotgotPassword(ForgotPasswordRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        if(request.getNewpassword().equals(request.getConfirmpassword()) && request.getNewpassword().equals(request.getConfirmpassword()))
        {
            user.setPassword(passwordEncoder.encode(request.getNewpassword()));
        }else throw new AppException(ErrorCode.PASSWORC_NOTEQUAL);

        System.out.println(request.getNewpassword().equals(request.getConfirmpassword()) && request.getNewpassword().equals(request.getConfirmpassword()));
        System.out.println(request.getNewpassword());
        System.out.println(request.getConfirmpassword());

        userRepository.save(user);
        ApiResponse apiResponse = new ApiResponse()
                .builder()
                .result(user)
                .build();
        return apiResponse;
    }

    public ApiResponse deleteUser(String userId) {
        userRepository.deleteById(userId);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Xóa thành công");
        return apiResponse;
    }

    public ApiResponse deleteAllUser() {
        userRepository.deleteAll();
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Xóa thành công");
        return apiResponse;
    }
}