package com.simulacion.clases;

import com.simulacion.dto.IntervaloDTO;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

public class Iteracion {

    private int nroIteracion;
    private LocalTime tiempo;
    private List<IntervaloDTO> intervalos;
    private double random1;
    private double random2;
    private LocalTime proxArrivo;
    private List<Empleado> empleados;
    private LinkedList<Cliente> clientes;
    private Evento evento;
    private String frecArrivos;
    private int longMaximaCola;

    public int getLongMaximaCola() {
        return longMaximaCola;
    }

    public void setLongMaximaCola(int longMaximaCola) {
        this.longMaximaCola = longMaximaCola;
    }

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

    public double getRandom1() {
        return random1;
    }

    public void setRandom1(double random1) {
        this.random1 = random1;
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

    public double getRandom2() {
        return random2;
    }

    public void setRandom2(double random2) {
        this.random2 = random2;
    }
}
