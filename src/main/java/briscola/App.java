package briscola;

import java.util.*;

public final class App {
    private App() {
    }

    public static void main(String[] args) throws Exception{
        Giocatore cpu = new GiocatoreCPU(), utente = new GiocatoreUtente();
        Scanner scan = new Scanner(System.in);
        Partita partita;

        int scelta = 0;

        System.out.println("\n\n" + "---------- BRISCOLA ----------" 
            + "\n\n" + "Benvenuto nella briscola!");
        
        while(scelta != -1){
            System.out.println("Vuoi iniziare una nuova partita?" + "\n\n" + "(1) SI!" + "\n" + "(2) NO, ESCI...");

            try {
                System.out.print("Scelta: ");
                scelta = scan.nextInt();
            } catch (Exception e) {
            scan.nextLine();
            }

            if (scelta == 1) {
                partita = new Partita();
                partita.avvia();

                if(utente.getPunti() > cpu.getPunti())
                    System.out.println("Hai vinto tu, bravo!" + "\n\n" + "\n\n" + "---------------------------------------");
                else
                    System.out.println("Ho vinto io! :D" + "\n\n" + "---------------------------------------");
                
            } else
                return;
        }
    }
}
