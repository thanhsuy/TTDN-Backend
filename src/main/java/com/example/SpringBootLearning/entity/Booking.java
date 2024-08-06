package com.example.SpringBootLearning.entity;

import java.time.LocalDate;

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
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idbooking;

    String bookingno;
    LocalDate startdatetime;
    LocalDate enddatetime;
    String driversinformation;
    String paymentmethod;
    String status;

    int carIdcar;

    int carIdcarowner;

    int userIduser;
}
