package com.example.SpringBootLearning.dto.respone;

import java.time.LocalDate;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserRespone {
    int iduser;
    String name;
    LocalDate dateofbirth;
    int nationalidno;
    String phoneno;
    String email;
    String address;
    String drivinglicense;
    String password;
    String role;
    float wallet;
}
