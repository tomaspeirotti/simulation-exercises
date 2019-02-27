package guille.empleados;


import guille.Factura;

import java.util.Date;

/**
 * Created by Guillermo on 3/5/2017.
 */
public class EmpleadoRegistro extends Empleado {
    private int id;
    private static int nextId = 0;
    private EstadosEmpleadoRegistro estado;
    private Factura factura;

    public EmpleadoRegistro() {
        this.id = nextId;
        nextId++;
        this.estado = EstadosEmpleadoRegistro.LIBRE;
        this.factura = null;
    }

    public int getId() {
        return this.id;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Factura getFactura() {
        return this.factura;
    }

    public boolean isLibre() {
        return this.estado == EstadosEmpleadoRegistro.LIBRE;
    }

    public void setLibre() {
        this.estado = EstadosEmpleadoRegistro.LIBRE;
        this.factura = null;
    }

    public boolean isOcupado() {
        return this.estado == EstadosEmpleadoRegistro.OCUPADO;
    }

    public void setOcupado() {
        this.estado = EstadosEmpleadoRegistro.OCUPADO;
    }

    public void clearData() {
        this.setRandomAtencion(-1);
        this.setTiempohastaProxFinAt(null);
        this.setHoraProxFinAt(null);

        this.setRandomSelloAprobado(-1);
        this.setTiempohastaProxFinSelloAprobado(null);
        this.setHoraProxFinSelloAprobado(null);

        this.setRandomSelloRechazado(-1);
        this.setTiempohastaProxFinSelloRechazado(null);
        this.setHoraProxFinSelloRechazado(null);
    }

    public enum EstadosEmpleadoRegistro {
        LIBRE, OCUPADO;
    }


    private int randomSelloAprobado;
    private Date tiempohastaProxFinSelloAprobado;
    private Date horaProxFinSelloAprobado;
    private int randomSelloRechazado;
    private Date tiempohastaProxFinSelloRechazado;
    private Date horaProxFinSelloRechazado;

    public int getRandomSelloAprobado() {
        return randomSelloAprobado;
    }

    public void setRandomSelloAprobado(int randomSelloAprobado) {
        this.randomSelloAprobado = randomSelloAprobado;
    }

    public Date getTiempohastaProxFinSelloAprobado() {
        return tiempohastaProxFinSelloAprobado;
    }

    public void setTiempohastaProxFinSelloAprobado(Date tiempohastaProxFinSelloAprobado) {
        this.tiempohastaProxFinSelloAprobado = tiempohastaProxFinSelloAprobado;
    }

    public Date getHoraProxFinSelloAprobado() {
        return horaProxFinSelloAprobado;
    }

    public void setHoraProxFinSelloAprobado(Date horaProxFinSelloAprobado) {
        this.horaProxFinSelloAprobado = horaProxFinSelloAprobado;
    }

    public int getRandomSelloRechazado() {
        return randomSelloRechazado;
    }

    public void setRandomSelloRechazado(int randomSelloRechazado) {
        this.randomSelloRechazado = randomSelloRechazado;
    }

    public Date getTiempohastaProxFinSelloRechazado() {
        return tiempohastaProxFinSelloRechazado;
    }

    public void setTiempohastaProxFinSelloRechazado(Date tiempohastaProxFinSelloRechazado) {
        this.tiempohastaProxFinSelloRechazado = tiempohastaProxFinSelloRechazado;
    }

    public Date getHoraProxFinSelloRechazado() {
        return horaProxFinSelloRechazado;
    }

    public void setHoraProxFinSelloRechazado(Date horaProxFinSelloRechazado) {
        this.horaProxFinSelloRechazado = horaProxFinSelloRechazado;
    }
}
