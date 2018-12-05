package com.simulacion.clases;

import java.time.LocalTime;

public class Cliente {

    private EstadoCliente estado;
//    private String estado;
    private Empleado siendoAtendidoPor;
    private int nroCliente;
    private LocalTime tiempoArrivo;

    public Cliente() {
    }

    public Cliente(Cliente cliente) {
        this.estado = cliente.getEstado();
        this.siendoAtendidoPor = cliente.getSiendoAtendidoPor();
        this.nroCliente = cliente.getNroCliente();
        this.tiempoArrivo = cliente.getTiempoArrivo();
    }

    public Cliente(EstadoCliente estado, Empleado siendoAtendidoPor, int nroCliente) {
        this.estado = estado;
        this.siendoAtendidoPor = siendoAtendidoPor;
        this.nroCliente = nroCliente;
    }

    public Cliente(EstadoCliente estado, Empleado siendoAtendidoPor, int nroCliente, LocalTime tiempoArrivo) {
        this.estado = estado;
        this.siendoAtendidoPor = siendoAtendidoPor;
        this.nroCliente = nroCliente;
        this.tiempoArrivo = tiempoArrivo;
    }

    public LocalTime getTiempoArrivo() {
        return tiempoArrivo;
    }

    public void setTiempoArrivo(LocalTime tiempoArrivo) {
        this.tiempoArrivo = tiempoArrivo;
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

//    public String getEstado() {
//        return estado;
//    }
//
//    public void setEstado(String estado) {
//        this.estado = estado;
//    }
}
