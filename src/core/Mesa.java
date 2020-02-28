package core;

import java.util.*;

/**
 * Esta clase define la mesa del juego
 *
 * @author: Aarón Iglesias Mosteiro
 * @version: 25/03/2018
 */
public class Mesa {

    private static Deque<Carta>[] mesa; //cola de cartas que conforma la mesa
    private final static int NUMPALOS = 4;
    private final static int CINCO = 5;

    /**
     * Constructor que crea la mesa del juego.
     */
    public Mesa() {
        mesa = new Deque[NUMPALOS];
        for (int i = 0; i < NUMPALOS; i++) {//recorre los palos 
            mesa[i] = new ArrayDeque(12);//a cada palo le asigna una nueva cola de cartas 
        }
    }

    /**
     * Método que devuelve la mesa de la partida
     *
     * @return la mesa como array de Deques
     */
    public Deque<Carta>[] getMesa() {
        return mesa;
    }

    /**
     * Método que inserta cartas en la mesa
     *
     * @param carta la carta escogida
     */
    public void insertar(Carta carta) {
        if (carta.getNumero() > CINCO) {
            mesa[carta.getPalo().ordinal()].addLast(carta);//añade a un lado de la cola
        } else {
            mesa[carta.getPalo().ordinal()].addFirst(carta);//añade al otro lado de la cola
        }
    }

    /**
     * Método que devuelve true si se puede poner la carta en la mesa y false en
     * caso contrario
     *
     * @param carta carta que se intenta poner en la mesa
     * @return poner devuelve true si se puede poner la carta en la mesa y false
     * en caso contrario
     */
    public boolean sePuedePoner(Carta carta) {
        boolean poner = false;
        int n = carta.getNumero();
        Deque<Carta> palo = mesa[carta.getPalo().ordinal()];

        if (n == CINCO) {
            poner = true;
        } else if (palo.peek() != null) { // si el palo tiene cartas
            if (n > CINCO) {
                if (palo.peekLast().getNumero() == n - 1) {
                    poner = true;
                }
            } else {
                if (palo.peekFirst().getNumero() == n + 1) {
                    poner = true;
                }
            }
        }
        return poner;
    }
    
    /**
     * Método que devuelve un ArrayList con las cartas que se pueden poner en la mesa
     *
     * @param jug jugador que tiene las cartas
     * @return toret devuelve las cartas que se pueden poner
     */
    public ArrayList cartasPosibles(Jugador jug) {
        ArrayList<Integer> toret = new ArrayList();
        int j = 0;
        System.out.println("\nTus cartas posibles son:\n");
        for (int i = 0; i < jug.getNumCartas(); i++) {
            if (sePuedePoner(jug.getCarta(i))) {
                toret.add(i);
                System.out.print(++j + ". " + jug.getCarta(i).toString());
            }
        }
        return toret;
    }

    /**
     * Método que limpia la mesa después de jugar.
     */
    public void limpiar() {
        for (int i = 0; i < NUMPALOS; i++) {
            mesa[i].clear();
        }
    }

    /**
     * Método que devuelve un mensaje con las cartas que hay en la mesa
     * ordenadas por palo y número
     * @return Las cartas en columna ordenadas por palo y número con su propio color
     */
    public String toString() {
        StringBuilder toret = new StringBuilder();
        Iterator<Carta> aux;

        toret.append("\nMesa: ");
        for (int i = 0; i < NUMPALOS; i++) { 
            toret.append("\n");
              switch (i) {
                    case 0:
                        toret.append("\033[33m" ); //color amarillo para los oros
                        break;
                    case 1:
                        toret.append("\033[31m" ); // color rojo para copas
                        break;
                    case 2:
                        toret.append("\033[34m" ); // color azul para espadas
                        break;
                    case 3:
                        toret.append("\033[32m" ); // color verde para bastos
                        break;

                }
            aux = mesa[i].iterator(); //almacena en aux una colección de elementos separados por palo
            while (aux.hasNext()) {
                toret.append(aux.next());
            }
            toret.append("\033[30m" ); //se bugeaba
        }
        return toret.toString();
    }
  
}
