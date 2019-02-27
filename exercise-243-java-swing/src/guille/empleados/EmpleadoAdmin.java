package guille.empleados;


import guille.Factura;

import java.util.Date;

/**
 * Created by Guillermo on 3/5/2017.
 */
public class EmpleadoAdmin extends Empleado {
    private int id;
    private static int nextId = 0;
    private EstadosEmpleadoAdmin estado;
    private Factura factura;

    public EmpleadoAdmin() {
        this.id = nextId;
        nextId++;
        this.estado = EstadosEmpleadoAdmin.LIBRE;
        this.factura = null;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Factura getFactura() {
        return this.factura;
    }

    public boolean isLibre() {
        return this.estado == EstadosEmpleadoAdmin.LIBRE;
    }

    public void setLibre() {
        this.estado = EstadosEmpleadoAdmin.LIBRE;
        this.factura = null;
    }

    public boolean isOcupado() {
        return this.estado == EstadosEmpleadoAdmin.OCUPADO;
    }

    public void setOcupado() {
        this.estado = EstadosEmpleadoAdmin.OCUPADO;
    }

    public enum EstadosEmpleadoAdmin {
        LIBRE, OCUPADO;
    }


    private int randomArchivar;
    private Date tiempohastaProxFinArchivar;
    private Date horaProxFinArchivar;

    public Date getHoraProxFinArchivar() {
        return horaProxFinArchivar;
    }

    public void setHoraProxFinArchivar(Date horaProxFinArchivar) {
        this.horaProxFinArchivar = horaProxFinArchivar;
    }

    public int getRandomArchivar() {
        return randomArchivar;
    }

    public void setRandomArchivar(int randomArchivar) {
        this.randomArchivar = randomArchivar;
    }

    public Date getTiempohastaProxFinArchivar() {
        return tiempohastaProxFinArchivar;
    }

    public void setTiempohastaProxFinArchivar(Date tiempohastaProxFinArchivar) {
        this.tiempohastaProxFinArchivar = tiempohastaProxFinArchivar;
    }
}
