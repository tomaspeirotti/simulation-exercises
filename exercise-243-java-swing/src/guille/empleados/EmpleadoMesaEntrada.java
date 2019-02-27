package guille.empleados;

import guille.Factura;

/**
 * Created by Guillermo on 3/5/2017.
 */
public class EmpleadoMesaEntrada extends Empleado {
    private int id;
    private static int nextId = 0;
    private EstadosEmpleadoMesaEntrada estado;
    private Factura factura;

    public EmpleadoMesaEntrada(){
        this.id = nextId;
        nextId++;
        this.estado = EstadosEmpleadoMesaEntrada.LIBRE;
    }

    public int getId() {
        return this.id;
    }

    public boolean isLibre() {
        return this.estado == EstadosEmpleadoMesaEntrada.LIBRE;
    }

    public void setLibre() {
        this.estado = EstadosEmpleadoMesaEntrada.LIBRE;
    }

    public boolean isOcupado() {
        return this.estado == EstadosEmpleadoMesaEntrada.OCUPADO;
    }

    public void setOcupado() {
        this.estado = EstadosEmpleadoMesaEntrada.OCUPADO;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public enum EstadosEmpleadoMesaEntrada {
        LIBRE, OCUPADO;
    }

}
