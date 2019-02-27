package guille.empleados;

import guille.Factura;

/**
 * Created by Guillermo on 3/5/2017.
 */
public class EmpleadoRecepcion extends Empleado {
    private int id;
    private static int nextId = 0;
    private EstadosEmpleadoRecepcion estado;
    private Factura factura;

    public EmpleadoRecepcion(){
        this.id = nextId;
        nextId++;
        this.estado = EstadosEmpleadoRecepcion.LIBRE;
    }

    public int getId() {
        return this.id;
    }

    public boolean isLibre() {
        return this.estado == EstadosEmpleadoRecepcion.LIBRE;
    }

    public void setLibre() {
        this.estado = EstadosEmpleadoRecepcion.LIBRE;
    }

    public boolean isOcupado() {
        return this.estado == EstadosEmpleadoRecepcion.OCUPADO;
    }

    public void setOcupado() {
        this.estado = EstadosEmpleadoRecepcion.OCUPADO;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public enum EstadosEmpleadoRecepcion {
        LIBRE, OCUPADO;
    }

}
