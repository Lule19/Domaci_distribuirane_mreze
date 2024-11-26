package com.mycompany.domaci2;

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
                sleep(vreme * 1000); 
                synchronized (skladiste) {
                    System.out.println("Izvestac " + id + ": stanje skladista: " + skladiste.getStanje());
                }
            }
        } catch (InterruptedException ex) {
            System.out.println("Izvestac " + id + " je zavrsio sa radom");
        }
    }
}
