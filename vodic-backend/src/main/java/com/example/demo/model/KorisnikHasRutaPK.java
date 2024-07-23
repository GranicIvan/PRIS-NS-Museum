package com.example.demo.model;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * The primary key class for the korisnik_has_ruta database table.
 * 
 */
@Embeddable
public class KorisnikHasRutaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int korisnik_idKorisnik;

	@Column(insertable=false, updatable=false)
	private int ruta_idRuta;

	public KorisnikHasRutaPK() {
	}
	public int getKorisnik_idKorisnik() {
		return this.korisnik_idKorisnik;
	}
	public void setKorisnik_idKorisnik(int korisnik_idKorisnik) {
		this.korisnik_idKorisnik = korisnik_idKorisnik;
	}
	public int getRuta_idRuta() {
		return this.ruta_idRuta;
	}
	public void setRuta_idRuta(int ruta_idRuta) {
		this.ruta_idRuta = ruta_idRuta;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof KorisnikHasRutaPK)) {
			return false;
		}
		KorisnikHasRutaPK castOther = (KorisnikHasRutaPK)other;
		return 
			(this.korisnik_idKorisnik == castOther.korisnik_idKorisnik)
			&& (this.ruta_idRuta == castOther.ruta_idRuta);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.korisnik_idKorisnik;
		hash = hash * prime + this.ruta_idRuta;
		
		return hash;
	}
}