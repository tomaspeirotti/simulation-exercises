package com.simulacion.clases;

public class Cliente {

    private EstadoCliente estado;
    private Empleado siendoAtendidoPor;
    private int nroCliente;

    public Cliente(EstadoCliente estado, Empleado siendoAtendidoPor, int nroCliente) {
        this.estado = estado;
        this.siendoAtendidoPor = siendoAtendidoPor;
        this.nroCliente = nroCliente;
    }

    public int getNroCliente() {
        return nroCliente;
    }

    public void setNroCliente(int nroCliente) {
        this.nroCliente = nroCliente;
    }

    public Empleado getSiendoAtendidoPor() {
        return siendoAtendidoPor;
    }

    public void setSiendoAtendidoPor(Empleado siendoAtendidoPor) {
        this.siendoAtendidoPor = siendoAtendidoPor;
    }

    public EstadoCliente getEstado() {
        return estado;
    }

    public void setEstado(EstadoCliente estado) {
        this.estado = estado;
    }
}
