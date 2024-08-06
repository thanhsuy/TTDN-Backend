package com.example.SpringBootLearning.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class User {
    @Id
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
