package com.example.demo.control;

import java.util.List;
import java.util.Optional;

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

import com.example.demo.model.Korisnik;
import com.example.demo.repo.KorisnikRepo;
@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/korisnik")
public class KorisnikController {

	@Autowired KorisnikRepo kr;
	
	@GetMapping
	public List<Korisnik> getAllKorisniks() {
		return kr.findAll();
	}
	
	
	@PostMapping
	public ResponseEntity<Korisnik> createKorisnik(@RequestBody Korisnik korisnik) {
        try {
            Korisnik savedKorisnik = kr.save(korisnik);
            return new ResponseEntity<>(savedKorisnik, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	
	//---------------
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Korisnik> getKorisnikById(@PathVariable Integer id) {
		Optional<Korisnik> korisnik = kr.findById(id);
		if (korisnik.isPresent()) {
			return ResponseEntity.ok(korisnik.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Korisnik> updateKorisnik(@PathVariable Integer id, @RequestBody Korisnik korisnikDetails) {
		Optional<Korisnik> licnostOptional = kr.findById(id);
		if (licnostOptional.isPresent()) {
			Korisnik korisnik = licnostOptional.get();

			korisnik.setIme(korisnikDetails.getIme());
			korisnik.setPrezime(korisnikDetails.getPrezime());
			korisnik.setEmail(korisnikDetails.getEmail());
			korisnik.setSalt(korisnikDetails.getSalt());
			korisnik.setPassword(korisnikDetails.getPassword());
			korisnik.setPreference(korisnikDetails.getPreference());

			Korisnik updatedLicnost = kr.save(korisnik);
			return ResponseEntity.ok(updatedLicnost);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteKorisnik(@PathVariable Integer id) {
		Optional<Korisnik> licnostOptional = kr.findById(id);
		if (licnostOptional.isPresent()) {
			kr.deleteById(id);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	@GetMapping("/searchByEmail")
	public Korisnik searchByEmail(@RequestParam String email){
		
		Optional<Korisnik> trazeni = kr.findByEmail(email);
		if(trazeni == null) {
			System.err.println("Ne postoji korisnik sa email-om: " + email);			
		}
		return trazeni.get();
	}
	    
	
}