package com.example.demo.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;


/**
 * The persistent class for the Ruta database table.
 * 
 */
@Entity
@NamedQuery(name="Ruta.findAll", query="SELECT r FROM Ruta r")
public class Ruta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idRuta;

	@Column(name="Ime_Rute")
	private String ime_Rute;

	@Column(name="Opis")
	private String opis;

	@Lob
	@Column(name="Stanice")
	private String stanice;

	//bi-directional many-to-many association to Korisnik
	@ManyToMany
	@JoinTable(
		name="Korisnik_has_Ruta"
		, joinColumns={
			@JoinColumn(name="Ruta_idRuta")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Korisnik_idKorisnik")
			}
		)
	private List<Korisnik> korisniks;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	@JoinColumn(name="Korisnik_idKorisnik")
	private Korisnik korisnik;

	//bi-directional many-to-many association to Muzej
	@ManyToMany(mappedBy="rutas")
	private List<Muzej> muzejs;

	public Ruta() {
	}

	public int getIdRuta() {
		return this.idRuta;
	}

	public void setIdRuta(int idRuta) {
		this.idRuta = idRuta;
	}

	public String getIme_Rute() {
		return this.ime_Rute;
	}

	public void setIme_Rute(String ime_Rute) {
		this.ime_Rute = ime_Rute;
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

	public List<Korisnik> getKorisniks() {
		return this.korisniks;
	}

	public void setKorisniks(List<Korisnik> korisniks) {
		this.korisniks = korisniks;
	}

	public Korisnik getKorisnik() {
		return this.korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public List<Muzej> getMuzejs() {
		return this.muzejs;
	}

	public void setMuzejs(List<Muzej> muzejs) {
		this.muzejs = muzejs;
	}

}