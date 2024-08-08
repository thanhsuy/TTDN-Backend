package com.example.SpringBootLearning.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class Redirect {
    @GetMapping("/redirect")
    public RedirectView redirectView(){
        return new RedirectView("https://phamanhduc.com/tich-hop-vnpay-vao-ung-dung-spring-boot/");
    }
}
