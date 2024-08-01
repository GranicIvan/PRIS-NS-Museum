package com.example.demo.control;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.coyote.BadRequestException;
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

import com.example.demo.model.Delo;
import com.example.demo.model.Licnost;
import com.example.demo.model.Period;
import com.example.demo.repo.LicnostRepo;
import com.example.demo.repo.PeriodRepo;
import com.example.demo.repo.DeloRepo;
import com.example.demo.repo.LicnostRepo;
import com.example.demo.repo.PeriodRepo;


@RestController
@RequestMapping("/delo")
public class DeloController {

	@Autowired
	DeloRepo deloRepo;
	@Autowired
	LicnostRepo licnostRepo;
	@Autowired
	PeriodRepo periodRepo;
	
	@GetMapping
    public ResponseEntity<List<Delo>> getAllDela() {
        List<Delo> dela = deloRepo.findAll();
        return ResponseEntity.ok(dela);
    }

    @GetMapping("/{idPERIOD}")
    public ResponseEntity<Delo> getDeloById(@PathVariable int idPERIOD) {
        Optional<Delo> result = Optional.ofNullable(deloRepo.findByIdPERIOD(idPERIOD));
        return result.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<String> createDelo(@RequestBody Delo delo) {
        Licnost licnost = licnostRepo.findByidLicnost(delo.getLicnost().getIdLicnost());
        Period period = periodRepo.findByIdPERIOD(delo.getPeriod().getIdPERIOD());

        if (licnost == null || period == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body("There is no licnost or period with given details");
        }

        delo.setLicnost(licnost);
        delo.setPeriod(period);

        Delo savedDelo = deloRepo.save(delo);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body("Delo created successfully with ID: " + savedDelo.getIdPERIOD());
    }

    @PutMapping("/{idPERIOD}")
    public ResponseEntity<String> updateDelo(@PathVariable int idPERIOD, @RequestBody Delo updatedDelo) {
        Optional<Delo> deloOptional = Optional.ofNullable(deloRepo.findByIdPERIOD(idPERIOD));

        if (!deloOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Delo with id " + idPERIOD + " does not exist");
        }

        Delo delo = deloOptional.get();
        Licnost licnost = licnostRepo.findByidLicnost(updatedDelo.getLicnost().getIdLicnost());
        Period period = periodRepo.findByIdPERIOD(updatedDelo.getPeriod().getIdPERIOD());

        if (licnost == null || period == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body("There is no licnost or period with given details");
        }

        delo.setGodinaNastanka(updatedDelo.getGodinaNastanka());
        delo.setKratkiOpis(updatedDelo.getKratkiOpis());
        delo.setNaziv(updatedDelo.getNaziv());
        delo.setTxt0(updatedDelo.getTxt0());
        delo.setTxt1(updatedDelo.getTxt1());
        delo.setTxt2(updatedDelo.getTxt2());
        delo.setLicnost(licnost);
        delo.setPeriod(period);

        deloRepo.save(delo);
        return ResponseEntity.ok("Delo updated successfully");
    }

	@DeleteMapping("/{idPERIOD}")
	public void deleteDelo(@PathVariable int idPERIOD) throws BadRequestException {
		if (deloRepo.existsById(idPERIOD)) {
			deloRepo.deleteById(idPERIOD);
		} else {
			throw new BadRequestException("Delo with id " + idPERIOD + " does not exist");
		}

	}
}
