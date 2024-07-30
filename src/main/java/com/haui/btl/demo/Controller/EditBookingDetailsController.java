package com.haui.btl.demo.Controller;

import com.haui.btl.demo.Service.EditBookingDetailsService;
import com.haui.btl.demo.dto.request.EditBookingDetailsRequest;
import com.haui.btl.demo.dto.response.ViewBookingListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/edit-booking")
public class EditBookingDetailsController {

    @Autowired
    private EditBookingDetailsService editBookingDetailsService;

    @PutMapping("/{id}")
    public ViewBookingListResponse updateBooking(@PathVariable Integer id, @RequestBody EditBookingDetailsRequest bookingRequest) {
        return editBookingDetailsService.updateBooking(id, bookingRequest);
    }
}
