package com.haui.btl.demo.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ViewBookingListResponse {
    private Integer idbooking;
    private String bookingno;
    private LocalDateTime startdatetime;
    private LocalDateTime enddatetime;
    private String driversinformation;
    private String paymentmethod;
    private String status;
    private int carIdcar;
    private String carImage;
    private int carIdcarowner;
    private int userIduser;
}
