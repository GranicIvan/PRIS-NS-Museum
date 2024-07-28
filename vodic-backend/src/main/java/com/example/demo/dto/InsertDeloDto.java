package com.example.demo.dto;

public class InsertDeloDto {
	private int idPERIOD;
	private String godina_nastanka;
	private String kratki_opis;
	private String naziv;
	private int licnostId;
	private int periodId;
	
	public InsertDeloDto() {
		super();
	}
	public int getIdPERIOD() {
		return idPERIOD;
	}
	public void setIdPERIOD(int idPERIOD) {
		this.idPERIOD = idPERIOD;
	}
	public String getGodina_nastanka() {
		return godina_nastanka;
	}
	public void setGodina_nastanka(String godina_nastanka) {
		this.godina_nastanka = godina_nastanka;
	}
	public String getKratki_opis() {
		return kratki_opis;
	}
	public void setKratki_opis(String kratki_opis) {
		this.kratki_opis = kratki_opis;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public int getLicnostId() {
		return licnostId;
	}
	public void setLicnostId(int licnostId) {
		this.licnostId = licnostId;
	}
	public int getPeriodId() {
		return periodId;
	}
	public void setPeriodId(int periodId) {
		this.periodId = periodId;
	}

}
