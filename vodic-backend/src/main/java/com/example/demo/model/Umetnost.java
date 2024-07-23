package com.example.demo.model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the umetnost database table.
 * 
 */
@Entity
@NamedQuery(name="Umetnost.findAll", query="SELECT u FROM Umetnost u")
public class Umetnost implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idUmetnost;

	private String naziv;

	private String opis;

	//bi-directional many-to-one association to LicnostDidUmetnost
	@OneToMany(mappedBy="umetnost")
	private List<LicnostDidUmetnost> licnostDidUmetnosts;

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

	public List<LicnostDidUmetnost> getLicnostDidUmetnosts() {
		return this.licnostDidUmetnosts;
	}

	public void setLicnostDidUmetnosts(List<LicnostDidUmetnost> licnostDidUmetnosts) {
		this.licnostDidUmetnosts = licnostDidUmetnosts;
	}

	public LicnostDidUmetnost addLicnostDidUmetnost(LicnostDidUmetnost licnostDidUmetnost) {
		getLicnostDidUmetnosts().add(licnostDidUmetnost);
		licnostDidUmetnost.setUmetnost(this);

		return licnostDidUmetnost;
	}

	public LicnostDidUmetnost removeLicnostDidUmetnost(LicnostDidUmetnost licnostDidUmetnost) {
		getLicnostDidUmetnosts().remove(licnostDidUmetnost);
		licnostDidUmetnost.setUmetnost(null);

		return licnostDidUmetnost;
	}

}