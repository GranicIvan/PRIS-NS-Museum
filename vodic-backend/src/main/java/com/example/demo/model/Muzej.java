package com.example.demo.model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the muzej database table.
 * 
 */
@Entity
@NamedQuery(name="Muzej.findAll", query="SELECT m FROM Muzej m")
public class Muzej implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPERIOD;

	private String adresa;

	private String koordinate;

	@Column(name="kratak_opis")
	private String kratakOpis;

	@Column(name="maps_deo_za_link")
	private String mapsDeoZaLink;

	@Column(name="maps_link")
	private String mapsLink;

	private String naziv;

	@Lob
	private String txt0;

	@Lob
	private String txt1;

	@Lob
	private String txt2;

	//bi-directional many-to-many association to Period
	@ManyToMany(mappedBy="muzejs")
	private List<Period> periods;

	//bi-directional many-to-one association to RutaHasMuzej
	@OneToMany(mappedBy="muzej")
	private List<RutaHasMuzej> rutaHasMuzejs;

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

	public String getKratakOpis() {
		return this.kratakOpis;
	}

	public void setKratakOpis(String kratakOpis) {
		this.kratakOpis = kratakOpis;
	}

	public String getMapsDeoZaLink() {
		return this.mapsDeoZaLink;
	}

	public void setMapsDeoZaLink(String mapsDeoZaLink) {
		this.mapsDeoZaLink = mapsDeoZaLink;
	}

	public String getMapsLink() {
		return this.mapsLink;
	}

	public void setMapsLink(String mapsLink) {
		this.mapsLink = mapsLink;
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

	public List<RutaHasMuzej> getRutaHasMuzejs() {
		return this.rutaHasMuzejs;
	}

	public void setRutaHasMuzejs(List<RutaHasMuzej> rutaHasMuzejs) {
		this.rutaHasMuzejs = rutaHasMuzejs;
	}

	public RutaHasMuzej addRutaHasMuzej(RutaHasMuzej rutaHasMuzej) {
		getRutaHasMuzejs().add(rutaHasMuzej);
		rutaHasMuzej.setMuzej(this);

		return rutaHasMuzej;
	}

	public RutaHasMuzej removeRutaHasMuzej(RutaHasMuzej rutaHasMuzej) {
		getRutaHasMuzejs().remove(rutaHasMuzej);
		rutaHasMuzej.setMuzej(null);

		return rutaHasMuzej;
	}

}