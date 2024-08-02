package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Period;

public interface PeriodRepo extends JpaRepository<Period, Integer> {
	Period findByIdPERIOD(int idPERIOD);
	@Query("SELECT p FROM Period p WHERE p.naziv = :naziv AND p.pocetak_perioda = :pocetak_perioda AND p.kraj_perioda = :kraj_perioda")
    Optional<Period> findByNazivAndPocetakPeriodaAndKrajPerioda(
        @Param("naziv") String naziv, 
        @Param("pocetak_perioda") int pocetak_perioda, 
        @Param("kraj_perioda") int kraj_perioda
    );
	
}
