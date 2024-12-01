package com.mycompany.domaci11;

public class Vozila implements Kategorizovano{
	
	private int kategorija;
	
	@Override
	public int getKategorija() {
		return this.kategorija;
	}

	public void setKategorija(int kategorija) {
		this.kategorija = kategorija;
	}

}
