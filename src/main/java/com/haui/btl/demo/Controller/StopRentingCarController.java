package com.haui.btl.demo.Controller;


import com.haui.btl.demo.Service.StopRentingCarService;
import com.haui.btl.demo.dto.response.ApiResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/stoprentingcar")
public class StopRentingCarController {
    @Autowired
    StopRentingCarService stopRentingCarService;

    @PostMapping("/{idcar}")
    public ApiResponse stopRentingCar(@PathVariable("idcar") Integer idcar){
        return new ApiResponse()
                .builder()
                .result(stopRentingCarService.stopRentingCar(idcar))
                .build();
    }

}
