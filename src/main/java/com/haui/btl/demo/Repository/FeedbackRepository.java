package com.haui.btl.demo.Repository;

import com.haui.btl.demo.Entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
    List<Feedback> findAllByBookingCarIdcarowner(int carOwnerId);

    @Query("SELECT AVG(f.rate) FROM Feedback f WHERE f.bookingCarIdcar = :idCar")
    Optional<Float> selectAvgRatingCar(@Param("idCar") int idCar);
}
