package com.example.demo.model;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the ruta_has_muzej database table.
 * 
 */
@Entity
@Table(name="ruta_has_muzej")
@NamedQuery(name="RutaHasMuzej.findAll", query="SELECT r FROM RutaHasMuzej r")
public class RutaHasMuzej implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RutaHasMuzejPK id;

	@Column(name="ruta_id_ruta")
	private int rutaIdRuta;

	//bi-directional many-to-one association to Muzej
	@ManyToOne
	private Muzej muzej;

	//bi-directional many-to-one association to Ruta
	@ManyToOne
	private Ruta ruta;

	public RutaHasMuzej() {
	}

	public RutaHasMuzejPK getId() {
		return this.id;
	}

	public void setId(RutaHasMuzejPK id) {
		this.id = id;
	}

	public int getRutaIdRuta() {
		return this.rutaIdRuta;
	}

	public void setRutaIdRuta(int rutaIdRuta) {
		this.rutaIdRuta = rutaIdRuta;
	}

	public Muzej getMuzej() {
		return this.muzej;
	}

	public void setMuzej(Muzej muzej) {
		this.muzej = muzej;
	}

	public Ruta getRuta() {
		return this.ruta;
	}

	public void setRuta(Ruta ruta) {
		this.ruta = ruta;
	}

}