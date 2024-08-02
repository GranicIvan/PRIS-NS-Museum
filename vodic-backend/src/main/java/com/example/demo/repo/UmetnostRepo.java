package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Umetnost;

public interface UmetnostRepo extends JpaRepository<Umetnost, Integer> {

	List<Umetnost> findByNaziv(String naziv);
}
