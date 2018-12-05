package briscola;

import java.util.*;

public class Partita {
    Scanner scan = new Scanner(System.in);
    Tavolo tavolo;
    Giocatore cpu, utente;
    boolean giocaPerPrimoUtente = true;

    public Partita() throws Exception {
        int count = 0;
        tavolo = new Tavolo();
        cpu = new GiocatoreCPU();
        utente = new GiocatoreUtente();

        for(String seme : Carta.semi){
            for(int i = 1; i < 11; i++) {

                int valoreCarta = 0;

                if(i == 1)
                    valoreCarta = 11;
                else if (i == 3)
                    valoreCarta = 10;
                else if (i == 10)
                    valoreCarta = 4;
                else if (i == 9)
                    valoreCarta = 3;
                else if (i == 8)
                    valoreCarta = 2;

                tavolo.getMazzo().add(new Carta(i, valoreCarta, seme));
            }
        }

        Collections.shuffle(tavolo.getMazzo());

        while (count != 3) {
            cpu.carteInMano.add(tavolo.getMazzo().pop());
            count++;
        }

        count = 0;

        while (count != 3) {
            utente.carteInMano.add(tavolo.getMazzo().pop());
            count++;
        }

        count++;
        tavolo.setBriscola(tavolo.getMazzo().pop());
    }


    public void avvia() {
        
        int i = 0;

        while(tavolo.getMazzo().size() != 1) {

            System.out.println("\n" + "Carte rimanenti: " + tavolo.getMazzo().size() + "\n\n" + "Mio punteggio: " 
                + cpu.getPunti() + "\n" + "Tuo punteggio: " + utente.getPunti() + "\n\n" + "Le tue carte sono: " + "\n");

            for (i = 0; i < utente.carteInMano.size(); i++)
                System.out.print("[" + utente.carteInMano.get(i) + "]" + " ");

            System.out.print("\n\n" + "La briscola è: " + "[" + tavolo.getBriscola() + "]" + "\n\n");
            Carta cartaUtente, cartaCPU, cartaVincente;

            if (giocaPerPrimoUtente) {

                cartaUtente = eseguiTurnoUtente();
                cartaCPU = eseguiTurnoCPU();
                cartaVincente = chiPrende(cartaUtente, cartaCPU);
            }
            else {
                cartaCPU = eseguiTurnoCPU();
                cartaUtente = eseguiTurnoUtente();         
                cartaVincente = chiPrende(cartaCPU, cartaUtente);
            }

            if(cartaVincente.equals(cartaUtente)) {
                utente.prendi(cartaUtente);
                utente.prendi(cartaCPU);
                System.out.println("Prendi tu..." + "\n" + "---------------------------------------");
                giocaPerPrimoUtente = true;

                if(tavolo.getMazzo().size() == 1){
                    utente.pesca(tavolo.getBriscola());
                    cpu.pesca(tavolo.getMazzo().pop());
                } else {
                utente.pesca(tavolo.getMazzo().pop());
                cpu.pesca(tavolo.getMazzo().pop());
                }
            }
            else {
                cpu.prendi(cartaUtente);
                cpu.prendi(cartaCPU);
                System.out.println("Prendo io!" + "\n" + "\n" + "---------------------------------------");
                giocaPerPrimoUtente = false;

                if(tavolo.getMazzo().size() == 1){
                    cpu.pesca(tavolo.getBriscola());
                    utente.pesca(tavolo.getMazzo().pop());
                } else {
                cpu.pesca(tavolo.getMazzo().pop());
                utente.pesca(tavolo.getMazzo().pop());
                }
            }
        }

        if(tavolo.getMazzo().size() == 1){
            while(utente.getCarteInMano().size() != 0 && cpu.getCarteInMano().size() != 0){

                System.out.println("\n" + "Mio punteggio: " + cpu.getPunti() + "\n" + "Tuo punteggio: " 
                    + utente.getPunti() + "\n\n" + "Le tue carte sono: " + "\n");

                for (i = 0; i < utente.carteInMano.size(); i++)
                    System.out.print("[" + utente.carteInMano.get(i) + "]" + " ");

                Carta cartaUtente, cartaCPU, cartaVincente;

                if (giocaPerPrimoUtente) {

                    cartaUtente = eseguiTurnoUtente();
                    cartaCPU = eseguiTurnoCPU();
                    cartaVincente = chiPrende(cartaUtente, cartaCPU);
                }
                else {
                    cartaCPU = eseguiTurnoCPU();
                    cartaUtente = eseguiTurnoUtente();         
                    cartaVincente = chiPrende(cartaCPU, cartaUtente);
                }

                if(cartaVincente.equals(cartaUtente)) {
                    utente.prendi(cartaUtente);
                    utente.prendi(cartaCPU);
                    System.out.println("Prendi tu..." + "\n" + "---------------------------------------");
                    giocaPerPrimoUtente = true;

                    if(utente.getCarteInMano().size() == 0 && cpu.getCarteInMano().size() == 0){
                        return;
                    }

                    if(tavolo.getMazzo().size() == 1){
                        utente.pesca(tavolo.getBriscola());
                        cpu.pesca(tavolo.getMazzo().pop());
                    }
                }
                else {
                    cpu.prendi(cartaUtente);
                    cpu.prendi(cartaCPU);
                    System.out.println("Prendo io!" + "\n" + "\n" + "---------------------------------------");
                    giocaPerPrimoUtente = false;

                    if(cpu.getCarteInMano().size() == 0 && cpu.getCarteInMano().size() == 0){
                        return;
                    }
                    if(tavolo.getMazzo().size() == 1){
                        cpu.pesca(tavolo.getMazzo().pop());
                        utente.pesca(tavolo.getBriscola());
                        
                    }
                }
            }
        }
    }

