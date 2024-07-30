package com.haui.btl.demo.dto.request;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

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
