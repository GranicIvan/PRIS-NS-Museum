package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Korisnik;
import com.example.demo.model.Muzej;

public interface MuzejRepo extends JpaRepository<Muzej, Integer> {

	
	Optional<Muzej> findByNaziv(String naziv);
	Optional<Muzej> findById(Integer id);
	Muzej findByidPERIOD(int idMuzej);
	
}
