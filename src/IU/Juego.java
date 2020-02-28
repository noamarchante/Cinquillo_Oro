package IU;

import core.*;
import java.util.ArrayList;

/**
 * Esta clase define como se va a desarrollar el juego
 *
 * @author: Noa López Marchante
 * @version: 02/04/2018
 */
public class Juego {

    private static final int MAXJUGADORES = 4;
    private static final int PUNTOSPARTIDA = 4;
    private static final int MAXMANO = 12;

    private static Mesa mesa;
    private static Baraja baraja;

    private static Jugador[] jugador;

    private static int puntosOro;

    /**
     * Método que organiza las acciones durante el juego
     */
    public static void inicioJuego() {
        mesa = new Mesa(); //crea la mesa de juego
        baraja = new Baraja(); //crea la baraja
        jugador = new Jugador[MAXJUGADORES];  //crea el array de jugadores

        int jugAct; //jugador actual (número deljugador en el array)

        crearJugadores(); //crea los jugadores de la partida

        do { //bucle de partidas
            setPuntosOro(); //establece el valor de los puntos oro en cada partida
            baraja.barajar(); //"recoge" y baraja la cartas
            repartirCartas(); //se reparten las cartas entre los jugadores
            jugAct = -1;//empieza el primer jugador

            do { //bucle de turnos
                jugAct ++; //jugador siguiente
                if (jugAct == MAXJUGADORES) {
                    jugAct = 0;
                }
                turno(jugAct); //turno del jugador actual

            } while (!(jugador[jugAct].getNumCartas() == 0));
        } while (!finPartida(jugAct).trim().equalsIgnoreCase("no"));

        finJuego(); //finaliza el juego y muestra información
    }

    private static void setPuntosOro() {
        Juego.puntosOro += 2;
    }

    private static void turno(int jugadorAct) {
        System.out.println("\n••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••");
        ES.leeString("\nTurno de " + jugador[jugadorAct].getNombre() + ": \033[37m(Pulsa Enter para continuar)");
        System.out.println(jugador[jugadorAct].toString(1));
        System.out.println(mesa.toString());

        ArrayList<Integer> posibles = mesa.cartasPosibles(jugador[jugadorAct]);
        if (posibles.isEmpty()) {
            System.out.println("No tienes cartas para poner en la mesa, pasas turno.");
        }
        else{
            int pos;
            do {
                pos = ES.leeNum("\nEscoge una carta de tu mano: ") -1;
                
            } while (pos<0 || pos>=posibles.size() );
            pos = posibles.get(pos);
            
            mesa.insertar(jugador[jugadorAct].getCarta(pos));
            puntuacionOro(jugadorAct, pos);
            jugador[jugadorAct].eliminarCarta(pos);
        }
    }

    private static void puntuacionOro(int jug, int carta) {
        if (jugador[jug].getCarta(carta).toString().trim().equals("As de oros")) {
            jugador[jug].setPuntos(puntosOro);
            puntosOro = 0;
        }
    }

    private static void repartirCartas() {
        for (int j = 0; j < MAXJUGADORES; j++) {//recorre los jugadores
            jugador[j].limpiar(); //resetea el número de cartas a 0
            for (int i = 0; i < MAXMANO; i++) {//recorre la mano
                jugador[j].setMano(baraja.quitarCarta());
            }
        }
    }

    private static void crearJugadores() {
        String nombre;
        for (int i = 0; i < MAXJUGADORES; i++) {
            do {
                nombre = ES.leeString("Jugador " + (i + 1) + " introduce tu nombre: ");
                jugador[i] = new Jugador(nombre);
            } while (nombre.trim().equals(""));
        }
    }

    private static String finPartida(int jug) { //dice quien es el ganador, asigna puntos, limpia la mesa y devuelve un string
        String msg;

        System.out.println("\nEl ganador de la partida es: " + jugador[jug].getNombre() + " VIVAAA‼‼");
        jugador[jug].setPuntos(PUNTOSPARTIDA);
        mesa.limpiar();

        do {
            msg = ES.leeString("\nQuieres jugar otra partida (si/no) ? ");
        } while (!msg.equalsIgnoreCase("si") && !msg.equalsIgnoreCase("no"));

        return msg;
    }

    private static void finJuego() {
        int mayor=0;
        
        System.out.println("\n\n¡¡ FIN DEL JUEGO !!");
        System.out.println("\nTABLA DE PUNTUACIONES:");
        for (int i = 0; i < MAXJUGADORES; i++) {//recorre el numero de jugadores y en cada vuelta establece los puntos a 0
            System.out.println(jugador[i].toString(0));       
            if(mayor<jugador[i].getPuntos()){
                mayor=jugador[i].getPuntos();
            }
        }
        
        System.out.println("\nEl/Los ganador/es es/son:");
        for (int i = 0; i < MAXJUGADORES; i++) {
            if(mayor==jugador[i].getPuntos()){
                System.out.println(jugador[i].getNombre());
            }
        }
    }
}
