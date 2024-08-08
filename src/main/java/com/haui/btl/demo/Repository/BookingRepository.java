package com.haui.btl.demo.Repository;

import com.haui.btl.demo.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    List<Booking> findByUserIduser(int userId);

    @Query(value = "select * from booking where Car_idCarOwner = :idcarowner", nativeQuery = true)
    public List<Booking> findBookingByIdCarOwner(@Param("idcarowner") Integer idcarowner);

    @Query(value = "select * from booking where User_idUser = :iduser", nativeQuery = true)
    public List<Booking> findBookingByIdUser(@Param("iduser") Integer iduser);

}