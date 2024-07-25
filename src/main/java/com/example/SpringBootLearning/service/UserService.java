package com.example.SpringBootLearning.service;

import com.example.SpringBootLearning.dto.respone.UserRespone;
import com.example.SpringBootLearning.enums.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.SpringBootLearning.entity.User;
import com.example.SpringBootLearning.exception.AppException;
import com.example.SpringBootLearning.exception.ErrorCode;
import com.example.SpringBootLearning.mapper.UserMapper;
import com.example.SpringBootLearning.repository.UserRepository;
import com.example.SpringBootLearning.dto.respone.ApiReponse;
import com.example.SpringBootLearning.dto.request.UserCreationRequest;

import java.util.HashSet;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PreAuthorize("hasRole('ADMIN')")
    public ApiReponse getUsers() {
        ApiReponse apiReponse = new ApiReponse();
        apiReponse.setResult(userRepository.findAll());
        apiReponse.setMessage("Sucess");
        return apiReponse;
    }

    public UserRespone getUserById(String userId) {
        return userMapper.toUserRespone(userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND)));
    }

    @PostAuthorize("returnObject.username == authentication.name")
    public UserRespone getMyInfo(){
        var context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();
        return userMapper.toUserRespone(userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND)));
    }

    public ApiReponse createUser(UserCreationRequest request) {
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        HashSet<String> roles = new HashSet<>();
        roles.add(Roles.USER.name());
        user.setRoles(roles);
        ApiReponse apiReponse = new ApiReponse();
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
        user = userMapper.updateUser(user,request);
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