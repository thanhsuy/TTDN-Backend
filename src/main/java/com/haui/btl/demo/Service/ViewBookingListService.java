package com.haui.btl.demo.Service;

import com.haui.btl.demo.Entity.Booking;
import com.haui.btl.demo.Entity.User;
import com.haui.btl.demo.Repository.BookingRepository;
import com.haui.btl.demo.Repository.CarRepository;
import com.haui.btl.demo.Repository.UserRepository;
import com.haui.btl.demo.dto.response.ViewBookingListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ViewBookingListService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarRepository carRepository;

    public List<ViewBookingListResponse> getBookingsForUser(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent() && "CUSTOMER".equals(userOptional.get().getRole())) {
            int userId = userOptional.get().getIduser();
            List<Booking> bookings = bookingRepository.findByUserIduser(userId);
            return mapBookingsToResponse(bookings);
        } else {
            throw new IllegalArgumentException("User không có quyền truy cập.");
        }
    }

    public List<ViewBookingListResponse> mapBookingsToResponse(List<Booking> bookings) {
        return bookings.stream()
                .map(this::mapToBookingResponseNew)
                .collect(Collectors.toList());
    }

    private ViewBookingListResponse mapToBookingResponseNew(Booking booking) {
        ViewBookingListResponse response = new ViewBookingListResponse();
        response.setIdbooking(booking.getIdbooking());
        response.setBookingno(booking.getBookingno());
        response.setStartdatetime(booking.getStartdatetime());
        response.setEnddatetime(booking.getEnddatetime());
        response.setDriversinformation(booking.getDriversinformation());
        response.setPaymentmethod(booking.getPaymentmethod());
        response.setStatus(booking.getStatus());

        // Lấy carImage từ bảng Car nếu có carIdcar
        if (booking.getCarIdcar() != 0) {
            carRepository.findById(booking.getCarIdcar()).ifPresent(car -> response.setCarImage(car.getImages()));
        }

        return response;
    }

    public ViewBookingListResponse getBookingById(Integer id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        return booking.map(this::mapToBookingResponse).orElse(null);
    }

    private ViewBookingListResponse mapToBookingResponse(Booking booking) {
        ViewBookingListResponse response = new ViewBookingListResponse();
        response.setIdbooking(booking.getIdbooking());
        response.setBookingno(booking.getBookingno());
        response.setStartdatetime(booking.getStartdatetime());
        response.setEnddatetime(booking.getEnddatetime());
        response.setDriversinformation(booking.getDriversinformation());
        response.setPaymentmethod(booking.getPaymentmethod());
        response.setStatus(booking.getStatus());
        response.setCarIdcar(booking.getCarIdcar());
        response.setCarIdcarowner(booking.getCarIdcarowner());
        response.setUserIduser(booking.getUserIduser());
        return response;
    }
}
