package com.example.SpringBootLearning.dto.request;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
