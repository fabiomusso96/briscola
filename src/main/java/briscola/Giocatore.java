package briscola;

import java.util.*;

public abstract class Giocatore {
    protected ArrayList<Carta> carteInMano = new ArrayList<>();
    protected ArrayList<Carta> cartePrese = new ArrayList<>();

    
    public ArrayList<Carta> getCarteInMano() { return carteInMano; }
    public ArrayList<Carta> getCartePrese() { return cartePrese; }
    public int getPunti() { return cartePrese.stream().mapToInt(x -> x.getValoreCarta()).sum(); }


    public void pesca(Carta carta) {
        carteInMano.add(carta);
    }

    public void prendi(Carta carta) {
        cartePrese.add(carta);
    }

    public Carta scarta(int cartaDaScartare) {
        Carta carta = carteInMano.get(cartaDaScartare);
        carteInMano.remove(cartaDaScartare);
        return carta;
    }
}