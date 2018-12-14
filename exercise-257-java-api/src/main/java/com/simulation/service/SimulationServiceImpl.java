package com.simulation.service;


import com.simulation.model.clases.*;
import com.simulation.model.dto.ParametrosDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.*;

@Component
public class SimulationServiceImpl implements SimulationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimulationServiceImpl.class);
    private int arrivos = 0;

    public SimulationServiceImpl() {
    }

    public List<String> getColumnasPredeterminadas() {
        String[] columnasPredeterminadas = {"Tiempo","Frec de arrivos","Evento","RND1","RND2","Prox arrivo","Tiempo entre arrivos","E0:Estado","E0:fin At","E0:Cola","E1:Estado", "E1:fin At", "E1:Cola","Long. Max. Cola"};
        return new ArrayList<>(Arrays.asList(columnasPredeterminadas).subList(0, columnasPredeterminadas.length));
    }

    @Override
    public List<Iteracion> startSimulation(ParametrosDTO parametrosDTO) {
        LinkedList<Iteracion> iteraciones = new LinkedList<>();
        iteraciones.add(getPrimeraIteracion());
        while (arrivos <= parametrosDTO.getArrivos()) {
            Iteracion iteracion = getProximaIteracion(iteraciones.getLast());
            iteraciones.addLast(iteracion);
        }
        return iteraciones;
    }

    private Iteracion getProximaIteracion(Iteracion ultimaIteracion) {
        Iteracion proxIteracion = new Iteracion();
        proxIteracion.setNroIteracion(ultimaIteracion.getNroIteracion()+1);

        proxIteracion.setEmpleados(getEmpleadosUltimaIteracion(ultimaIteracion));
        proxIteracion.setClientes(getClientesUltimaIteracion(ultimaIteracion));

        Evento proxEvent = getProximoEvento(ultimaIteracion);
        proxIteracion.setEvento(proxEvent);
        proxIteracion.setTiempo(proxEvent.getTiempo());
        proxIteracion.setRnd(getRandom());
        proxIteracion.setProxArrivo(ultimaIteracion.getProxArrivo());

        handleEvent(proxIteracion);

        return proxIteracion;
    }

    private LinkedList<Cliente> getClientesUltimaIteracion(Iteracion ultimaIteracion) {
        LinkedList<Cliente> clientesNuevaIteracion = new LinkedList<>();
        ultimaIteracion.getClientes().forEach(cliente -> clientesNuevaIteracion.add(new Cliente(cliente)));
        return clientesNuevaIteracion;
    }

    private List<Empleado> getEmpleadosUltimaIteracion(Iteracion ultimaIteracion) {
        LinkedList<Empleado> empleadosUltimaIteracion = new LinkedList<>();
        ultimaIteracion.getEmpleados().forEach(empleadoUltimaIt ->{
            Empleado nuevoEmpleado = new Empleado();
            if (empleadoUltimaIt.getClienteSiendoAtendido() != null) nuevoEmpleado.setClienteSiendoAtendido(new Cliente(empleadoUltimaIt.getClienteSiendoAtendido()));
            nuevoEmpleado.setFinAtencion(empleadoUltimaIt.getFinAtencion());
            nuevoEmpleado.setEstadoEmpleado(empleadoUltimaIt.getEstadoEmpleado());
            nuevoEmpleado.setAtencion(empleadoUltimaIt.getAtencion());
            nuevoEmpleado.setNombre(empleadoUltimaIt.getNombre());
            nuevoEmpleado.setNumero(empleadoUltimaIt.getNumero());
            empleadosUltimaIteracion.add(nuevoEmpleado);
        });
        return empleadosUltimaIteracion;
    }

    private void handleEvent(Iteracion proxIteracion) {
        switch (proxIteracion.getEvento().getTipo()) {
            case ARRIVA_CLIENTE:
                handleArrivoCliente(proxIteracion);
                break;
            case FIN_ATENCION_EMP_0:
                handleFinAtencionEmp(proxIteracion, 0);
                break;
            case FIN_ATENCION_EMP_1:
                handleFinAtencionEmp(proxIteracion, 1);
                break;
            case INICIALIZACION:
                break;
            default: break;
        }
    }

    private void handleFinAtencionEmp(Iteracion proxIteracion, int numero) {

    }

    private Cliente getPrimerClienteEnLaCola(Empleado empleado, List<Cliente> clientesDeIteracion) {
        return empleado.getCola(clientesDeIteracion).stream().findFirst().get();
    }

    private void handleArrivoCliente(Iteracion proxIteracion) {

    }

    private LocalTime getTiempoEntreArrivos(LocalTime proxArrivo, Iteracion proxIteracion) {
        return proxArrivo.minusSeconds(proxIteracion.getProxArrivo().getSecond());
    }

    private Empleado getEmpleadoOcupadoConMenorCola(List<Empleado> empleados, List<Cliente> clientes) {
        return empleados.stream().filter(Empleado::isOcupado).min(Comparator.comparing(emp -> emp.getColaSize(clientes))).get();
    }

    private Empleado getEmpleadoConMayorCola(List<Empleado> empleados, List<Cliente> clientes) {
        return empleados.stream().max(Comparator.comparing(emp -> emp.getColaSize(clientes))).get();
    }

    private Empleado getPrimerEmpleadoLibre(List<Empleado> empleados) {
        return (empleados.stream().filter(Empleado::isLibre).count() > 0) ? empleados.stream().filter(Empleado::isLibre).findFirst().get() : null;
    }

    private Evento getProximoEvento(Iteracion ultimaIteracion) {
        List<Evento> eventos = new ArrayList<>();
        Empleado emp0 = ultimaIteracion.getEmpleados().stream().filter(emp -> emp.getNumero()==0).findFirst().get();
        Empleado emp1 = ultimaIteracion.getEmpleados().stream().filter(emp -> emp.getNumero()==1).findFirst().get();
        eventos.add(new Evento(ultimaIteracion.getProxArrivo(), TipoEvento.ARRIVA_CLIENTE));
        if (emp0.getFinAtencion() != null) eventos.add(new Evento(emp0.getFinAtencion(), TipoEvento.FIN_ATENCION_EMP_0));
        if (emp1.getFinAtencion() != null) eventos.add(new Evento(emp1.getFinAtencion(), TipoEvento.FIN_ATENCION_EMP_1));
        return eventos.stream().min(Comparator.comparing(Evento::getTiempo)).get();
    }

    public Iteracion getPrimeraIteracion() {


        return null;
    }

    public double getRandom() {
        Random r = new Random();
        int low = 1;
        int high = 100;
        return (r.nextInt(high-low) + low)*0.01;
    }
}
