package com.example.SpringBootLearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringBootLearning.entity.Additionalfunctions;
import com.example.SpringBootLearning.entity.AdditionalfunctionsId;

@Repository
public interface AdditionalfunctionsRepository extends JpaRepository<Additionalfunctions, AdditionalfunctionsId> {}
