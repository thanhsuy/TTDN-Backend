package com.example.SpringBootLearning.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.SpringBootLearning.dto.request.ForgotPasswordRequest;
import com.example.SpringBootLearning.dto.request.UserCreationRequest;
import com.example.SpringBootLearning.dto.respone.ApiResponse;
import com.example.SpringBootLearning.dto.respone.UserRespone;
import com.example.SpringBootLearning.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping()
    public ApiResponse getUser() {
        return userService.getUsers();
    }

    @GetMapping("{userId}")
    public ApiResponse<UserRespone> getUserById(@PathVariable("userId") Integer userId) {
        return ApiResponse.<UserRespone>builder()
                .result(userService.getUserById(userId))
                .build();
    }

    @GetMapping("/myInfo")
    public ApiResponse<UserRespone> getMyInfo() {
        return ApiResponse.<UserRespone>builder()
                .result(userService.getMyInfo())
                .build();
    }

    @PostMapping("/create")
    public ApiResponse createUser(@RequestBody @Valid UserCreationRequest request) {
        return userService.createUser(request);
    }

    @PutMapping("/{userId}/update")
    public ApiResponse updateUser(
            @PathVariable("userId") Integer userId, @RequestBody @Valid UserCreationRequest request) {
        return userService.updateUser(userId, request);
    }

    @PutMapping("/forgot")
    public ApiResponse forgotPassword(@RequestBody @Valid ForgotPasswordRequest request) {
        return userService.fotgotPassword(request);
    }

    @DeleteMapping("/{userId}/delete")
    public ApiResponse deleteUser(@PathVariable("userId") Integer userId) {
        return userService.deleteUser(userId);
    }

    @DeleteMapping("deleteAll")
    public ApiResponse deleteAllUser() {
        return userService.deleteAllUser();
    }
}
