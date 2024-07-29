package com.example.demo.control;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Umetnost;
import com.example.demo.repo.UmetnostRepo;

@RestController
@RequestMapping("/api/umetnost")
public class UmetnostController {

    @Autowired
    private UmetnostRepo umetnostRepository;

    @GetMapping
    public List<Umetnost> getAllUmetnosti() {
        return umetnostRepository.findAll();
    }

    @GetMapping("/{id}")
    public Umetnost getUmetnostById(@PathVariable int id) {
        return umetnostRepository.findById(id).get();
    }

    @PostMapping
    public Umetnost createUmetnost(@RequestBody Umetnost umetnost) {
        return umetnostRepository.save(umetnost);
    }

    @PutMapping("/{id}")
    public Umetnost updateUmetnost(@PathVariable int id, @RequestBody Umetnost umetnost) {
        Optional<Umetnost> postojecaUmetnost = umetnostRepository.findById(id);
        if (postojecaUmetnost.isPresent()) {
        	Umetnost u = postojecaUmetnost.get();
        	u.setIdUmetnost(umetnost.getIdUmetnost());
        	u.setLicnostDidUmetnosts(umetnost.getLicnostDidUmetnosts());
        	u.setNaziv(umetnost.getNaziv());
        	u.setOpis(umetnost.getOpis());
        	Umetnost novaUmetnost = umetnostRepository.save(u);
            return novaUmetnost;
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public boolean deleteUmetnost(@PathVariable int id) {
        Optional<Umetnost> postojecaUmetnost = umetnostRepository.findById(id);
        if (postojecaUmetnost.isPresent()) {
            umetnostRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
