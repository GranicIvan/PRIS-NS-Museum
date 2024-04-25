package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Umetnost;

public interface UmetnostRepo extends JpaRepository<Umetnost, Integer> {

}
