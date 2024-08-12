package com.haui.btl.demo.Controller;

import com.haui.btl.demo.Service.ViewWalletService;
import com.haui.btl.demo.dto.request.TopUpRequest;
import com.haui.btl.demo.dto.request.ViewWalletRequest;
import com.haui.btl.demo.dto.request.WithdrawRequest;
import com.haui.btl.demo.dto.response.ViewWalletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/viewWallet")
public class ViewWalletController {

    @Autowired
    private ViewWalletService viewWalletService;

//    @GetMapping("/{userId}")
//    public ViewWalletResponse viewWallet(@PathVariable int userId) {
//        ViewWalletRequest request = new ViewWalletRequest();
//        request.setUserId(userId);
//        return viewWalletService.viewWallet(request);
//    }

    @GetMapping
    public ViewWalletResponse viewWallet() {
        return viewWalletService.viewWallet();
    }

    @PostMapping("/topup")
    public ViewWalletResponse topUpWallet(@RequestBody TopUpRequest topUpRequest) {
        return viewWalletService.topUpWallet(topUpRequest);
    }

    @PostMapping("/withdraw")
    public ViewWalletResponse withdrawFromWallet(@RequestBody WithdrawRequest withdrawRequest) {
        return viewWalletService.withdrawFromWallet(withdrawRequest);
    }
}