    public Carta chiPrende(Carta c1, Carta c2) {
        if(c1.getSeme() == tavolo.getBriscola().getSeme() &&
           c2.getSeme() != tavolo.getBriscola().getSeme())
                return c1;
        else if (c1.getSeme() != tavolo.getBriscola().getSeme() &&
                c2.getSeme() == tavolo.getBriscola().getSeme())
                return c2;
        else if(c1.getSeme() == c2.getSeme())
            if(c1.getValoreCarta() > c2.getValoreCarta())
                return c1;
            else
                return c2;
        else
            return c1;

    }

    public Carta eseguiTurnoUtente() {
        int scelta = 0;
        Carta carta = null;
        System.out.print("Che vuoi fare?" 
        + "\n" + "(1) BUTTO UNA CARTA!" + "\n" + "(2) TERMINO LA PARTITA..." + "\n\n");

        try {
            System.out.print("Scelta: ");
            scelta = scan.nextInt();
        } catch (Exception e) {
            scan.nextLine();
        }

        if(scelta == 1){
            System.out.print("\n" + "Ok, quale?" + "\n\n");

            for(int i = 0; i < utente.carteInMano.size(); i++)
                System.out.print("(" + i + ") " + utente.carteInMano.get(i) + "  ");
            
            int sceltaCarta = scan.nextInt();
            carta = utente.scarta(sceltaCarta);
            System.out.println("Hai buttato " + carta + ".");
        }
        else if(scelta == 2)
            System.out.println("Peccato, mi stavo divertendo! :(" + "\n" + "Spero di giocare di nuovo con te un'altra volta! :)");
        else 
            System.out.println("Scelta non valida!");

        return carta;
    }

    public Carta eseguiTurnoCPU() {
        Carta carta = algoritmoIApotentissimo();
        cpu.scarta(cpu.getCarteInMano().indexOf(carta));
        System.out.println("Ho buttato " + carta + ".");
        return carta;
    }

    private Carta algoritmoIApotentissimo() {
        boolean buttaBriscola;
        Random random = new Random();

        if(cpu.getCarteInMano().stream().anyMatch(x -> x.getSeme() == tavolo.getBriscola().getSeme()))
        {
            buttaBriscola = random.nextBoolean();

            if(buttaBriscola)
                return cpu.getCarteInMano()
                          .stream()
                          .filter(x -> x.getSeme() == tavolo.getBriscola().getSeme())
                          .min((c1, c2) -> Integer.compare(c1.getValoreCarta(), c2.getValoreCarta()))
                          .get();
        }

        return cpu.getCarteInMano().get(0);
    }
}