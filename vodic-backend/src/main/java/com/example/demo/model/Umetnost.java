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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;


/**
 * The persistent class for the Umetnost database table.
 * 
 */
@Entity
@NamedQuery(name="Umetnost.findAll", query="SELECT u FROM Umetnost u")
public class Umetnost implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idUmetnost;

	@Column(name="Naziv")
	private String naziv;

	@Column(name="Opis")
	private String opis;

	//bi-directional many-to-many association to Licnost
	@ManyToMany
	@JoinTable(
		name="Licnost_did_Umetnost"
		, joinColumns={
			@JoinColumn(name="Umetnost_idUmetnost")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Licnost_idLicnost")
			}
		)
	private List<Licnost> licnosts;

	public Umetnost() {
	}

	public int getIdUmetnost() {
		return this.idUmetnost;
	}

	public void setIdUmetnost(int idUmetnost) {
		this.idUmetnost = idUmetnost;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public List<Licnost> getLicnosts() {
		return this.licnosts;
	}

	public void setLicnosts(List<Licnost> licnosts) {
		this.licnosts = licnosts;
	}

}