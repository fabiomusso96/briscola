package briscola;

public class Carta{
    private int numero, valoreCarta;
    private String seme;
    public final static String[] semi = { "Coppe", "Denari", "Bastoni", "Spade" };

    public Carta(int numero, int valoreCarta, String seme){
        this.numero = numero;
        this.valoreCarta = valoreCarta;
        this.seme = seme;
    }

    public int getValoreCarta() { return valoreCarta; }
    public int getValore() { return numero; }
    public String getSeme() { return seme; }

    @Override
    public String toString() { return numero + " di " + seme; }
}