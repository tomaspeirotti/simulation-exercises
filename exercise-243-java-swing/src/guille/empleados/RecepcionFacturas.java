package guille.empleados;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Guillermo on 3/5/2017.
 */
public class RecepcionFacturas {
    private static List<EmpleadoRecepcion> empleadoRecepcionList;

    public static void inicializar(int cantEmpleados) {
        empleadoRecepcionList = new LinkedList<>();
        for (int i = 0; i < cantEmpleados; i++) {
            empleadoRecepcionList.add(new EmpleadoRecepcion());
        }
    }

    public static EmpleadoRecepcion getEmpleadoLibre() {
        for (EmpleadoRecepcion empleadoRecepcion :
                empleadoRecepcionList) {
            if (empleadoRecepcion.isLibre()) {
                return empleadoRecepcion;
            }
        }
        return null;
    }

    public static int getEmpleadosOcupados() {
        int cantEmpleadosOcupados = 0;
        for (EmpleadoRecepcion empleadoRecepcion : empleadoRecepcionList) {
            if (empleadoRecepcion.isOcupado()) {
                cantEmpleadosOcupados++;
            }
        }
        return cantEmpleadosOcupados;
    }

    public static Iterator<EmpleadoRecepcion> getEmpleadosIterator() {
        return empleadoRecepcionList.iterator();
    }

}
