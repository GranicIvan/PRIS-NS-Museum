package com.example.demo.control;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Period;
import com.example.demo.repo.PeriodRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/period")
public class PeriodController {

	@Autowired
	PeriodRepo pr;
	@PersistenceContext
	private EntityManager entityManager;

	@GetMapping
	public List<Period> getAllPeriods() {
		return pr.findAll();
	}

	@PostMapping
	public ResponseEntity<String> kreirajPeriod(@RequestBody Period period) {
		if (period.getNaziv() == null || String.valueOf(period.getPocetak_perioda()) == null
				|| String.valueOf(period.getKraj_perioda()) == null) {
			return new ResponseEntity<>("Naziv, pocetak i kraj perioda su obavezni!", HttpStatus.BAD_REQUEST);
		}

		Optional<Period> postojeciPeriod = pr.findByNazivAndPocetakPeriodaAndKrajPerioda(period.getNaziv(), period.getPocetak_perioda(),
				period.getKraj_perioda());
		if (postojeciPeriod.isPresent()) {
			return new ResponseEntity<>("Period vec postoji!", HttpStatus.CONFLICT);
		}

		pr.save(period);
		return new ResponseEntity<>("Period je uspesno kreiran!", HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Period> updatePeriod(@PathVariable Integer idPERIOD, @RequestBody Period periodDetails) {
		Optional<Period> periodOptional = pr.findById(idPERIOD);
		if (periodOptional.isPresent()) {
			Period period = periodOptional.get();

			period.setNaziv(periodDetails.getNaziv());
			period.setPocetak_perioda(periodDetails.getPocetak_perioda());
			period.setKraj_perioda(periodDetails.getKraj_perioda());

			Period updatedPeriod = pr.save(period);
			return ResponseEntity.ok(updatedPeriod);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePeriod(@PathVariable Integer idPERIOD) {
		Optional<Period> periodOptional = pr.findById(idPERIOD);
		if (periodOptional.isPresent()) {
			pr.deleteById(idPERIOD);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/periodsByName")
	public List<Period> getPeriodsByName(@RequestParam("naziv") String naziv) {

		String upit = "SELECT p FROM Period p WHERE p.naziv = :naziv";
		TypedQuery<Period> query = entityManager.createQuery(upit, Period.class);
		query.setParameter("naziv", naziv);

		return query.getResultList();
	}

	@GetMapping("/periodsByStart")
	public List<Period> getPeriodsByStart(@RequestParam("pocetak") int pocetakPerioda) {

		String upit = "SELECT p FROM Period p WHERE p.pocetak_perioda = :pocetakPerioda";
		TypedQuery<Period> query = entityManager.createQuery(upit, Period.class);
		query.setParameter("pocetakPerioda", pocetakPerioda);

		return query.getResultList();
	}
}