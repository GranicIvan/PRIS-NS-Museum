package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Delo;

@Repository
public interface DeloRepository extends JpaRepository<Delo, Integer>{

	Delo findByIdPERIOD(int idPERIOD);
}
