package com.haui.btl.demo.Controller;


import com.haui.btl.demo.Service.UserService;
import com.haui.btl.demo.dto.request.ForgotPasswordRequest;
import com.haui.btl.demo.dto.request.UserCreationRequest;
import com.haui.btl.demo.dto.response.ApiResponse;
import com.haui.btl.demo.dto.response.UserRespone;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @CrossOrigin(origins = "http://localhost:3000")
    public ApiResponse createUser(@RequestBody @Valid UserCreationRequest request) {
        return userService.createUser(request);
    }


    @PutMapping("/{userId}/update")
    public ApiResponse updateUser(@PathVariable("userId") Integer userId, @RequestBody @Valid UserCreationRequest request) {
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