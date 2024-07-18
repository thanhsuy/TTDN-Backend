package com.example.SpringBootLearning.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.SpringBootLearning.request.ApiReponse;
import com.example.SpringBootLearning.request.UserCreationRequest;
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
    public ApiReponse getUserById(@PathVariable("userId") String userId) {
        return userService.getUserById(userId);
    }

    @PostMapping("/create")
    public ApiReponse createUser(@RequestBody @Valid UserCreationRequest request) {
        return userService.createUser(request);
    }

    @PutMapping("/{userId}/update")
    public ApiReponse updateUser(
            @PathVariable("userId") String userId, @RequestBody @Valid UserCreationRequest request) {
        return userService.updateUser(userId, request);
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
