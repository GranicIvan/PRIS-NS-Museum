package com.example.demo.model;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * The primary key class for the ruta_has_muzej database table.
 * 
 */
@Embeddable
public class RutaHasMuzejPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int ruta_idRuta;

	@Column(insertable=false, updatable=false)
	private int muzej_idPERIOD;

	public RutaHasMuzejPK() {
	}
	public int getRuta_idRuta() {
		return this.ruta_idRuta;
	}
	public void setRuta_idRuta(int ruta_idRuta) {
		this.ruta_idRuta = ruta_idRuta;
	}
	public int getMuzej_idPERIOD() {
		return this.muzej_idPERIOD;
	}
	public void setMuzej_idPERIOD(int muzej_idPERIOD) {
		this.muzej_idPERIOD = muzej_idPERIOD;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RutaHasMuzejPK)) {
			return false;
		}
		RutaHasMuzejPK castOther = (RutaHasMuzejPK)other;
		return 
			(this.ruta_idRuta == castOther.ruta_idRuta)
			&& (this.muzej_idPERIOD == castOther.muzej_idPERIOD);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.ruta_idRuta;
		hash = hash * prime + this.muzej_idPERIOD;
		
		return hash;
	}
}