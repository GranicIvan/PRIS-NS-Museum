package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Period;

public interface PeriodRepo extends JpaRepository<Period, Integer> {

}
