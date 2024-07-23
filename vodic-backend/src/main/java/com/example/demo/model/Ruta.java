package com.example.demo.model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the ruta database table.
 * 
 */
@Entity
@NamedQuery(name="Ruta.findAll", query="SELECT r FROM Ruta r")
public class Ruta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idRuta;

	@Column(name="ime_rute")
	private String imeRute;

	@Column(name="korisnik_id_korisnik")
	private int korisnikIdKorisnik;

	private String opis;

	@Lob
	private String stanice;

	//bi-directional many-to-one association to KorisnikHasRuta
	@OneToMany(mappedBy="ruta")
	private List<KorisnikHasRuta> korisnikHasRutas;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	private Korisnik korisnik;

	//bi-directional many-to-one association to RutaHasMuzej
	@OneToMany(mappedBy="ruta")
	private List<RutaHasMuzej> rutaHasMuzejs;

	public Ruta() {
	}

	public int getIdRuta() {
		return this.idRuta;
	}

	public void setIdRuta(int idRuta) {
		this.idRuta = idRuta;
	}

	public String getImeRute() {
		return this.imeRute;
	}

	public void setImeRute(String imeRute) {
		this.imeRute = imeRute;
	}

	public int getKorisnikIdKorisnik() {
		return this.korisnikIdKorisnik;
	}

	public void setKorisnikIdKorisnik(int korisnikIdKorisnik) {
		this.korisnikIdKorisnik = korisnikIdKorisnik;
	}

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getStanice() {
		return this.stanice;
	}

	public void setStanice(String stanice) {
		this.stanice = stanice;
	}

	public List<KorisnikHasRuta> getKorisnikHasRutas() {
		return this.korisnikHasRutas;
	}

	public void setKorisnikHasRutas(List<KorisnikHasRuta> korisnikHasRutas) {
		this.korisnikHasRutas = korisnikHasRutas;
	}

	public KorisnikHasRuta addKorisnikHasRuta(KorisnikHasRuta korisnikHasRuta) {
		getKorisnikHasRutas().add(korisnikHasRuta);
		korisnikHasRuta.setRuta(this);

		return korisnikHasRuta;
	}

	public KorisnikHasRuta removeKorisnikHasRuta(KorisnikHasRuta korisnikHasRuta) {
		getKorisnikHasRutas().remove(korisnikHasRuta);
		korisnikHasRuta.setRuta(null);

		return korisnikHasRuta;
	}

	public Korisnik getKorisnik() {
		return this.korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public List<RutaHasMuzej> getRutaHasMuzejs() {
		return this.rutaHasMuzejs;
	}

	public void setRutaHasMuzejs(List<RutaHasMuzej> rutaHasMuzejs) {
		this.rutaHasMuzejs = rutaHasMuzejs;
	}

	public RutaHasMuzej addRutaHasMuzej(RutaHasMuzej rutaHasMuzej) {
		getRutaHasMuzejs().add(rutaHasMuzej);
		rutaHasMuzej.setRuta(this);

		return rutaHasMuzej;
	}

	public RutaHasMuzej removeRutaHasMuzej(RutaHasMuzej rutaHasMuzej) {
		getRutaHasMuzejs().remove(rutaHasMuzej);
		rutaHasMuzej.setRuta(null);

		return rutaHasMuzej;
	}

}