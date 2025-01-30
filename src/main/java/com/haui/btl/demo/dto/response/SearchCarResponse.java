package com.haui.btl.demo.dto.response;

import com.haui.btl.demo.Entity.Car;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class SearchCarResponse {
    Car car;
    long bookingNumber;
    double rate;

}
