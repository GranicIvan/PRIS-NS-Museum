package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Korisnik;
import com.example.demo.model.Licnost;

public interface KorisnikRepo extends JpaRepository<Korisnik, Integer> {

	
	Optional<Korisnik> findByImeAndPrezime(String ime, String prezime);
	Optional<Korisnik> findById(Integer id);
	Korisnik findByidKorisnik(int idLicnost);
	Korisnik findByEmail(String email);
}
