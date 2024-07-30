package com.haui.btl.demo.Controller;

import com.haui.btl.demo.Service.ViewBookingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.haui.btl.demo.dto.response.ViewBookingListResponse;
import java.util.List;

@RestController
@RequestMapping("/view-bookings")
public class ViewBookingListController {

    @Autowired
    private ViewBookingListService viewBookingListService;

    @GetMapping
    public List<ViewBookingListResponse> getBookingsForCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return viewBookingListService.getBookingsForUser(email);
    }

    @GetMapping("/{id}")
    public ViewBookingListResponse getBookingById(@PathVariable Integer id) {
        return viewBookingListService.getBookingById(id);
    }
}
