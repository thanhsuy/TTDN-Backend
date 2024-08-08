package com.example.SpringBootLearning.controller;

import com.example.SpringBootLearning.Gmail.GmailQuickstart;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
public class GmailController {
    @Autowired
    private GmailQuickstart gmailQuickstart;

    @PostMapping("/send_mail")
    public void sendMail() throws MessagingException, GeneralSecurityException, IOException {
        gmailQuickstart.sendMail("VANTHUC TEST MAIL", "XIN CHAO");
    }
}
