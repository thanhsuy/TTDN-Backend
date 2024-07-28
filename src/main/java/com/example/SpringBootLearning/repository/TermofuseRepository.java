package com.example.SpringBootLearning.repository;

import com.example.SpringBootLearning.entity.Termofuse;
import com.example.SpringBootLearning.entity.TermofuseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TermofuseRepository extends JpaRepository<Termofuse, TermofuseId> {

}
