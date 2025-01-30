package com.haui.btl.demo.Controller;

import com.haui.btl.demo.Service.RentACarService;
import com.haui.btl.demo.dto.request.RentACarRequest;
import com.haui.btl.demo.dto.response.ApiResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class RentACarController {
    
    @Autowired
    RentACarService rentACarService;

    @PostMapping("/makeABooking/{carIdcar}")
    public ApiResponse makeABooking(@RequestBody RentACarRequest request, @PathVariable("carIdcar") int carIdcar) {
        return rentACarService.makeABooking(request, carIdcar);
    }

    @PostMapping("/paidDeposid/{idbooking}")
    public ApiResponse paidDeposid(@PathVariable("idbooking") Integer idbooking) throws UnsupportedEncodingException {
        return rentACarService.paidDeposid(idbooking);
    }

    @GetMapping("/getcar/{carIdcar}")
    public ApiResponse getCarById(@PathVariable("carIdcar") Integer carIdcar) {
        return rentACarService.getCarById(carIdcar);
    }

    @GetMapping("/getlistcar")
    public ApiResponse getListCar() {
        return rentACarService.getListCar();
    }

    @GetMapping("/getCarBooking/{carIdcar}")
    public ApiResponse getCarBooking(@PathVariable("carIdcar") int carIdcar){
        return rentACarService.getBooking(carIdcar);
    }
}
