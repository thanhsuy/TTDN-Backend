package com.haui.btl.demo.Controller;

import com.haui.btl.demo.Service.BankTransferService;
import com.haui.btl.demo.Service.RentACarService;
import com.haui.btl.demo.dto.response.ApiResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class BankTransferController {
    BankTransferService bankTransferService;
    RentACarService rentACarService;

    @PostMapping("/createbanktransfer/{idbooking}")
    public ApiResponse paidDeposid(@PathVariable("idbooking") Integer idbooking) throws UnsupportedEncodingException {
        System.out.println(bankTransferService.paidDeposid(idbooking));
        return new ApiResponse()
                .builder()
                .result(bankTransferService.paidDeposid(idbooking))
                .build();
    }
    @GetMapping("/returnurl")
    public ApiResponse returnUrl(@RequestParam(value = "vnp_ResponseCode") String vnp_ResponseCode,
                                 @RequestParam(value = "vnp_OrderInfo") String vnp_OrderInfo) throws UnsupportedEncodingException {

        if(vnp_ResponseCode.equals("00")){
            return rentACarService.paidDeposid(Integer.parseInt(vnp_OrderInfo));
        }
        return new ApiResponse()
                .builder()
                .result(false)
                .build();
    }
}
