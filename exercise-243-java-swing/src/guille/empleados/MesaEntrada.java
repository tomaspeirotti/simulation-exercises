package guille.empleados;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Guillermo on 3/5/2017.
 */
public class MesaEntrada {
    private static List<EmpleadoMesaEntrada> empleadoMesaEntradaList;

    public static void inicializar(int cantEmpleados) {
        empleadoMesaEntradaList = new LinkedList<>();
        for (int i = 0; i < cantEmpleados; i++) {
            empleadoMesaEntradaList.add(new EmpleadoMesaEntrada());
        }
    }

    public static EmpleadoMesaEntrada getEmpleadoLibre() {
        for (EmpleadoMesaEntrada empleadoMesaEntrada :
                empleadoMesaEntradaList) {
            if (empleadoMesaEntrada.isLibre()) {
                return empleadoMesaEntrada;
            }
        }
        return null;
    }

    public static int getEmpleadosOcupados() {
        int cantEmpleadosOcupados = 0;
        for (EmpleadoMesaEntrada empleadoMesaEntrada : empleadoMesaEntradaList) {
            if (empleadoMesaEntrada.isOcupado()) {
                cantEmpleadosOcupados++;
            }
        }
        return cantEmpleadosOcupados;
    }

    public static Iterator<EmpleadoMesaEntrada> getEmpleadosIterator() {
        return empleadoMesaEntradaList.iterator();
    }

}
