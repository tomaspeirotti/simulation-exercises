package guille.empleados;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Guillermo on 3/5/2017.
 */
public class Administracion {
    private static List<EmpleadoAdmin> empleadoAdminList;

    public static void inicializar(int cantEmpleados) {
        empleadoAdminList = new LinkedList<>();
        for (int i = 0; i < cantEmpleados; i++) {
            empleadoAdminList.add(new EmpleadoAdmin());
        }
    }

    public static EmpleadoAdmin getEmpleadoLibre() {
        for (EmpleadoAdmin empleadoAdmin :
                empleadoAdminList) {
            if (empleadoAdmin.isLibre()) {
                return empleadoAdmin;
            }
        }
        return null;
    }

    public static int getEmpleadosOcupados() {
        int cantEmpleadosOcupados = 0;
        for (EmpleadoAdmin empleadoAdmin : empleadoAdminList) {
            if (empleadoAdmin.isOcupado()) {
                cantEmpleadosOcupados++;
            }
        }
        return cantEmpleadosOcupados;
    }

    public static Iterator<EmpleadoAdmin> getEmpleadosIterator() {
        return empleadoAdminList.iterator();
    }

}
