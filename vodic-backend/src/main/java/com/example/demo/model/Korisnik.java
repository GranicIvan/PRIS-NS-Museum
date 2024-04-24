package com.example.demo.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;


/**
 * The persistent class for the Korisnik database table.
 * 
 */
@Entity
@NamedQuery(name="Korisnik.findAll", query="SELECT k FROM Korisnik k")
public class Korisnik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idKorisnik;

	@Column(name="Email")
	private String email;

	@Column(name="Ime")
	private String ime;

	@Column(name="Password")
	private String password;

	@Column(name="Preference")
	private String preference;

	@Column(name="Prezime")
	private String prezime;

	@Column(name="Salt")
	private String salt;

	//bi-directional many-to-many association to Ruta
	@ManyToMany(mappedBy="korisniks")
	private List<Ruta> rutas1;

	//bi-directional many-to-one association to Ruta
	@OneToMany(mappedBy="korisnik")
	private List<Ruta> rutas2;

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

	public List<Ruta> getRutas1() {
		return this.rutas1;
	}

	public void setRutas1(List<Ruta> rutas1) {
		this.rutas1 = rutas1;
	}

	public List<Ruta> getRutas2() {
		return this.rutas2;
	}

	public void setRutas2(List<Ruta> rutas2) {
		this.rutas2 = rutas2;
	}

	public Ruta addRutas2(Ruta rutas2) {
		getRutas2().add(rutas2);
		rutas2.setKorisnik(this);

		return rutas2;
	}

	public Ruta removeRutas2(Ruta rutas2) {
		getRutas2().remove(rutas2);
		rutas2.setKorisnik(null);

		return rutas2;
	}

}