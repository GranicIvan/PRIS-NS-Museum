package com.example.demo.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;


/**
 * The persistent class for the Delo database table.
 * 
 */
@Entity
@NamedQuery(name="Delo.findAll", query="SELECT d FROM Delo d")
public class Delo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPERIOD;

	@Column(name="Godina_nastanka")
	private String godina_nastanka;

	@Column(name="Kratki_opis")
	private String kratki_opis;

	@Column(name="Naziv")
	private String naziv;

	@Lob
	@Column(name="TXT0")
	private String txt0;

	@Lob
	@Column(name="TXT1")
	private String txt1;

	@Lob
	@Column(name="TXT2")
	private String txt2;

	//bi-directional many-to-one association to Period
	@ManyToOne
	@JoinColumn(name="Period_idPERIOD")
	private Period period;

	//bi-directional many-to-one association to Licnost
	@ManyToOne
	@JoinColumn(name="Licnost_idLicnost")
	private Licnost licnost;

	public Delo() {
	}

	public int getIdPERIOD() {
		return this.idPERIOD;
	}

	public void setIdPERIOD(int idPERIOD) {
		this.idPERIOD = idPERIOD;
	}

	public String getGodina_nastanka() {
		return this.godina_nastanka;
	}

	public void setGodina_nastanka(String godina_nastanka) {
		this.godina_nastanka = godina_nastanka;
	}

	public String getKratki_opis() {
		return this.kratki_opis;
	}

	public void setKratki_opis(String kratki_opis) {
		this.kratki_opis = kratki_opis;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
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

	public Period getPeriod() {
		return this.period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

	public Licnost getLicnost() {
		return this.licnost;
	}

	public void setLicnost(Licnost licnost) {
		this.licnost = licnost;
	}

}