package com.simulacion.clases;

public class Cliente {

    private EstadoCliente estado;
    private Empleado siendoAtendidoPor;

    public Cliente(EstadoCliente estado, Empleado siendoAtendidoPor) {
        this.estado = estado;
        this.siendoAtendidoPor = siendoAtendidoPor;
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
