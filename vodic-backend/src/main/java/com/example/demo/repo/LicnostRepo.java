package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Licnost;

public interface LicnostRepo extends JpaRepository<Licnost, Integer> {

	Optional<Licnost> findByImeAndPrezime(String ime, String prezime);
}
