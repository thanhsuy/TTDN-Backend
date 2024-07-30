package com.haui.btl.demo.Controller;

import com.haui.btl.demo.dto.response.ApiResponse;
import com.haui.btl.demo.Service.ConfirmDepositService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ConfirmDepositController {
    ConfirmDepositService confirmDepositService;

    @PostMapping("/confirmdeposit/{idbooking}")
    public ApiResponse confirmdeposit(@PathVariable("idbooking") Integer idbooking){
        return ApiResponse
                .builder()
                .result(confirmDepositService.confirmDeposit(idbooking))
                .build();
    }
}
