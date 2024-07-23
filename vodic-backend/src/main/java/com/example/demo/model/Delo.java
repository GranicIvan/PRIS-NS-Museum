package com.example.demo.model;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the delo database table.
 * 
 */
@Entity
@NamedQuery(name="Delo.findAll", query="SELECT d FROM Delo d")
public class Delo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPERIOD;

	@Column(name="godina_nastanka")
	private String godinaNastanka;

	@Column(name="kratki_opis")
	private String kratkiOpis;

	@Column(name="licnost_id_licnost")
	private int licnostIdLicnost;

	private String naziv;

	@Lob
	private String txt0;

	@Lob
	private String txt1;

	@Lob
	private String txt2;

	//bi-directional many-to-one association to Licnost
	@ManyToOne
	private Licnost licnost;

	//bi-directional many-to-one association to Period
	@ManyToOne
	private Period period;

	public Delo() {
	}

	public int getIdPERIOD() {
		return this.idPERIOD;
	}

	public void setIdPERIOD(int idPERIOD) {
		this.idPERIOD = idPERIOD;
	}

	public String getGodinaNastanka() {
		return this.godinaNastanka;
	}

	public void setGodinaNastanka(String godinaNastanka) {
		this.godinaNastanka = godinaNastanka;
	}

	public String getKratkiOpis() {
		return this.kratkiOpis;
	}

	public void setKratkiOpis(String kratkiOpis) {
		this.kratkiOpis = kratkiOpis;
	}

	public int getLicnostIdLicnost() {
		return this.licnostIdLicnost;
	}

	public void setLicnostIdLicnost(int licnostIdLicnost) {
		this.licnostIdLicnost = licnostIdLicnost;
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

	public Licnost getLicnost() {
		return this.licnost;
	}

	public void setLicnost(Licnost licnost) {
		this.licnost = licnost;
	}

	public Period getPeriod() {
		return this.period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

}