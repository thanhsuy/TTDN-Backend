package com.example.SpringBootLearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.SpringBootLearning.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    @Query(value = "select * from booking where Car_idCarOwner = :idcarowner", nativeQuery = true)
    public List<Booking> findBookingByIdCarOwner(@Param("idcarowner") Integer idcarowner);

    @Query(value = "select * from booking where User_idUser = :iduser", nativeQuery = true)
    public List<Booking> findBookingByIdUser(@Param("iduser") Integer iduser);
}
