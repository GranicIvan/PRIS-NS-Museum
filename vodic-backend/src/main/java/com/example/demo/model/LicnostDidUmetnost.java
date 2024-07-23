package com.example.demo.model;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the licnost_did_umetnost database table.
 * 
 */
@Entity
@Table(name="licnost_did_umetnost")
@NamedQuery(name="LicnostDidUmetnost.findAll", query="SELECT l FROM LicnostDidUmetnost l")
public class LicnostDidUmetnost implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private LicnostDidUmetnostPK id;

	@Column(name="licnost_id_licnost")
	private int licnostIdLicnost;

	@Column(name="umetnost_id_umetnost")
	private int umetnostIdUmetnost;

	//bi-directional many-to-one association to Licnost
	@ManyToOne
	private Licnost licnost;

	//bi-directional many-to-one association to Umetnost
	@ManyToOne
	private Umetnost umetnost;

	public LicnostDidUmetnost() {
	}

	public LicnostDidUmetnostPK getId() {
		return this.id;
	}

	public void setId(LicnostDidUmetnostPK id) {
		this.id = id;
	}

	public int getLicnostIdLicnost() {
		return this.licnostIdLicnost;
	}

	public void setLicnostIdLicnost(int licnostIdLicnost) {
		this.licnostIdLicnost = licnostIdLicnost;
	}

	public int getUmetnostIdUmetnost() {
		return this.umetnostIdUmetnost;
	}

	public void setUmetnostIdUmetnost(int umetnostIdUmetnost) {
		this.umetnostIdUmetnost = umetnostIdUmetnost;
	}

	public Licnost getLicnost() {
		return this.licnost;
	}

	public void setLicnost(Licnost licnost) {
		this.licnost = licnost;
	}

	public Umetnost getUmetnost() {
		return this.umetnost;
	}

	public void setUmetnost(Umetnost umetnost) {
		this.umetnost = umetnost;
	}

}