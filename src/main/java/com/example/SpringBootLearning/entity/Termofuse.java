package com.example.SpringBootLearning.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@IdClass(TermofuseId.class)
public class Termofuse {
    @Id
    @Column(nullable = false)
    int idCar;

    @Id
    @Column(nullable = false, length = 100)
    String nameTerms;
}