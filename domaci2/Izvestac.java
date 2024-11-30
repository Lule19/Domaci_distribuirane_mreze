package com.mycompany.domaci22;

import java.util.List;

public class Izvestac extends Thread {
	private static int statId = 0;
	private int id = ++statId;
	
	private Skladiste skladiste;
	private List<Integer> raspored;
	
	public Izvestac(Skladiste skladiste, List<Integer> raspored) {
		this.skladiste = skladiste;
		this.raspored = raspored;
	}

	 public void run() {
        try {
            for (int vreme : raspored) {
                sleep(vreme * 1000); // Pauza prema rasporedu

                synchronized (skladiste) {
                    // Ispis trenutnog stanja skladišta
                    System.out.println("Izvestac " + id + ": stanje skladišta: " + skladiste.getStanje());

                    // Ispis svih članova niza (proizvodi u skladištu), bez nula
                    System.out.print("Svi proizvodi u skladištu: ");
                    int[] niz = skladiste.getNiz(); // Dohvatimo niz proizvoda
                    boolean imaProizvoda = false;

                    for (int i = 0; i < niz.length; i++) {
                        if (niz[i] != 0) { // Ako član nije 0, ispisujemo ga
                            System.out.print(niz[i] + " ");
                            imaProizvoda = true;
                        }
                    }
                    
                    if (!imaProizvoda) {
                        System.out.print("Nema proizvoda u skladištu."); // Ako nema proizvoda (nema brojeva osim nula)
                    }
                    System.out.println(); // Nova linija nakon ispisa niza
                }
            }
        } catch (InterruptedException ex) {
            System.out.println("Izvestac " + id + " je zavrsio sa radom");
        }
    }
}
