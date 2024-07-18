package com.example.SpringBootLearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringBootLearning.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {}
