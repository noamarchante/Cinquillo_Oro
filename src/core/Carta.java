package core;

/**
 * Esta clase define una carta
 * @author: Luciano Martín Gonzalez Van Gent
 * @version: 12/04/2018
 */
public class Carta {

    public static enum Palo { OROS, COPAS, ESPADAS, BASTOS };
    private final int numero;
    private final Palo palo;

    /**
     * Constructor de la carta con los datos de número y palo
     * @param numero El parámetro numero define el número de la carta
     * @param palo El parámetro palo define el palo de la baraja
     */
    public Carta(int numero,Palo palo) {
        this.numero = numero;
        this.palo = palo;
    }

    /**
     * Método que devuelve un entero del número de la carta
     * @return El número de la carta como entero
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Método que devuelve el palo de la carta
     * @return El palo de la carta como enumerado
     */
    public Palo getPalo() {
        return palo;
    }

    /**
     * Método que devuelve un string con el nombre de la carta
     * @return el nombre de la carta como String
     */
    public String toString() {
        StringBuilder toret = new StringBuilder();
        final int MAXCHAR = 19;
        
        switch (getNumero()) {
            case 1:
                toret.append("As");
                break;
            case 2: case 3: case 4: case 5: case 6: case 7: case 8: case 9:
                toret.append(getNumero());
                break;
            case 10:
                toret.append("Sota");
                break;
            case 11:
                toret.append("Caballo");
                break;
            case 12:
                toret.append("Rey");
                break;
        }
        toret.append(" de ").append(getPalo().toString().toLowerCase());

        while (toret.length() < MAXCHAR) { //añade espacios para igual el tamaño del string de cada carta
            toret.append(" ");
        }

        return toret.toString();
    }
}