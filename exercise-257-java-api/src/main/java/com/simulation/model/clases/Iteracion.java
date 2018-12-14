package com.simulation.model.clases;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

public class Iteracion {

    private int nroIteracion;
    private LocalTime tiempo;
    private double rnd;
    private LocalTime proxArrivo;
    private List<Empleado> empleados;
    private LinkedList<Cliente> clientes;
    private Evento evento;
    private LocalTime tiempoEntreArrivos;

    public LocalTime getTiempoEntreArrivos() {
        return tiempoEntreArrivos;
    }

    public void setTiempoEntreArrivos(LocalTime tiempoEntreArrivos) {
        this.tiempoEntreArrivos = tiempoEntreArrivos;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public int getNroIteracion() {
        return nroIteracion;
    }

    public void setNroIteracion(int nroIteracion) {
        this.nroIteracion = nroIteracion;
    }

    public LocalTime getTiempo() {
        return tiempo;
    }

    public void setTiempo(LocalTime tiempo) {
        this.tiempo = tiempo;
    }

    public LocalTime getProxArrivo() {
        return proxArrivo;
    }

    public void setProxArrivo(LocalTime proxArrivo) {
        this.proxArrivo = proxArrivo;
    }

    public double getRnd() {
        return rnd;
    }

    public void setRnd(double rnd) {
        this.rnd = rnd;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public LinkedList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(LinkedList<Cliente> clientes) {
        this.clientes = clientes;
    }
}
