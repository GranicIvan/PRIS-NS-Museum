package com.example.demo.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Korisnik;
import com.example.demo.repo.KorisnikRepo;

@RestController
@RequestMapping("/korisnik")
public class KorisnikController {

	@Autowired KorisnikRepo kr;
	
	@GetMapping
	public List<Korisnik> getAllKorisniks() {
		return kr.findAll();
	}
	
}