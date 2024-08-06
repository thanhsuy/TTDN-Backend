package com.example.SpringBootLearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringBootLearning.entity.Transactions;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Integer> {}
