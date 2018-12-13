package com.simulation.model.clases;

import com.simulacion.dto.ParametroDTO;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Empleado {

    private int numero;
    private String nombre;
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

    public List<Cliente> getCola(List<Cliente> clientes) {
        return clientes.stream()
                .filter(cli -> cli.getEstado()!=null && cli.getSiendoAtendidoPor().getNumero()==this.getNumero() && cli.getEstado().equals(EstadoCliente.ESPERANDO))
                .sorted(Comparator.comparing(Cliente::getTiempoArrivo))
                .collect(Collectors.toList());
    }

    public int getColaSize(List<Cliente> clientes) {
        return this.getCola(clientes).size();
    }
}
