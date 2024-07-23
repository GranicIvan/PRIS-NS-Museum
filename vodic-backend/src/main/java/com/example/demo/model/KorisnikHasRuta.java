package com.example.demo.model;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the korisnik_has_ruta database table.
 * 
 */
@Entity
@Table(name="korisnik_has_ruta")
@NamedQuery(name="KorisnikHasRuta.findAll", query="SELECT k FROM KorisnikHasRuta k")
public class KorisnikHasRuta implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private KorisnikHasRutaPK id;

	@Column(name="korisnik_id_korisnik")
	private int korisnikIdKorisnik;

	@Column(name="ruta_id_ruta")
	private int rutaIdRuta;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	private Korisnik korisnik;

	//bi-directional many-to-one association to Ruta
	@ManyToOne
	private Ruta ruta;

	public KorisnikHasRuta() {
	}

	public KorisnikHasRutaPK getId() {
		return this.id;
	}

	public void setId(KorisnikHasRutaPK id) {
		this.id = id;
	}

	public int getKorisnikIdKorisnik() {
		return this.korisnikIdKorisnik;
	}

	public void setKorisnikIdKorisnik(int korisnikIdKorisnik) {
		this.korisnikIdKorisnik = korisnikIdKorisnik;
	}

	public int getRutaIdRuta() {
		return this.rutaIdRuta;
	}

	public void setRutaIdRuta(int rutaIdRuta) {
		this.rutaIdRuta = rutaIdRuta;
	}

	public Korisnik getKorisnik() {
		return this.korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public Ruta getRuta() {
		return this.ruta;
	}

	public void setRuta(Ruta ruta) {
		this.ruta = ruta;
	}

}