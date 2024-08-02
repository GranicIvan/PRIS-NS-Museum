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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Muzej;
import com.example.demo.repo.MuzejRepo;

@RestController
@RequestMapping("/muzej")
public class MuzejController {

	@Autowired
	MuzejRepo mr;
	
	
	
	
	@PostMapping
	public ResponseEntity<String> createMuzej(@RequestBody Muzej muzej) {

		if (muzej.getNaziv() == null ) {
			return new ResponseEntity<>("Naziv is required.", HttpStatus.BAD_REQUEST);
		}


		mr.save(muzej);
		return new ResponseEntity<>("Muzej created successfully.", HttpStatus.CREATED);
	}

	@GetMapping
	public List<Muzej> getAllLicnosti() {
		return mr.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Muzej> getMuzejById(@PathVariable Integer id) {
		Optional<Muzej> licnost = mr.findById(id);
		if (licnost.isPresent()) {
			return ResponseEntity.ok(licnost.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Muzej> updateMuzej(@PathVariable Integer id, @RequestBody Muzej muzejDetails) {
		Optional<Muzej> licnostOptional = mr.findById(id);
		if (licnostOptional.isPresent()) {
			Muzej muzej = licnostOptional.get();

			muzej.setNaziv(muzejDetails.getNaziv());
			muzej.setTxt0(muzejDetails.getTxt0());
			muzej.setTxt1(muzejDetails.getTxt1());
			muzej.setTxt2(muzejDetails.getTxt2());
			muzej.setKoordinate(muzejDetails.getKoordinate());
			muzej.setAdresa(muzejDetails.getAdresa());
			muzej.setMapsLink(muzejDetails.getMapsLink());
			muzej.setMapsDeoZaLink(muzejDetails.getMapsDeoZaLink());
			muzej.setKratakOpis(muzejDetails.getKratakOpis());

			Muzej updatedLicnost = mr.save(muzej);
			return ResponseEntity.ok(updatedLicnost);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteMuzej(@PathVariable Integer id) {
		Optional<Muzej> licnostOptional = mr.findById(id);
		if (licnostOptional.isPresent()) {
			mr.deleteById(id);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/searchByNaziv")
	public List<Muzej> searchByNaziv(@RequestParam String naziv){
		List<Muzej> muzeji = mr.findByNaziv(naziv);
		if(muzeji.isEmpty()) {
			System.err.println("Nema muzeja sa imenom:" + naziv);
		}
		
		return muzeji;
	}
	
	
}
