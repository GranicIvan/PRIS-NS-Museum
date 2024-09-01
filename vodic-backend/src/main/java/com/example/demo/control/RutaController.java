package com.example.demo.control;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Delo;
import com.example.demo.model.Korisnik;
import com.example.demo.model.Muzej;
import com.example.demo.model.Ruta;
import com.example.demo.repo.DeloRepo;
import com.example.demo.repo.KorisnikRepo;
import com.example.demo.repo.MuzejRepo;
import com.example.demo.repo.RutaRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/ruta")
public class RutaController {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    RutaRepo rr;
    
    @Autowired
    MuzejRepo mr;
    
    @Autowired
    KorisnikRepo kr;
    
    @Autowired
    DeloRepo dr;
    
    
	@GetMapping
	public List<Ruta> getAllRute() {
		return rr.findAll();
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<Ruta> getRutaById(@PathVariable Integer id) {
        return rr.findById(id)
            .map(ruta -> ResponseEntity.ok().body(ruta))
            .orElse(ResponseEntity.notFound().build());
    }
	
	@PostMapping
	public ResponseEntity<String> createRuta(@RequestBody Ruta ruta) {

		if (ruta.getImeRute() == null ) {
			return new ResponseEntity<>("Naziv is required.", HttpStatus.BAD_REQUEST);
		}


		rr.save(ruta);
		return new ResponseEntity<>("Muzej created successfully.", HttpStatus.CREATED);
	}
	

    // Metoda za dodavanje nove rute MCT
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
    
    @GetMapping("/sviSaImenom")
    public List<Ruta> getAllRuteWithName() {
    	List<Ruta> rute  = rr.findAll();
    	for(Ruta r : rute) {
    		r.setStanice(prevediIdUImena(r)) ;
    	}
		return rr.findAll();
	}
    
    public String prevediIdUImena(Ruta r) {
    	StringBuilder sb = new StringBuilder();
    	

    	int[] idMuzeja = Arrays.stream(r.getStanice().split(","))
                .map(String::trim) 
                .mapToInt(Integer::parseInt) 
                .toArray(); 
    	
    	for(int i: idMuzeja) {
    		Optional<Muzej> m = mr.findById(i);
    		if(!m.isEmpty()) {
    			sb.append(m.get().getNaziv() + ", ");
    		}
    	}
    	
    	// Skracujem poslednja dva karaktera da ne bi bilo ', ' na kraju striga IG
    	return (sb.delete(sb.length()-2, sb.length()) ).toString();
    }
    
    @GetMapping("/byIdWName/{id}")
    public Ruta byIdSaImenom(@PathVariable Integer id) {
    	Optional<Ruta> rOptional = rr.findById(id); 
    	if(rOptional.isEmpty()) {
    		System.err.println("Nema rute sa id: " + id);
    		return null;
    	}
    	
    	Ruta r = rOptional.get();
    	
    	r.setStanice( prevediIdUImena(r));
    	
    	    	
    	return r;
    }
    
    @GetMapping("/personalisedRoute/{id}/{uid}") // uid stands for user ID
    public String personalised(@PathVariable Integer id, @PathVariable Integer uid) {
    	
    	//DELETE - just for debugging
    	System.err.println("- - - - - - - id rute: " + id + ",  user id: "+ uid);
    	
    	Optional<Ruta> rOptional = rr.findById(id); 
    	if(rOptional.isEmpty()) {
    		System.err.println("Nema rute sa id: " + id);
    		return null;
    	}
    	Ruta r = rOptional.get();
    	
    	
    	
    	Optional<Korisnik> kOptional = kr.findById(uid);
    	if(kOptional.isEmpty()) {
    		System.err.println("Nema korisnika sa id: " + id);
    		return null;
    	}
    	Korisnik k = kOptional.get();
    	
    	
    	
    	if(k.getPreference().isEmpty()) {
    		System.err.println("Korisnik nema preference");
    		return null;    		
    	}
    	
    	
    	System.err.println("Pre extracta %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
    	
    	ArrayList<Delo> dela = extractDelaKojaKorisnikZeli(k);
    	
    	
    	//To su stanice (muzeji) koji se nalaze na ruti
    	String[] stanice = r.getStanice().trim().split(",");
    	//Id evi od tih muzeja
    	ArrayList<Integer> lista =  new ArrayList<Integer>();
    	
    	for(String s: stanice) {
    		lista.add( Integer.parseInt( s.trim()) );
    	}
    	
    	
    	//Sada rasporedjujemo dela po muzejima
    	Map<Integer, List<Delo>> mapa = new HashMap<Integer, List<Delo>>();
    	
    	
    	for(Delo d : dela) {
    		d.getKratkiOpis();
//    		mapa.put( Integer.parseInt( d.getKratkiOpis().trim() )  , d);
    		Integer key = Integer.parseInt( d.getKratkiOpis().trim());
    		mapa.computeIfAbsent( key , newValue -> new ArrayList<>()).add(d);

    	}
    	
    	System.err.println("OVDE $$$$$$$$$");
    	extraxtInfo(mapa, lista, r );
    	
    	return "Rezultat";
    }
    
    
    private ArrayList<Delo> extractDelaKojaKorisnikZeli(Korisnik k) {//POI - point of interest in this case art piece
    	
    	String preferences = k.getPreference().trim();
    	    	
    	String[] idInString = preferences.split(","); 
    	
    	
    	
    	ArrayList<Integer> result  = new ArrayList<Integer>();
    	
    	for(String s : idInString) {
    		
    		result.add( Integer.parseInt(s.trim()) );
    	}
    	
    	ArrayList<Delo> dela = new ArrayList<Delo>();
    	
    	for(Integer i : result) {
    		dela.add( dr.findByIdPERIOD(i) );
    	}
    	    	
    	
		return dela;     	
    }
    
    
    private String extraxtInfo(Map<Integer, List<Delo>> mapa, ArrayList<Integer> listaMuzeja, Ruta r) {
    	
    	StringBuilder sb = new StringBuilder();
    	
    	sb.append("Ruta: "+r.getImeRute() + "\n");
    	sb.append("Opis " + r.getOpis() + "\n\n");
    	
    	for(Integer i: listaMuzeja) {
    		Muzej muzej = mr.findByidPERIOD(i);
    		sb.append("================================\n");
    		sb.append(muzej.getNaziv()+"\n");
    		
    		List<Delo> dela = mapa.get(i); //Dela koja se nalaze u muzeju i kor zeli da ih vidi
    		
    		for(Delo d: dela) {
    			sb.append(d.getNaziv() + "\n");
    			sb.append("Godina nastanka:" + d.getGodinaNastanka() + "\n");
    			sb.append(d.getTxt0() + "\n");
    			sb.append("--------------------------------\n\n");
    		}
    		
    		sb.append("================================\n\n");
    	}
    	
    	System.err.println(sb.toString());
    	return sb.toString();
    }
    
    
    
}
