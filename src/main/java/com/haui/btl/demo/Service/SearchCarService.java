package com.haui.btl.demo.Service;

import com.haui.btl.demo.Entity.Booking;
import com.haui.btl.demo.Entity.Car;
import com.haui.btl.demo.Entity.Feedback;
import com.haui.btl.demo.Repository.BookingRepository;
import com.haui.btl.demo.Repository.CarRepository;
import com.haui.btl.demo.Repository.FeedbackRepository;
import com.haui.btl.demo.dto.request.SearchCarRequest;
import com.haui.btl.demo.dto.response.ApiResponse;
import com.haui.btl.demo.dto.response.SearchCarResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

@Service
public class SearchCarService {
    @Autowired
    CarRepository carRepository;

    @Autowired
    FeedbackRepository feedbackRepository;

    @Autowired
    BookingRepository bookingRepository;

    public double calculateAverageRateForCar(int bookingCarIdcar) {
        List<Feedback> feedbacks = feedbackRepository.findAll();

        OptionalDouble averageRate = feedbacks.stream()
                .filter(feedback -> feedback.getBookingCarIdcar() == bookingCarIdcar)
                .mapToInt(Feedback::getRate)
                .average();

        return averageRate.isPresent() ? averageRate.getAsDouble() : 0.0;
    }

    public long countBookingsForCar(int carIdcar) {
        List<Booking> allBookings = bookingRepository.findAll();

        // Lọc và đếm các booking phù hợp với tiêu chí

        return allBookings.stream()
                .filter(booking -> booking.getCarIdcar() == carIdcar)
                .filter(booking -> booking.getEnddatetime().isBefore(LocalDateTime.now()))
                .filter(booking -> !booking.getStatus().equalsIgnoreCase("Cancelled"))
                .count();
    }

    public ApiResponse<List<SearchCarResponse>> getListCar(SearchCarRequest searchCarRequest){
        List<Car> allCars = carRepository.findAll();
        ApiResponse<List<SearchCarResponse>> apiResponse = new ApiResponse<>();
        List<Car> carListSearch = allCars.stream()
                                  .filter(car -> car.getAddress().contains(searchCarRequest.getAddress()) && "Available".equalsIgnoreCase(car.getStatus()))
                                  .toList();
        List<SearchCarResponse> searchCarResponseList = new ArrayList<>();
        for (Car i : carListSearch){
            SearchCarResponse searchCarResponse = new SearchCarResponse();
            searchCarResponse.setCar(i);
            searchCarResponse.setRate(calculateAverageRateForCar(i.getIdcar()));
            searchCarResponse.setBookingNumber(countBookingsForCar(i.getIdcar()));
        }
        apiResponse.setResult(searchCarResponseList);
        return apiResponse;
    }
}
