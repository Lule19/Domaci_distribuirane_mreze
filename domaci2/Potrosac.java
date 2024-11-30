
package com.mycompany.domaci22;


public class Potrosac extends Thread{
    private static int statId=0;
    private int id = ++statId;
    
    private Skladiste skladiste;
    private int minTime;
    private int maxTime;

    public Potrosac(Skladiste skladiste, int minTime, int maxTime) {
        this.skladiste = skladiste;
        this.minTime = minTime;
        this.maxTime = maxTime;
    }
    
   public void run() {
	/*	
       System.out.println("Potrosac " + id + " je krenuo sa radom");
        */
		try {
	        while(!interrupted()){
                int trajanje = minTime + (int) (Math.random() * (maxTime - minTime));
                sleep(trajanje);
                int proizvod = skladiste.Uzmi();
                /*
                System.out.println("Potrosac " + id + " je uzeo proizvod " + proizvod);
                */
            }
        } catch (InterruptedException ex) {
            /*
            System.out.println("Potrosac " + id + " je zavrsio sa radom");
            */
        }
	}
    
}
