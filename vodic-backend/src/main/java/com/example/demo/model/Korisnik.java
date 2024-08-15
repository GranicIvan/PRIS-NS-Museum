package com.example.demo.model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the korisnik database table.
 * 
 */
@Entity
@NamedQuery(name="Korisnik.findAll", query="SELECT k FROM Korisnik k")
public class Korisnik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idKorisnik;

	private String email;

	private String ime;

	private String password;

	private String preference;

	private String prezime;

	private String salt;

	//bi-directional many-to-one association to KorisnikHasRuta
	@OneToMany(mappedBy="korisnik")
	@JsonIgnore
	private List<KorisnikHasRuta> korisnikHasRutas;

	//bi-directional many-to-one association to Ruta
	@OneToMany(mappedBy="korisnik")
	@JsonIgnore
	private List<Ruta> rutas;

	public Korisnik() {
	}

	public int getIdKorisnik() {
		return this.idKorisnik;
	}

	public void setIdKorisnik(int idKorisnik) {
		this.idKorisnik = idKorisnik;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPreference() {
		return this.preference;
	}

	public void setPreference(String preference) {
		this.preference = preference;
	}

	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getSalt() {
		return this.salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public List<KorisnikHasRuta> getKorisnikHasRutas() {
		return this.korisnikHasRutas;
	}

	public void setKorisnikHasRutas(List<KorisnikHasRuta> korisnikHasRutas) {
		this.korisnikHasRutas = korisnikHasRutas;
	}

	public KorisnikHasRuta addKorisnikHasRuta(KorisnikHasRuta korisnikHasRuta) {
		getKorisnikHasRutas().add(korisnikHasRuta);
		korisnikHasRuta.setKorisnik(this);

		return korisnikHasRuta;
	}

	public KorisnikHasRuta removeKorisnikHasRuta(KorisnikHasRuta korisnikHasRuta) {
		getKorisnikHasRutas().remove(korisnikHasRuta);
		korisnikHasRuta.setKorisnik(null);

		return korisnikHasRuta;
	}

	public List<Ruta> getRutas() {
		return this.rutas;
	}

	public void setRutas(List<Ruta> rutas) {
		this.rutas = rutas;
	}

	public Ruta addRuta(Ruta ruta) {
		getRutas().add(ruta);
		ruta.setKorisnik(this);

		return ruta;
	}

	public Ruta removeRuta(Ruta ruta) {
		getRutas().remove(ruta);
		ruta.setKorisnik(null);

		return ruta;
	}

}