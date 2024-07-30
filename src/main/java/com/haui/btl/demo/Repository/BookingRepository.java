package com.haui.btl.demo.Repository;

import com.haui.btl.demo.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    List<Booking> findByUserIduser(int userId);

}