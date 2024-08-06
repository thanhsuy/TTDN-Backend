package com.example.SpringBootLearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringBootLearning.entity.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    //    List<Car> findAllByidCarOwner(Integer idcarowner);

    List<Car> findAllByidcarowner(Long idCarOwner);
}
