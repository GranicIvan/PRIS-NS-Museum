package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Licnost;

public interface LicnostRepo extends JpaRepository<Licnost, Integer> {

}
