package com.example.SpringBootLearning.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.example.SpringBootLearning.dto.request.RentACarRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.SpringBootLearning.entity.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    //    List<Car> findAllByidCarOwner(Integer idcarowner);

    List<Car> findAllByidcarowner(Long idCarOwner);

    @Query(value = "SELECT EXISTS (" +
            "SELECT 1 FROM car WHERE status = 'Available' AND idCar = :idCar AND idCar NOT IN (" +
            "SELECT Car_idCar FROM booking " +
            "WHERE startDateTime < :endTime AND endDateTime > :startTime " +
            "AND status <> 'Complete'))",
            nativeQuery = true)
    Long checkCarAvailable(@Param("startTime") LocalDate startTime,
                              @Param("endTime") LocalDate endTime,
                              @Param("idCar") int idCar);

}
