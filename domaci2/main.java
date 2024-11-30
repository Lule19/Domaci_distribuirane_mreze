package com.mycompany.domaci22;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class main {
    public static void main(String[] args) throws InterruptedException {
        Skladiste skladiste = new Skladiste(10);
        String fajl = "raspored.txt";
        
        // Kreiramo i pokrecemo proizvodjace
        for (int i = 0; i < 20; i++) {
            new Proizvodjac(skladiste, 500, 1000).start();
        }
        
        // Kreiramo i pokrecemo potrosace
        for (int i = 0; i < 30; i++) {
            new Potrosac(skladiste, 500, 1000).start();
        }
        
        upisiRaspored(fajl);
        List<List<Integer>> rasporedi = ucitajRaspored(fajl);
        
        if (rasporedi.isEmpty()) {
            System.out.println("Raspored nije pronadjen. Generisem novi raspored...");
        } else {
            System.out.println("Raspored uspesno ucitan iz fajla.");
        }
        
        // Koristimo ExecutorService za paralelno pokretanje izveštača
        ExecutorService executor = Executors.newFixedThreadPool(3); // Možete podesiti broj niti po želji
        
        List<Izvestac> izvestaci = new ArrayList<>();
        for (int i = 0; i < rasporedi.size(); i++) {
            Izvestac izvestac = new Izvestac(skladiste, rasporedi.get(i));
            izvestaci.add(izvestac);
        }
        
        // Pokrećemo izveštače paralelno koristeći Executor
        for (Izvestac izvestac : izvestaci) {
            executor.submit(izvestac);
        }

        // Čekamo da svi izveštači završe
        executor.shutdown();
        while (!executor.isTerminated()) {
            // Možemo dodati logiku za čekanje svih niti da završe
        }

        // Pokrecemo Timer za ispis stanja skladišta
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // Ispis stanja skladišta
                /*
                System.out.println("Stanje skladista: " + skladiste.getStanje());
                */
            }
        }, 0, 5000); // Ispis svakih 5 sekundi (5000 milisekundi)
    }

    private static List<List<Integer>> ucitajRaspored(String fajl) {
        List<List<Integer>> rasporedi = new ArrayList<>();
        for (int i = 0; i < 3; i++) rasporedi.add(new ArrayList<>());
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(fajl));
            String linija;
            while ((linija = br.readLine()) != null) {
                String[] delovi = linija.split(" ");
                int vreme = Integer.parseInt(delovi[0]);
                int izvestacId = Integer.parseInt(delovi[1].replace("Izvestac", "")) - 1;
                rasporedi.get(izvestacId).add(vreme);
            }
            br.close();
        } catch (IOException e) {
            try {
                FileWriter writer = new FileWriter(fajl);
                for (int i = 0; i < 10; i++) {
                    int vreme = (int) (Math.random() * 10);
                    int izvestacId = (i % 3) + 1;
                    writer.write(vreme + " Izvestac" + izvestacId + "\n");
                }
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return rasporedi;
    }

    private static void upisiRaspored(String fajl) {
        Random random = new Random();
        try (FileWriter writer = new FileWriter(fajl)) {
            for (int i = 1; i <= 10; i++) {
                int izvestacBroj = random.nextInt(3) + 1;
                writer.write(i + " Izvestac" + izvestacBroj + "\n");
            }
            writer.flush();
            System.out.println("Raspored je uspesno generisan u fajlu: " + fajl);
        } catch (IOException e) {
            System.err.println("Greska prilikom pisanja fajla: " + e.getMessage());
        }
    }
}
