package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Muzej;

public interface MuzejRepo extends JpaRepository<Muzej, Integer> {

}
