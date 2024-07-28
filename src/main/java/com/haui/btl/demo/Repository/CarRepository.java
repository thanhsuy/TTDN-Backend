package com.haui.btl.demo.Repository;

import com.haui.btl.demo.Entity.Car;
import com.haui.btl.demo.Repository.CustomCarRepository.CarRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CarRepository extends JpaRepository<Car, Integer>, CarRepositoryCustom<Car> {
    @Query("SELECT SUBSTRING_INDEX(c.address, ',', -1) AS city, COUNT(c.idcar) AS carCount " +
            "FROM Car c " +
            "GROUP BY city " +
            "ORDER BY carCount DESC " +
            "LIMIT 5")
    List<Object[]> findTop5CitiesWithMostCars();


}
