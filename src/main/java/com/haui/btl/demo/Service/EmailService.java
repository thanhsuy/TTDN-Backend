package com.haui.btl.demo.Service;

import com.haui.btl.demo.Exception.AppException;
import com.haui.btl.demo.Exception.ErrorCode;
import com.haui.btl.demo.Repository.UserRepository;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Builder
public class EmailService {

    @Autowired
    UserRepository userRepository;


    public void postEmail(String from, String to, String subject, String text, String category) throws IOException {
        if(!userRepository.existsByEmail(to)){
            throw new AppException(ErrorCode.EMAIL_NOT_EXISTED);
        }
        MediaType mediaType = MediaType.parse("application/json");
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        RequestBody body = RequestBody.create(mediaType,
                "{\"from\":{\"email\":\"" + from + "\",\"name\":\"Mailtrap Test\"},\"to\":[{\"email\":\"" + to + "\"}],\"subject\":\"" + subject + "\",\"text\":\"" + text + "\",\"category\":\"" + category + "\"}");
        Request request = new Request.Builder()
                .url("https://send.api.mailtrap.io/api/send")
                .method("POST", body)
                .addHeader("Authorization", "Bearer 839142fedcb109c4ff53a500dd1649d0")
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
    }
}
