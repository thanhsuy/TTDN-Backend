package com.haui.btl.demo.Service;


import com.haui.btl.demo.Config.JwtTokenProvider;
import com.haui.btl.demo.Entity.User;
import com.haui.btl.demo.Exception.AppException;
import com.haui.btl.demo.Exception.ErrorCode;
import com.haui.btl.demo.Mapper.UserMapper;
import com.haui.btl.demo.Repository.UserRepository;
import com.haui.btl.demo.dto.request.ForgotPasswordRequest;
import com.haui.btl.demo.dto.request.UserCreationRequest;
import com.haui.btl.demo.dto.response.ApiResponse;
import com.haui.btl.demo.dto.response.UserRespone;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public UserRespone getUserInfoFromToken(String token) {
        String email = jwtTokenProvider.getUsernameFromToken(token);
        return userMapper.toUserRespone(userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse getUsers() {
        ApiResponse apiReponse = new ApiResponse();
        apiReponse.setResult(userRepository.findAll());
        apiReponse.setMessage("Sucess");
        return apiReponse;
    }

    public UserRespone getUserById(Integer userId) {
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

    public ApiResponse updateUser(Integer userId, UserCreationRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        ApiResponse apiResponse = new ApiResponse();

        // Nếu mật khẩu trong yêu cầu khác với mật khẩu hiện tại, mã hóa lại mật khẩu
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        // Cập nhật các trường khác
        user = userMapper.updateUser(user, request);
        userRepository.save(user);
        apiResponse.setResult(user);
        return apiResponse;
    }

    public ApiResponse fotgotPassword(ForgotPasswordRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        if(request.getNewpassword().equals(request.getConfirmpassword()))
        {
            user.setPassword(passwordEncoder.encode(request.getNewpassword()));
        }else throw new AppException(ErrorCode.PASSWORC_NOTEQUAL);

        System.out.println(request.getNewpassword().equals(request.getConfirmpassword()));
        System.out.println(request.getNewpassword());
        System.out.println(request.getConfirmpassword());

        userRepository.save(user);
        ApiResponse apiReponse = new ApiResponse()
                .builder()
                .result(user)
                .build();
        return apiReponse;
    }

    public ApiResponse deleteUser(Integer userId) {
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


    //Quang's source
//    public User updateProfile(Long id, User updatedUser) {
//        User user = userRepository.findById(Math.toIntExact(id)).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
//        user.setName(updatedUser.getName());
//        user.setDateofbirth(updatedUser.getDateofbirth());
//        user.setEmail(updatedUser.getEmail());
//        user.setPhoneno(updatedUser.getPhoneno());
//        user.setNationalidno(updatedUser.getNationalidno());
//        user.setAddress(updatedUser.getAddress());
//        user.setDrivinglicense(updatedUser.getDrivinglicense());
//        user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
//        return userRepository.save(user);
//    }

    public User updateProfile(Integer id, User updatedUser) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            updatedUser.setIduser(id);
//            updatedUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            return userRepository.save(updatedUser);
        } else {
            throw new AppException(ErrorCode.USER_NOTFOUND);
        }
    }

    public User updateProfilePassword(Integer id, String password) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setIduser(id);
            existingUser.setPassword(passwordEncoder.encode(password));
            System.out.println(passwordEncoder.encode(password) + "    "  + password);
            return userRepository.save(existingUser);
        } else {
            throw new AppException(ErrorCode.USER_NOTFOUND);
        }
    }

}