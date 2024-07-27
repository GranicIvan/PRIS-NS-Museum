package com.example.demo.control;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.DeloResponseDto;
import com.example.demo.dto.InsertDeloDto;
import com.example.demo.dto.UpdateDeloDto;
import com.example.demo.model.Delo;
import com.example.demo.model.Licnost;
import com.example.demo.model.Period;
import com.example.demo.repository.DeloRepository;
import com.example.demo.repository.LicnostRepo;
import com.example.demo.repository.PeriodRepo;

@RestController
@RequestMapping("/delo")
public class DeloController {

	@Autowired
	DeloRepository deloRepo;
	@Autowired
	LicnostRepo licnostRepo;
	@Autowired
	PeriodRepo periodRepo;
	
	@GetMapping
	public List<DeloResponseDto> getAllDela() {
		List<Delo> dela =  deloRepo.findAll();
		 return dela.stream()
                 .map(x -> this.convertToDto(x))
                 .collect(Collectors.toList());
	}

	@GetMapping("/{idPERIOD}")
	public DeloResponseDto getDeloById(@PathVariable int idPERIOD) throws BadRequestException {
		Delo result = deloRepo.findByIdPERIOD(idPERIOD);
		if (result == null) {
			throw new BadRequestException("Delo with id " + idPERIOD + " does not exist");
		}
		return this.convertToDto(result);
	}

	@PostMapping
	public int createDelo(@RequestBody InsertDeloDto deloDTO) throws BadRequestException {
        Licnost licnost = licnostRepo.findByidLicnost(deloDTO.getLicnostId());
        Period period = periodRepo.findByIdPERIOD(deloDTO.getPeriodId());
        
        if(licnost == null  || period == null) {
        	throw new BadRequestException("There is no licnost or period with given details");
        }

        Delo delo = new Delo();
        delo.setGodina_nastanka(deloDTO.getGodina_nastanka());
        delo.setKratki_opis(deloDTO.getKratki_opis());
        delo.setNaziv(deloDTO.getNaziv());
        delo.setLicnost(licnost);
        delo.setPeriod(period);

        // Save the entity
        Delo savedDelo = deloRepo.save(delo);

        return savedDelo.getIdPERIOD();
	}
	
	@PutMapping("/{idPERIOD}")
	public void updateDelo(@PathVariable int idPERIOD, @RequestBody UpdateDeloDto deloDTO ) throws BadRequestException {
		Delo delo = deloRepo.findByIdPERIOD(idPERIOD);
		
		if (delo == null) {
			throw new BadRequestException("Delo with id " + idPERIOD + " does not exist");
		}
		
		Licnost licnost = licnostRepo.findByidLicnost(deloDTO.getLicnostId());
        Period period = periodRepo.findByIdPERIOD(deloDTO.getPeriodId());
        
        if(licnost == null  || period == null) {
        	throw new BadRequestException("There is no licnost or period with given details");
        }
       
        delo.setGodina_nastanka(deloDTO.getGodina_nastanka());
        delo.setKratki_opis(deloDTO.getKratki_opis());
        delo.setNaziv(deloDTO.getNaziv());
        delo.setTxt0(deloDTO.getTxt0());
        delo.setTxt1(deloDTO.getTxt1());
        delo.setTxt2(deloDTO.getTxt2());
        delo.setLicnost(licnost);
        delo.setPeriod(period);

        deloRepo.save(delo);
	}

	@DeleteMapping("/{idPERIOD}")
	public void deleteDelo(@PathVariable int idPERIOD) throws BadRequestException {
		if (deloRepo.existsById(idPERIOD)) {
			deloRepo.deleteById(idPERIOD);
		} else {
			throw new BadRequestException("Delo with id " + idPERIOD + " does not exist");
		}

	}
	
	public DeloResponseDto convertToDto(Delo delo) {
        DeloResponseDto dto = new DeloResponseDto();
        dto.setIdPERIOD(delo.getIdPERIOD());
        dto.setGodina_nastanka(delo.getGodina_nastanka());
        dto.setKratki_opis(delo.getKratki_opis());
        dto.setNaziv(delo.getNaziv());
        dto.setLicnostId(delo.getLicnost().getIdLicnost());
        dto.setPeriodId(delo.getPeriod().getIdPERIOD());
        dto.setNazivLicnosti(delo.getLicnost().getIme() + " " + delo.getLicnost().getPrezime()); // Assuming Licnost has a getNaziv() method
        dto.setNazivPerioda(delo.getPeriod().getNaziv()); // Assuming Period has a getNaziv() method
        return dto;
    }
}
