package com.example.demo.control;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Licnost;
import com.example.demo.model.Period;
import com.example.demo.repo.LicnostRepo;

@RestController
@RequestMapping("/licnost")
public class LicnostController {

	@Autowired
	LicnostRepo lr;

	@PostMapping
	public ResponseEntity<String> createLicnost(@RequestBody Licnost licnost) {

		if (licnost.getIme() == null || licnost.getPrezime() == null) {
			return new ResponseEntity<>("Ime and Prezime are required.", HttpStatus.BAD_REQUEST);
		}

		Optional<Licnost> existingLicnost = lr.findByImeAndPrezime(licnost.getIme(), licnost.getPrezime());
		if (existingLicnost.isPresent()) {
			return new ResponseEntity<>("Licnost already exists.", HttpStatus.CONFLICT);
		}

		lr.save(licnost);
		return new ResponseEntity<>("Licnost created successfully.", HttpStatus.CREATED);
	}

	@GetMapping
	public List<Licnost> getAllLicnosti() {
		return lr.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Licnost> getLicnostById(@PathVariable Integer id) {
		Optional<Licnost> licnost = lr.findById(id);
		if (licnost.isPresent()) {
			return ResponseEntity.ok(licnost.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Licnost> updateLicnost(@PathVariable Integer id, @RequestBody Licnost licnostDetails) {
		Optional<Licnost> licnostOptional = lr.findById(id);
		if (licnostOptional.isPresent()) {
			Licnost licnost = licnostOptional.get();

			licnost.setIme(licnostDetails.getIme());
			licnost.setPrezime(licnostDetails.getPrezime());
			licnost.setGodinaRodjenja(licnostDetails.getGodinaRodjenja());
			licnost.setMestoRodjenja(licnostDetails.getMestoRodjenja());
			licnost.setGodinaSmrti(licnostDetails.getGodinaSmrti());

			Licnost updatedLicnost = lr.save(licnost);
			return ResponseEntity.ok(updatedLicnost);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteLicnost(@PathVariable Integer id) {
		Optional<Licnost> licnostOptional = lr.findById(id);
		if (licnostOptional.isPresent()) {
			lr.deleteById(id);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
