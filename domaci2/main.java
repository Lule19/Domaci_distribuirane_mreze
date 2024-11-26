/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.domaci2;

/**
 *
 * @author W10
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class main {
    public static void main(String[] args) {
    
        Skladiste skladiste = new Skladiste(10);
        String fajl = "./raspored.txt";
        
        for (int i = 0; i < 20; i++) {
            new Proizvodjac(skladiste, 500, 1000).start();
        }
        
        for(int i = 0; i < 30; i++) {
            new Potrosac(skladiste, 500, 1000).start();
        }
        
        upisiRaspored(fajl);
        List<List<Integer>> rasporedi = ucitajRaspored(fajl);
        
        if (rasporedi.isEmpty()) {
            System.out.println("Raspored nije pronadjen. Generisem novi raspored...");
        } else {
            System.out.println("Raspored uspesno ucitan iz fajla.");
        }
        
        for(int i = 0; i < rasporedi.size(); i++) {
            new Izvestac(skladiste, rasporedi.get(i)).start();
        }
    }
    
    private static List<List<Integer>> ucitajRaspored(String fajl){
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
                for (int i = 0; i < 100; i++) {
                    int vreme = (int) (Math.random() * 100);
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
            for (int i = 1; i <= 100; i++) {
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