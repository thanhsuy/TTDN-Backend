package com.example.SpringBootLearning.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idtransactions;

    Integer userIduser;
    Float amount;
    String type;
    LocalDateTime datetime;
    String bookingno;
    String carname;
    String note;
}
