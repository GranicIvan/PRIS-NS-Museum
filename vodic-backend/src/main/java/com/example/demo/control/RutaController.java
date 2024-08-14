package com.example.demo.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Ruta;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@RestController
@RequestMapping("/ruta")
public class RutaController {

    @PersistenceContext
    private EntityManager entityManager;

    // Metoda za dodavanje nove rute
    @PostMapping("/add")
    public Ruta addRuta(@RequestBody Ruta ruta) {
        entityManager.persist(ruta);
        return ruta;
    }

    // Metoda za izmenu postojeće rute
    @PutMapping("/update/{id}")
    public Ruta updateRuta(@PathVariable int id, @RequestBody Ruta rutaDetails) {
        Ruta ruta = entityManager.find(Ruta.class, id);

        if (ruta != null) {
            ruta.setImeRute(rutaDetails.getImeRute());
            ruta.setKorisnikIdKorisnik(rutaDetails.getKorisnikIdKorisnik());
            ruta.setOpis(rutaDetails.getOpis());
            ruta.setStanice(rutaDetails.getStanice());
            entityManager.merge(ruta);
        }
        
        return ruta;
    }
}
