package com.example.SpringBootLearning.repository;

import com.example.SpringBootLearning.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
//    List<Car> findAllByidCarOwner(Integer idcarowner);


    List<Car> findAllByidcarowner(Long idCarOwner);
}
