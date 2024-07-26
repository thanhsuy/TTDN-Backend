package com.haui.btl.demo.Repository;

import com.haui.btl.demo.Entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
}
