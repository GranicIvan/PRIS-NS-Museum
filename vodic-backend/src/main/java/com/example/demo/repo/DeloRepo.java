package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Delo;

@Repository
public interface DeloRepo extends JpaRepository<Delo, Integer>{

	Delo findByIdPERIOD(int idPERIOD);
}
