package com.example.demo.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;


/**
 * The persistent class for the Licnost database table.
 * 
 */
@Entity
@NamedQuery(name="Licnost.findAll", query="SELECT l FROM Licnost l")
public class Licnost implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idLicnost;

	@Column(name="Godina_rodjenja")
	private String godina_rodjenja;

	@Column(name="Godina_smrti")
	private String godina_smrti;

	@Column(name="Ime")
	private String ime;

	@Column(name="Mesto_rodjenja")
	private String mesto_rodjenja;

	@Column(name="Prezime")
	private String prezime;

	//bi-directional many-to-one association to Delo
	@OneToMany(mappedBy="licnost")
	private List<Delo> delos;

	//bi-directional many-to-one association to Period
	@ManyToOne
	@JoinColumn(name="Period_idPERIOD")
	private Period period;

	//bi-directional many-to-many association to Umetnost
	@ManyToMany(mappedBy="licnosts")
	private List<Umetnost> umetnosts;

	public Licnost() {
	}

	public int getIdLicnost() {
		return this.idLicnost;
	}

	public void setIdLicnost(int idLicnost) {
		this.idLicnost = idLicnost;
	}

	public String getGodina_rodjenja() {
		return this.godina_rodjenja;
	}

	public void setGodina_rodjenja(String godina_rodjenja) {
		this.godina_rodjenja = godina_rodjenja;
	}

	public String getGodina_smrti() {
		return this.godina_smrti;
	}

	public void setGodina_smrti(String godina_smrti) {
		this.godina_smrti = godina_smrti;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getMesto_rodjenja() {
		return this.mesto_rodjenja;
	}

	public void setMesto_rodjenja(String mesto_rodjenja) {
		this.mesto_rodjenja = mesto_rodjenja;
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

	public List<Umetnost> getUmetnosts() {
		return this.umetnosts;
	}

	public void setUmetnosts(List<Umetnost> umetnosts) {
		this.umetnosts = umetnosts;
	}

}