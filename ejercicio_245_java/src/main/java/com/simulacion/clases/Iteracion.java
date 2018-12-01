package com.simulacion.clases;

import com.simulacion.dto.IntervaloDTO;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

public class Iteracion {

    private int nroIteracion;
    private LocalTime tiempo;
    private List<IntervaloDTO> intervalos;
    private double random;
    private LocalTime proxArrivo;
    private List<Empleado> empleados;
    private LinkedList<Cliente> clientes;
    private Evento evento;
    private String frecArrivos;

    public String getFrecArrivos() {
        return frecArrivos;
    }

    public void setFrecArrivos(String frecArrivos) {
        this.frecArrivos = frecArrivos;
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

    public List<IntervaloDTO> getIntervalos() {
        return intervalos;
    }

    public void setIntervalos(List<IntervaloDTO> intervalos) {
        this.intervalos = intervalos;
    }

    public double getRandom() {
        return random;
    }

    public void setRandom(double random) {
        this.random = random;
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