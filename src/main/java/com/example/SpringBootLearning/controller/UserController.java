package com.example.SpringBootLearning.controller;

import com.example.SpringBootLearning.dto.request.ForgotPasswordRequest;
import com.example.SpringBootLearning.dto.respone.UserRespone;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.example.SpringBootLearning.dto.respone.ApiReponse;
import com.example.SpringBootLearning.dto.request.UserCreationRequest;
import com.example.SpringBootLearning.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping()
    public ApiReponse getUser() {
        return userService.getUsers();
    }

    @GetMapping("{userId}")
    public ApiReponse<UserRespone> getUserById(@PathVariable("userId") String userId) {
        return ApiReponse.<UserRespone>builder()
                .result(userService.getUserById(userId))
                .build();
    }

    @GetMapping("/myInfo")
    public ApiReponse<UserRespone> getMyInfo() {
        return ApiReponse.<UserRespone>builder()
                .result(userService.getMyInfo())
                .build();
    }

    @PostMapping("/create")
    @CrossOrigin(origins = "http://localhost:3000")
    public ApiReponse createUser(@RequestBody @Valid UserCreationRequest request) {
        return userService.createUser(request);
    }


    @PutMapping("/{userId}/update")
    public ApiReponse updateUser(@PathVariable("userId") String userId, @RequestBody @Valid UserCreationRequest request) {
        return userService.updateUser(userId, request);
    }

    @PutMapping("/forgot")
    public ApiReponse forgotPassword(@RequestBody @Valid ForgotPasswordRequest request) {
        return userService.fotgotPassword(request);
    }


    @DeleteMapping("/{userId}/delete")
    public ApiReponse deleteUser(@PathVariable("userId") String userId) {
        return userService.deleteUser(userId);
    }

    @DeleteMapping("deleteAll")
    public ApiReponse deleteAllUser() {
        return userService.deleteAllUser();
    }
}