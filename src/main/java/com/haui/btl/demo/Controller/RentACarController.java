package com.haui.btl.demo.Controller;

import com.haui.btl.demo.dto.request.RentACarRequest;
import com.haui.btl.demo.dto.response.ApiResponse;
import com.haui.btl.demo.Service.RentACarService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class RentACarController {
    
    @Autowired
    RentACarService rentACarService;

    @PostMapping("/makeABooking/{carIdcar}")
    public ApiResponse makeABooking(@RequestBody RentACarRequest request, @PathVariable("carIdcar") int carIdcar){
        return ApiResponse
                .builder()
                .result(rentACarService.makeABooking(request,carIdcar))
                .build();
    }
    @PostMapping("/paidDeposid/{idbooking}")
    public ApiResponse paidDeposid(@PathVariable("idbooking") Integer idbooking){
        return ApiResponse
                .builder()
                .result(rentACarService.paidDeposid(idbooking))
                .build();
    }

    @GetMapping("/getcar/{carIdcar}")
    public ApiResponse getCarById(@PathVariable("carIdcar") Integer carIdcar){
        return ApiResponse
                .builder()
                .result(rentACarService.getCarById(carIdcar))
                .build();
    }
}
