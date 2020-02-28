package IU;

import java.util.Scanner;

public class ES {

    /**
     * Lee un num. de teclado
     *
     * @param msg El mensaje a visualizar.
     * @return El num., como entero
     */
    public static int leeNum(String msg) {
        boolean repite;
        int toret = 0;
        Scanner teclado = new Scanner(System.in);

        do {
            repite = false;
            System.out.print(msg);

            try {
                toret = Integer.parseInt(teclado.nextLine());
            } catch (NumberFormatException exc) {
                repite = true;
            }
        } while (repite);

        return toret;
    }

    /**
     * Lee una cadena de texto de teclado
     *
     * @param msg El mensaje a visualizar.
     * @return El mensaje., como cadena
     */
    public static String leeString(String msg) {
        String toret = "";
        Scanner teclado = new Scanner(System.in);

        System.out.print(msg);

        toret = teclado.nextLine();

        return toret;
    }

}
