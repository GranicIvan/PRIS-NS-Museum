package com.example.demo.control;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Licnost;
import com.example.demo.repo.LicnostRepo;

@RestController
@RequestMapping("/licnost")
public class LicnostController {
	
	@Autowired LicnostRepo lr;
	
	@PostMapping
    public ResponseEntity<String> createLicnost(@RequestBody Licnost licnost) {
        // Check for required parameters
        if (licnost.getIme() == null || licnost.getPrezime() == null) {
            return new ResponseEntity<>("Ime and Prezime are required.", HttpStatus.BAD_REQUEST);
        }

        // Check if the licnost already exists
        Optional<Licnost> existingLicnost = lr.findByImeAndPrezime(licnost.getIme(), licnost.getPrezime());
        if (existingLicnost.isPresent()) {
            return new ResponseEntity<>("Licnost already exists.", HttpStatus.CONFLICT);
        }

        // Save the new licnost
        lr.save(licnost);
        return new ResponseEntity<>("Licnost created successfully.", HttpStatus.CREATED);
    }

}
