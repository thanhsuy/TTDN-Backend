package com.haui.btl.demo.Controller;

import com.haui.btl.demo.Service.ReturnCarService;
import com.haui.btl.demo.dto.response.ApiResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/returncar")
public class ReturnCarController {
    ReturnCarService returnCarService;

    @PostMapping("/{idbooking}")
    public ApiResponse returnCar(@PathVariable("idbooking") Integer idbooking){
        return new ApiResponse()
                .builder()
                .result(returnCarService.returnCar(idbooking))
                .build();
    }
}
