package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Delo;

public interface DeloRepo extends JpaRepository<Delo, Integer> {

}
