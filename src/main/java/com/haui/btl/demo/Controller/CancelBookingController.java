package com.haui.btl.demo.Controller;

import com.haui.btl.demo.Service.CancelBookingService;
import com.haui.btl.demo.dto.response.ApiResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
public class CancelBookingController {
    CancelBookingService cancelBookingService;

    @PostMapping("/cancelbooking/{idbooking}")
    public ApiResponse cancelBooking(@PathVariable("idbooking") Integer idbooking){

        return new ApiResponse()
                .builder()
                .result(cancelBookingService.cancelBooking(idbooking))
                .build();
    }
}
