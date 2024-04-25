package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Korisnik;

public interface KorisnikRepo extends JpaRepository<Korisnik, Integer> {

}
