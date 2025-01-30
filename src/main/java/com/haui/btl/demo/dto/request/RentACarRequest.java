package com.haui.btl.demo.dto.request;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RentACarRequest {
    LocalDateTime startdatetime;
    LocalDateTime enddatetime;
    String paymentmethod;
}
