package com.example.demo.model;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * The primary key class for the licnost_did_umetnost database table.
 * 
 */
@Embeddable
public class LicnostDidUmetnostPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int licnost_idLicnost;

	@Column(insertable=false, updatable=false)
	private int umetnost_idUmetnost;

	public LicnostDidUmetnostPK() {
	}
	public int getLicnost_idLicnost() {
		return this.licnost_idLicnost;
	}
	public void setLicnost_idLicnost(int licnost_idLicnost) {
		this.licnost_idLicnost = licnost_idLicnost;
	}
	public int getUmetnost_idUmetnost() {
		return this.umetnost_idUmetnost;
	}
	public void setUmetnost_idUmetnost(int umetnost_idUmetnost) {
		this.umetnost_idUmetnost = umetnost_idUmetnost;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof LicnostDidUmetnostPK)) {
			return false;
		}
		LicnostDidUmetnostPK castOther = (LicnostDidUmetnostPK)other;
		return 
			(this.licnost_idLicnost == castOther.licnost_idLicnost)
			&& (this.umetnost_idUmetnost == castOther.umetnost_idUmetnost);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.licnost_idLicnost;
		hash = hash * prime + this.umetnost_idUmetnost;
		
		return hash;
	}
}