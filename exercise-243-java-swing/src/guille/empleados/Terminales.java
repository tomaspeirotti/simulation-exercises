package guille.empleados;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Guillermo on 3/5/2017.
 */
public class Terminales {
    private static List<Terminal> terminalList;

    public static void inicializar(int cantTerminales) {
        terminalList = new LinkedList<>();
        for (int i = 0; i < cantTerminales; i++) {
            terminalList.add(new Terminal());
        }
    }

    public static Terminal getTerminalLibre() {
        for (Terminal terminal :
                terminalList) {
            if (terminal.isLibre()) {
                return terminal;
            }
        }
        return null;
    }

    public static int getTerminalesOcupadas() {
        int cantTerminalesOcupadas = 0;
        for (Terminal terminal : terminalList) {
            if (terminal.isOcupado()) {
                cantTerminalesOcupadas++;
            }
        }
        return cantTerminalesOcupadas;
    }

    public static Iterator<Terminal> getTerminalesIterator() {
        return terminalList.iterator();
    }

    public static List<Terminal> getTerminales() {
        return terminalList;
    }
}
