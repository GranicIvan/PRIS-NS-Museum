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
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;


/**
 * The persistent class for the Period database table.
 * 
 */
@Entity
@NamedQuery(name="Period.findAll", query="SELECT p FROM Period p")
public class Period implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPERIOD;

	@Column(name="Kraj_perioda")
	private int kraj_perioda;

	@Column(name="Naziv")
	private String naziv;

	@Column(name="Pocetak_perioda")
	private int pocetak_perioda;

	@Lob
	@Column(name="TXT0")
	private String txt0;

	@Lob
	@Column(name="TXT1")
	private String txt1;

	@Lob
	@Column(name="TXT2")
	private String txt2;

	//bi-directional many-to-one association to Delo
	@OneToMany(mappedBy="period")
	private List<Delo> delos;

	//bi-directional many-to-one association to Licnost
	@OneToMany(mappedBy="period")
	private List<Licnost> licnosts;

	//bi-directional many-to-many association to Muzej
	@ManyToMany
	@JoinTable(
		name="Muzej_has_Period"
		, joinColumns={
			@JoinColumn(name="Period_idPERIOD")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Muzej_idPERIOD")
			}
		)
	private List<Muzej> muzejs;

	public Period() {
	}

	public int getIdPERIOD() {
		return this.idPERIOD;
	}

	public void setIdPERIOD(int idPERIOD) {
		this.idPERIOD = idPERIOD;
	}

	public int getKraj_perioda() {
		return this.kraj_perioda;
	}

	public void setKraj_perioda(int kraj_perioda) {
		this.kraj_perioda = kraj_perioda;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public int getPocetak_perioda() {
		return this.pocetak_perioda;
	}

	public void setPocetak_perioda(int pocetak_perioda) {
		this.pocetak_perioda = pocetak_perioda;
	}

	public String getTxt0() {
		return this.txt0;
	}

	public void setTxt0(String txt0) {
		this.txt0 = txt0;
	}

	public String getTxt1() {
		return this.txt1;
	}

	public void setTxt1(String txt1) {
		this.txt1 = txt1;
	}

	public String getTxt2() {
		return this.txt2;
	}

	public void setTxt2(String txt2) {
		this.txt2 = txt2;
	}

	public List<Delo> getDelos() {
		return this.delos;
	}

	public void setDelos(List<Delo> delos) {
		this.delos = delos;
	}

	public Delo addDelo(Delo delo) {
		getDelos().add(delo);
		delo.setPeriod(this);

		return delo;
	}

	public Delo removeDelo(Delo delo) {
		getDelos().remove(delo);
		delo.setPeriod(null);

		return delo;
	}

	public List<Licnost> getLicnosts() {
		return this.licnosts;
	}

	public void setLicnosts(List<Licnost> licnosts) {
		this.licnosts = licnosts;
	}

	public Licnost addLicnost(Licnost licnost) {
		getLicnosts().add(licnost);
		licnost.setPeriod(this);

		return licnost;
	}

	public Licnost removeLicnost(Licnost licnost) {
		getLicnosts().remove(licnost);
		licnost.setPeriod(null);

		return licnost;
	}

	public List<Muzej> getMuzejs() {
		return this.muzejs;
	}

	public void setMuzejs(List<Muzej> muzejs) {
		this.muzejs = muzejs;
	}

}