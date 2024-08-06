package com.example.SpringBootLearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringBootLearning.entity.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {}
