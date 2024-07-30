package com.haui.btl.demo.Service;


import com.haui.btl.demo.Entity.User;
import com.haui.btl.demo.Exception.AppException;
import com.haui.btl.demo.Exception.ErrorCode;
import com.haui.btl.demo.Mapper.UserMapper;
import com.haui.btl.demo.Repository.UserRepository;
import com.haui.btl.demo.dto.request.ForgotPasswordRequest;
import com.haui.btl.demo.dto.request.UserCreationRequest;
import com.haui.btl.demo.dto.response.ApiResponse;
import com.haui.btl.demo.dto.response.UserRespone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse getUsers() {
        ApiResponse apiReponse = new ApiResponse();
        apiReponse.setResult(userRepository.findAll());
        apiReponse.setMessage("Sucess");
        return apiReponse;
    }

    public UserRespone getUserById(String userId) {
        return userMapper.toUserRespone(userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND)));
    }

    @PostAuthorize("returnObject.email == authentication.name")
    public UserRespone getMyInfo(){
        var context = SecurityContextHolder.getContext();
        String email = context.getAuthentication().getName();
        return userMapper.toUserRespone(userRepository.findByEmail(email).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND)));
    }

    public ApiResponse createUser(UserCreationRequest request) {
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        userRepository.save(user);
        ApiResponse apiReponse = new ApiResponse();
        apiReponse.setResult(user);
        return apiReponse;
    }

    public ApiResponse updateUser(String userId, UserCreationRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        ApiResponse apiReponse = new ApiResponse();
        user = userMapper.updateUser(user,request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        apiReponse.setResult(userRepository.save(user));
        return apiReponse;
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
        ApiResponse apiReponse = new ApiResponse()
                .builder()
                .result(user)
                .build();
        return apiReponse;
    }

    public ApiResponse deleteUser(String userId) {
        userRepository.deleteById(userId);
        ApiResponse apiReponse = new ApiResponse();
        apiReponse.setMessage("Xóa thành công");
        return apiReponse;
    }

    public ApiResponse deleteAllUser() {
        userRepository.deleteAll();
        ApiResponse apiReponse = new ApiResponse();
        apiReponse.setMessage("Xóa thành công");
        return apiReponse;
    }

    public User updateProfile(Long id, User updatedUser) {
        User user = userRepository.findById(String.valueOf(id)).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        updatedUser.setIduser(user.getIduser());
        updatedUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        return userRepository.save(updatedUser);
    }
}