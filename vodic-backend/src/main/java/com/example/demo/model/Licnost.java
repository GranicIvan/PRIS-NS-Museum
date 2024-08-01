package com.example.demo.model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the licnost database table.
 * 
 */
@Entity
@NamedQuery(name="Licnost.findAll", query="SELECT l FROM Licnost l")
public class Licnost implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idLicnost;

	@Column(name="godina_rodjenja")
	private String godinaRodjenja;

	@Column(name="godina_smrti")
	private String godinaSmrti;

	private String ime;

	@Column(name="mesto_rodjenja")
	private String mestoRodjenja;

	private String prezime;

	//bi-directional many-to-one association to Delo
	@OneToMany(mappedBy = "licnost", cascade = CascadeType.ALL, orphanRemoval = true)
	//@OneToMany(mappedBy="licnost")
	private List<Delo> delos;

	//bi-directional many-to-one association to Period
	@ManyToOne
	private Period period;

	//bi-directional many-to-one association to LicnostDidUmetnost
	@OneToMany(mappedBy = "licnost", cascade = CascadeType.ALL, orphanRemoval = true)
	//@OneToMany(mappedBy="licnost")
	private List<LicnostDidUmetnost> licnostDidUmetnosts;

	public Licnost() {
	}

	public int getIdLicnost() {
		return this.idLicnost;
	}

	public void setIdLicnost(int idLicnost) {
		this.idLicnost = idLicnost;
	}

	public String getGodinaRodjenja() {
		return this.godinaRodjenja;
	}

	public void setGodinaRodjenja(String godinaRodjenja) {
		this.godinaRodjenja = godinaRodjenja;
	}

	public String getGodinaSmrti() {
		return this.godinaSmrti;
	}

	public void setGodinaSmrti(String godinaSmrti) {
		this.godinaSmrti = godinaSmrti;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getMestoRodjenja() {
		return this.mestoRodjenja;
	}

	public void setMestoRodjenja(String mestoRodjenja) {
		this.mestoRodjenja = mestoRodjenja;
	}

	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public List<Delo> getDelos() {
		return this.delos;
	}

	public void setDelos(List<Delo> delos) {
		this.delos = delos;
	}

	public Delo addDelo(Delo delo) {
		getDelos().add(delo);
		delo.setLicnost(this);

		return delo;
	}

	public Delo removeDelo(Delo delo) {
		getDelos().remove(delo);
		delo.setLicnost(null);

		return delo;
	}

	public Period getPeriod() {
		return this.period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

	public List<LicnostDidUmetnost> getLicnostDidUmetnosts() {
		return this.licnostDidUmetnosts;
	}

	public void setLicnostDidUmetnosts(List<LicnostDidUmetnost> licnostDidUmetnosts) {
		this.licnostDidUmetnosts = licnostDidUmetnosts;
	}

	public LicnostDidUmetnost addLicnostDidUmetnost(LicnostDidUmetnost licnostDidUmetnost) {
		getLicnostDidUmetnosts().add(licnostDidUmetnost);
		licnostDidUmetnost.setLicnost(this);

		return licnostDidUmetnost;
	}

	public LicnostDidUmetnost removeLicnostDidUmetnost(LicnostDidUmetnost licnostDidUmetnost) {
		getLicnostDidUmetnosts().remove(licnostDidUmetnost);
		licnostDidUmetnost.setLicnost(null);

		return licnostDidUmetnost;
	}

}