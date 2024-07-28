package com.example.SpringBootLearning.repository;

import com.example.SpringBootLearning.entity.Additionalfunctions;
import com.example.SpringBootLearning.entity.AdditionalfunctionsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdditionalfunctionsRepository extends JpaRepository<Additionalfunctions, AdditionalfunctionsId> {

}