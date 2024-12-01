package com.mycompany.domaci11;

public class Main {
    public static void main(String[] args) {

        String nazivRampe = "Test rampa";
        double tsr = 2.0;

        // Definisanje cenovnika
        int[] cene1 = {100, 200, 300};
        Cenovnik cenovnik1 = new Cenovnik(cene1);

        // Prva rampa (3 stanice)
        Stanica teststanica1 = new Stanica(cenovnik1);
        Rampa rampa1 = new Rampa(nazivRampe + " 1", 3, teststanica1, tsr);

        // Druga rampa (4 stanice)
        Stanica teststanica2 = new Stanica(cenovnik1);
        Rampa rampa2 = new Rampa(nazivRampe + " 2", 4, teststanica2, tsr);

        // Treća rampa (5 stanica)
        Stanica teststanica3 = new Stanica(cenovnik1);
        Rampa rampa3 = new Rampa(nazivRampe + " 3", 5, teststanica3, tsr);

        // Četvrta rampa (6 stanica)
        Stanica teststanica4 = new Stanica(cenovnik1);
        Rampa rampa4 = new Rampa(nazivRampe + " 4", 6, teststanica4, tsr);

        // Otvaranje rampe 1 s novim cenovnikom
        int[] cene2 = {120, 220, 320};
        Cenovnik cenovnik2 = new Cenovnik(cene2);
        System.out.println("Otvaranje rampe 1");
        rampa1.otvori(cenovnik2);

        // Otvaranje ostalih rampi s početnim cenovnikom
        System.out.println("Otvaranje ostalih rampi");
        rampa2.otvori(cenovnik1);
        rampa3.otvori(cenovnik1);
        rampa4.otvori(cenovnik1);

        // Pauza od 10 sekundi da simulira rad rampi
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            System.err.println("Glavni thread prekinut: " + e.getMessage());
        }

        // Prikaz stanja rampi
        System.out.println("Stanje rampe 1 nakon 10 sekundi rada:");
        System.out.println(rampa1);
        System.out.println("Stanje rampe 2:");
        System.out.println(rampa2);
        System.out.println("Stanje rampe 3:");
        System.out.println(rampa3);
        System.out.println("Stanje rampe 4:");
        System.out.println(rampa4);

        // Zatvaranje rampe 1
        System.out.println("Zatvaranje rampe 1");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.err.println("Glavni thread prekinut: " + e.getMessage());
        }
        System.out.println("Stanje rampe 1 nakon zatvaranja:");
        System.out.println(rampa1);

        // Uništavanje rampi
        System.out.println("Uništavanje rampi...");
        rampa1.unisti();
        rampa2.unisti();
        rampa3.unisti();
        rampa4.unisti();
        System.out.println("Gotovo izvršavanje programa.");
    }
}
