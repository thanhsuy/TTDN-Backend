package com.example.SpringBootLearning.dto.request;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RentACarRequest {
    LocalDate startdatetime;
    LocalDate enddatetime;
    String paymentmethod;
}
