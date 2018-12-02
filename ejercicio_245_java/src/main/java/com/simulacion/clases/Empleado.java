package com.simulacion.clases;

import com.simulacion.dto.ParametroDTO;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Empleado {

    private int numero;
    private String nombre;
    private List<Cliente> cola;
    private LocalTime finAtencion;
    private EstadoEmpleado estadoEmpleado;
    private ParametroDTO atencion;
    public Cliente clienteSiendoAtendido;

    public Empleado() {
    }

    public Empleado(int numero, String nombre, LocalTime finAtencion, EstadoEmpleado estadoEmpleado, ParametroDTO atencion) {
        this.numero = numero;
        this.nombre = nombre;
        this.finAtencion = finAtencion;
        this.estadoEmpleado = estadoEmpleado;
        this.atencion = atencion;
        this.cola = new ArrayList<>();
    }

    public int getColaSize() {
        return this.cola.size();
    }

    public List<Cliente> getCola() {
        return cola;
    }

    public void setCola(List<Cliente> cola) {
        this.cola = cola;
    }

    public Cliente getClienteSiendoAtendido() {
        return clienteSiendoAtendido;
    }

    public void setClienteSiendoAtendido(Cliente clienteSiendoAtendido) {
        this.clienteSiendoAtendido = clienteSiendoAtendido;
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
