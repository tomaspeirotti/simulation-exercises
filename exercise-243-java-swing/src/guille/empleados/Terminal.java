package guille.empleados;

/**
 * Created by Guillermo on 3/5/2017.
 */
public class Terminal extends Empleado {
    private int id;
    private static int nextId = 0;
    private EstadosTerminal estado;
    private EmpleadoRegistro empleadoRegistro;

    public Terminal() {
        this.id = nextId;
        nextId++;
        this.estado = EstadosTerminal.LIBRE;
        this.empleadoRegistro = null;
    }

    public EmpleadoRegistro getEmpleadoRegistro() {
        return empleadoRegistro;
    }

    public void setEmpleadoRegistro(EmpleadoRegistro empleadoRegistro) {
        this.empleadoRegistro = empleadoRegistro;
    }

    public boolean isLibre() {
        return this.estado == EstadosTerminal.LIBRE;
    }

    public void setLibre() {
        this.estado = EstadosTerminal.LIBRE;
    }

    public boolean isOcupado() {
        return this.estado == EstadosTerminal.OCUPADO;
    }

    public void setOcupado() {
        this.estado = EstadosTerminal.OCUPADO;
    }

    public enum EstadosTerminal {
        LIBRE, OCUPADO;
    }
}
