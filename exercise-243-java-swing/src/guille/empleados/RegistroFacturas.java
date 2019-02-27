package guille.empleados;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Guillermo on 3/5/2017.
 */
public class RegistroFacturas {
    private static List<EmpleadoRegistro> empleadoRegistroList;

    public static void inicializar(int cantEmpleados) {
        empleadoRegistroList = new LinkedList<>();
        for (int i = 0; i < cantEmpleados; i++) {
            empleadoRegistroList.add(new EmpleadoRegistro());
        }
    }

    public static EmpleadoRegistro getEmpleadoLibre() {
        for (EmpleadoRegistro empleadoRegistro :
                empleadoRegistroList) {
            if (empleadoRegistro.isLibre()) {
                return empleadoRegistro;
            }
        }
        return null;
    }

    public static int getEmpleadosOcupados() {
        int cantEmpleadosOcupados = 0;
        for (EmpleadoRegistro empleadoRegistro : empleadoRegistroList) {
            if (empleadoRegistro.isOcupado()) {
                cantEmpleadosOcupados++;
            }
        }
        return cantEmpleadosOcupados;
    }

    public static Iterator<EmpleadoRegistro> getEmpleadosIterator() {
        return empleadoRegistroList.iterator();
    }

}
