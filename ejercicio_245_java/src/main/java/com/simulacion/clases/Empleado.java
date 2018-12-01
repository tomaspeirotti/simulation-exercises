package com.simulacion.clases;

import com.simulacion.dto.ParametroDTO;

import java.time.LocalTime;

public class Empleado {

    private int numero;
    private String nombre;
    private int cola;
    private LocalTime finAtencion;
    private EstadoEmpleado estadoEmpleado;
    private ParametroDTO atencion;

    public Empleado(int numero, String nombre, int cola, LocalTime finAtencion, EstadoEmpleado estadoEmpleado, ParametroDTO atencion) {
        this.numero = numero;
        this.nombre = nombre;
        this.cola = cola;
        this.finAtencion = finAtencion;
        this.estadoEmpleado = estadoEmpleado;
        this.atencion = atencion;
    }

    public void addCola(int cantidad) {
        this.cola += cantidad;
    }

    public void removeCola(int cantidad) {
        this.cola -= cantidad;
    }

    public ParametroDTO getAtencion() {
        return atencion;
    }

    public void setAtencion(ParametroDTO atencion) {
        this.atencion = atencion;
    }

    public boolean isLibre() {
        return this.getEstadoEmpleado().equals(EstadoEmpleado.LIBRE);
    }

    public boolean isOcupado() {
        return this.getEstadoEmpleado().equals(EstadoEmpleado.OCUPADO);
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCola() {
        return cola;
    }

    public void setCola(int cola) {
        this.cola = cola;
    }

    public LocalTime getFinAtencion() {
        return finAtencion;
    }

    public void setFinAtencion(LocalTime finAtencion) {
        this.finAtencion = finAtencion;
    }

    public EstadoEmpleado getEstadoEmpleado() {
        return estadoEmpleado;
    }

    public void setEstadoEmpleado(EstadoEmpleado estadoEmpleado) {
        this.estadoEmpleado = estadoEmpleado;
    }
}
