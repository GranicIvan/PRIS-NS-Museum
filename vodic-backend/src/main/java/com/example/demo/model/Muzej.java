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


/**
 * The persistent class for the Muzej database table.
 * 
 */
@Entity
@NamedQuery(name="Muzej.findAll", query="SELECT m FROM Muzej m")
public class Muzej implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPERIOD;

	@Column(name="Adresa")
	private String adresa;

	@Column(name="Koordinate")
	private String koordinate;

	@Column(name="Kratak_opis")
	private String kratak_opis;

	@Column(name="Maps_deo_za_link")
	private String maps_deo_za_link;

	@Column(name="Maps_link")
	private String maps_link;

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

	//bi-directional many-to-many association to Period
	@ManyToMany(mappedBy="muzejs")
	private List<Period> periods;

	//bi-directional many-to-many association to Ruta
	@ManyToMany
	@JoinTable(
		name="Ruta_has_Muzej"
		, joinColumns={
			@JoinColumn(name="Muzej_idPERIOD")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Ruta_idRuta")
			}
		)
	private List<Ruta> rutas;

	public Muzej() {
	}

	public int getIdPERIOD() {
		return this.idPERIOD;
	}

	public void setIdPERIOD(int idPERIOD) {
		this.idPERIOD = idPERIOD;
	}

	public String getAdresa() {
		return this.adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getKoordinate() {
		return this.koordinate;
	}

	public void setKoordinate(String koordinate) {
		this.koordinate = koordinate;
	}

	public String getKratak_opis() {
		return this.kratak_opis;
	}

	public void setKratak_opis(String kratak_opis) {
		this.kratak_opis = kratak_opis;
	}

	public String getMaps_deo_za_link() {
		return this.maps_deo_za_link;
	}

	public void setMaps_deo_za_link(String maps_deo_za_link) {
		this.maps_deo_za_link = maps_deo_za_link;
	}

	public String getMaps_link() {
		return this.maps_link;
	}

	public void setMaps_link(String maps_link) {
		this.maps_link = maps_link;
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

	public List<Period> getPeriods() {
		return this.periods;
	}

	public void setPeriods(List<Period> periods) {
		this.periods = periods;
	}

	public List<Ruta> getRutas() {
		return this.rutas;
	}

	public void setRutas(List<Ruta> rutas) {
		this.rutas = rutas;
	}

}