package com.mycompany.domaci11;

public class Cenovnik {

	private int[] putarine;
	
	public Cenovnik(int[] putarine) {
		this.putarine = putarine;
	}
	
	public int getBrojKategorija() {
		return putarine.length;
	}
	
	public int getPutarina(int kategorija) throws IllegalArgumentException {
		if(kategorija < 1 || kategorija > putarine.length) {
			throw new IllegalArgumentException("Kategorija ne postoji");
		}
		return putarine[kategorija-1];
	}
}
