package com.haui.btl.demo.Controller;


import com.haui.btl.demo.Entity.User;
import com.haui.btl.demo.Service.UserService;
import com.haui.btl.demo.Service.ViewHomePageasCustomerService;
import com.haui.btl.demo.dto.response.ApiResponse;
import com.haui.btl.demo.dto.response.AuthenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/editProfile")
public class EditProfileController {
    @Autowired
    private UserService userService;

    @PutMapping("/{id}")
    public ApiResponse<User> updateUser(@PathVariable Integer id, @RequestBody User user) {
        ApiResponse<User> apiReponse = new ApiResponse<>();
        apiReponse.setResult(userService.updateProfile(id, user));
        return apiReponse;
    }

    @GetMapping("/getId")
    public ApiResponse authRole(){
        int id = userService.getMyInfo().getIduser();
        return ApiResponse.builder()
                .result(id)
                .build();
    }
}
