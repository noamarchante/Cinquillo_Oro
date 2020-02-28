package core;

import core.Carta.Palo;
import java.util.ArrayList;

/**
 * Esta clase define la baraja de cartas
 * @author: Luciano Martín Gonzalez Van Gent
 * @version: 12/04/2018
 */
public class Baraja {

    private static ArrayList<Carta> baraja;
    private int numCartas;
    private final static int TOTALCARTAS = 48;
    
    /**
     * Constructor de la baraja.
     */
    public Baraja() {        
        baraja = new ArrayList(TOTALCARTAS);
        
        for (Palo p: Carta.Palo.values()) {
            for (int n = 1; n <= 12; n++) {
                baraja.add(new Carta(n,p));
            }
        }
    }

    /**
     * Método que devuelve la ultima carta de la baraja.
     * @return La ultima carta como Carta
     */
    public Carta quitarCarta() {
        return baraja.get(--numCartas);
    }

    /**
     * Método que "recoge" y baraja las cartas de la baraja.
     */
    public void barajar() {
        ArrayList<Carta> barajado = new ArrayList(TOTALCARTAS);
        ArrayList<Integer> numeros = numAleatorios();

        for (int i = 0; i < TOTALCARTAS; i++) {
            barajado.add(baraja.get(numeros.get(i))); 
        }
        
        baraja = barajado;
        numCartas = TOTALCARTAS;
    }

    private static ArrayList<Integer> numAleatorios() {
        java.util.Random rnd = new java.util.Random();
        
        ArrayList<Integer> ordenado = new ArrayList(TOTALCARTAS);
        ArrayList<Integer> aleatorio = new ArrayList(TOTALCARTAS);
        int aux;
        
        for (int i = 0; i < TOTALCARTAS; i++) {
            ordenado.add(i);
        }
        
        for (int i = 0; i < TOTALCARTAS; i++) {
            aux = rnd.nextInt(TOTALCARTAS - i);
            aleatorio.add(ordenado.get(aux));
            ordenado.remove(aux);
        }

        return aleatorio;
    }
}