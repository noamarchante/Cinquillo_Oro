package core;

/**
 * Esta clase define los jugadores de la partida
 *
 * @author: Juan Manuel Fuentes Macia
 * @version: 02/04/2018
 */
public class Jugador {

    private final String nombre; //nombre del jugador
    private int puntos; //contador de puntos del jugador
    private Mano mano; //mano de cartas del jugador
    private int numCartas; //número de cartas que tiene la cartasMano del jugador (MAX 12)
    
    /**
     * Constructor de un jugador con los datos de su nombre, sus puntos y su
     * mano de cartas
     *
     * @param nombre define el nombre del jugador
     */
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.puntos = 0;
        mano = new Mano();
    }

    /**
     * Método que devuelve una String con el nombre de un jugador
     *
     * @return El nombre del jugador como String
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método que devuelve un int con los puntos acumulados del jugador
     *
     * @return Los puntos actuales del jugador
     */
    public int getPuntos() {
        return puntos;
    }

    /**
     * Método que acumula los puntos ganados por el jugador
     *
     * @param puntos El parámetro puntos define el número de puntos a
     * incrementar
     */
    public void setPuntos(int puntos) {
        this.puntos += puntos;
    }
    
    /**
    * Método que devuelve un entero con el número de cartas que tiene la
    * mano en cada jugada
    *
    * @return El número de cartas de la mano
    */
    public int getNumCartas() {
        return numCartas;
    }

    /**
    * Método que reinicia el número de cartas de la mano
    */
    public void limpiar() {
        this.numCartas = 0;
    }

    /**
     * Método que devuelve la carta seleccionada de la mano del jugador
     *
     * @param posicion selecciona la posición en la que se encuentra la
     * carta que quiere utilizar
     * @return La carta seleccionada por el jugador para su jugada
     */
    public Carta getCarta(int posicion) {
        return mano.cartasMano[posicion];
    }

    /**
     * Método que elimina de la mano del jugador la carta seleccionada por
     * este para utilizarla en su jugada
     *
     * @param posicion El parámetro posicion define el lugar que ocupa la
     * carta seleccionada en la mano del jugador
     */
    public void eliminarCarta(int posicion) {
        mano.cartasMano[posicion] = mano.cartasMano[--numCartas];
    }

    /**
     * Método que modifica la mano del jugador al repartir las cartas
     *
     * @param carta El parámetro carta define la carta de la baraja que se
     * reparte a judador
     */
    public void setMano(Carta carta) {
        mano.cartasMano[numCartas] = carta;
        numCartas++;
    }
        
    /**
     * Método que devuelve un mensaje con el nombre del jugador y sus puntos o
     * las cartas de su mano.
     *
     * @param opcion
     * @return si opcion == 0 devuelve el nombre del jugador y sus puntos
     *         si opcion == 1 devuelve las cartas de su mano
     */
    public String toString(int opcion) {
        StringBuilder toret = new StringBuilder();
        
        switch(opcion){
            case 0:
                toret.append(getNombre()).append(" con ").append(getPuntos()).append(" puntos.");
                break;
            case 1:
                toret.append(mano);
                break;
        }
        return toret.toString();
    }

    /**
     * Esta clase define la cartasMano del jugador
     *
     * @author: Juan Manuel Fuentes Macia
     * @version: 2/04/2018
     */
    public class Mano {

        private Carta[] cartasMano; //array que almacena las cartas de la mano

        /**
         * Constructor para la Mano de cartas del jugador, con los datos de
         * cartas de la mano y número de cartas
         */
        public Mano() {
            cartasMano = new Carta[12];
        }

        /**
         * Método que devuelve un mensaje con las cartas del jugador
         *
         * @return posición de la carta en la mano: carta de esa posición
         */
        public String toString() {
            StringBuilder toret = new StringBuilder();

            toret.append("\nMano:");
            for (int i = 0; i < getNumCartas(); i++) {
                if (i % 3 == 0) {
                    toret.append("\n");
                }
                toret.append(cartasMano[i]);
            }

            return toret.toString();
        }
    }
}
