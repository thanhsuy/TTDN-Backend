package com.example.SpringBootLearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringBootLearning.entity.Termofuse;
import com.example.SpringBootLearning.entity.TermofuseId;

@Repository
public interface TermofuseRepository extends JpaRepository<Termofuse, TermofuseId> {}
