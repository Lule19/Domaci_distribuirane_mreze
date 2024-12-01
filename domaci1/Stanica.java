package com.mycompany.domaci11;

public class Stanica {
	
	private static int idBrojac = 0;
	private int id;
	private Cenovnik cenovnik;
	private int naplacenaPutarina = 0;
	private int naplacenoOdPostavljanjaCenovnika;
	
	public Stanica(Cenovnik cenovnik) {
        this.id = idBrojac++;
        this.cenovnik = cenovnik;
    }
	
	public Cenovnik getCenovnik() {
		return cenovnik;
	}

	public void setCenovnik(Cenovnik cenovnik) {
		this.cenovnik = cenovnik;
		naplacenoOdPostavljanjaCenovnika = 0;
	}

	public Stanica kopirajStanicu() {
		return new Stanica(this.cenovnik);
	}
	
	public void naplatiPutarinu(Vozila vozilo) throws RuntimeException {
		if(cenovnik == null) {
			throw new NullPointerException();
		}
		this.naplacenaPutarina += this.cenovnik.getPutarina(vozilo.getKategorija());
		this.naplacenoOdPostavljanjaCenovnika += this.cenovnik.getPutarina(vozilo.getKategorija());
	}

	public int getIznosOdPostavljanja() {
		return this.naplacenoOdPostavljanjaCenovnika;
	}
	
	@Override
	public String toString() {
		return id + "(" + naplacenaPutarina + ")";
	}
	
}
