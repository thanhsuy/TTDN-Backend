package com.example.SpringBootLearning.dto.respone;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

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
