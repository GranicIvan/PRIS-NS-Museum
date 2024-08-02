package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Delo;

@Repository
public interface DeloRepo extends JpaRepository<Delo, Integer>{

	Delo findByIdPERIOD(int idPERIOD);
	List<Delo> findByNaziv(String naziv);
	@Query("SELECT d FROM Delo d JOIN d.licnost l WHERE l.ime = :ime AND l.prezime = :prezime")
    List<Delo> findByLicnostImeAndPrezime(@Param("ime") String ime, @Param("prezime") String prezime);
}
