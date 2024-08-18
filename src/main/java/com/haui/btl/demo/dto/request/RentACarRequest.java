package com.haui.btl.demo.dto.request;


import com.haui.btl.demo.Validator.CusValidDate;
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
    @CusValidDate(past = false, message = "DATE_INVALID")
    LocalDateTime startdatetime;
    @CusValidDate(past = false, message = "DATE_INVALID")
    LocalDateTime enddatetime;
    String paymentmethod;
}
