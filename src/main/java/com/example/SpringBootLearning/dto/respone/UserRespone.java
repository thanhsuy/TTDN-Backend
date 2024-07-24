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
    String id;
    String username;
    String address;
    LocalDate dob;
    String firstname;
    String lastname;
    String phone;
    Set<String> roles;
}
