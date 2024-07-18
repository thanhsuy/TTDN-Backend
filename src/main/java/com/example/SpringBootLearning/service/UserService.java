package com.example.SpringBootLearning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SpringBootLearning.entity.User;
import com.example.SpringBootLearning.exception.AppException;
import com.example.SpringBootLearning.exception.ErrorCode;
import com.example.SpringBootLearning.repository.UserRepository;
import com.example.SpringBootLearning.request.ApiReponse;
import com.example.SpringBootLearning.request.UserCreationRequest;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public ApiReponse getUsers() {
        ApiReponse apiReponse = new ApiReponse();
        apiReponse.setResult(userRepository.findAll());
        apiReponse.setMessage("Sucess");
        return apiReponse;
    }

    public ApiReponse getUserById(String userId) {
        ApiReponse apiReponse = new ApiReponse();
        apiReponse.setResult(userRepository.findById(userId));
        return apiReponse;
    }

    public ApiReponse createUser(UserCreationRequest request) {
        User user = new User();
        ApiReponse apiReponse = new ApiReponse();
        user.setUsername(request.getUsername());
        user.setAddress(request.getAddress());
        user.setDob(request.getDob());
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setPassword(request.getPassword());
        user.setPhone(request.getPhone());
        apiReponse.setMessage("Sucess");
        apiReponse.setResult(user);
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        userRepository.save(user);
        return apiReponse;
    }

    public ApiReponse updateUser(String userId, UserCreationRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        ApiReponse apiReponse = new ApiReponse();
        user.setUsername(request.getUsername());
        user.setAddress(request.getAddress());
        user.setDob(request.getDob());
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setPassword(request.getPassword());
        user.setPhone(request.getPhone());
        userRepository.save(user);
        apiReponse.setResult(userRepository.save(user));
        return apiReponse;
    }

    public ApiReponse deleteUser(String userId) {
        userRepository.deleteById(userId);
        ApiReponse apiReponse = new ApiReponse();
        apiReponse.setMessage("Xóa thành công");
        return apiReponse;
    }

    public ApiReponse deleteAllUser() {
        userRepository.deleteAll();
        ApiReponse apiReponse = new ApiReponse();
        apiReponse.setMessage("Xóa thành công");
        return apiReponse;
    }
}
