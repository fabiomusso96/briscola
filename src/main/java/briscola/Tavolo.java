package briscola;

import java.util.*;

public class Tavolo{
    private Carta briscola;
    private ArrayList<Carta> carteSulTavolo = new ArrayList<>();
    private Stack<Carta> mazzo = new Stack<>();
    
    public ArrayList<Carta> getCarteSulTavolo() { return carteSulTavolo; }
    public Stack<Carta> getMazzo() { return mazzo; }
    public Carta getBriscola() { return briscola; }

    public void setBriscola(Carta briscola) {
        this.briscola = briscola;
    }
}