package com.simulacion.clases;

import com.simulacion.dto.IntervaloDTO;
import com.simulacion.dto.ParametrosDTO;
import com.simulacion.util.FileUtil;
import com.simulacion.util.JSONUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.*;

public class SimulationBusiness {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimulationBusiness.class);
    private static final String DESKTOP_DIRECTORY = Paths.get("C:\\Users\\Tomas\\Desktop").toString();
    public static final String MAS_MENOS = " +/- ";
    private JSONUtils<ParametrosDTO> parametrosDTOJSONUtils;
    private final ParametrosDTO params = getParametrosFromJson();


    public List<String> getColumnasPredeterminadas() {
        String[] columnasPredeterminadas = {"Tiempo","Frec de arrivos","TipoEvento","RND","Prox arrivo","Emp 1 - EstadoEmpleado","Emp 1 - Tiempo fin At","Emp 1 - Cola","Emp 2 - EstadoEmpleado", "Emp 2 - Tiempo fin At", "Emp 2 - Cola"};
        return new ArrayList<>(Arrays.asList(columnasPredeterminadas).subList(0, columnasPredeterminadas.length));
    }

    public List<Iteracion> startSimulation() throws Exception {
        LinkedList<Iteracion> iteraciones = new LinkedList<>();
        iteraciones.add(getPrimeraIteracion());
        for (int i = 0; i < params.getArrivos(); i++) {
            iteraciones.addFirst(getProximaIteracion(iteraciones.getFirst()));
        }
        return iteraciones;
    }

    private Iteracion getProximaIteracion(Iteracion ultimaIteracion) throws Exception {
        Iteracion proxIteracion = new Iteracion();
        proxIteracion.setNroIteracion(ultimaIteracion.getNroIteracion()+1);
        proxIteracion.setIntervalos(ultimaIteracion.getIntervalos());
        proxIteracion.setEmpleados(ultimaIteracion.getEmpleados());
        proxIteracion.setClientes(ultimaIteracion.getClientes());

        Evento proxEvent = getProximoEvento(ultimaIteracion);
        proxIteracion.setEvento(proxEvent);
        proxIteracion.setTiempo(proxEvent.getTiempo());
        proxIteracion.setRandom(getRandom());
        if (proxEvent.getTipo().equals(TipoEvento.ARRIVA_CLIENTE)) proxIteracion.setProxArrivo(proxIteracion.getTiempo().plusSeconds(getProxArrivo(getIntervaloActual(proxIteracion),proxIteracion.getRandom())));
        IntervaloDTO intervalo = getIntervaloActual(proxIteracion);
        proxIteracion.setFrecArrivos((long)intervalo.getMedia() + MAS_MENOS + (long)intervalo.getVarianza());

        handleEvent(proxIteracion);

        return proxIteracion;
    }

    private void handleEvent(Iteracion proxIteracion) throws Exception {
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
        Empleado empleado = proxIteracion.getEmpleados().stream().filter(emp -> emp.getNumero()==numero).findFirst().get();
        empleado.removeCola(1);
        if (empleado.getCola()==0) {
            empleado.setEstadoEmpleado(EstadoEmpleado.LIBRE);
        } else {
            empleado.setEstadoEmpleado(EstadoEmpleado.OCUPADO);
        }
    }

    private void handleArrivoCliente(Iteracion proxIteracion) throws Exception {
        List<Empleado> empleados = proxIteracion.getEmpleados();
        Empleado empleadoLibre = getPrimerEmpleadoLibre(empleados);
        if (empleadoLibre != null) {
            if (empleadoLibre.getCola()!=0) throw new Exception("Empleado libre con cola > 0");
            empleadoLibre.setEstadoEmpleado(EstadoEmpleado.OCUPADO);
            empleadoLibre.setFinAtencion(proxIteracion.getTiempo().plusSeconds(getFinAtencion(empleadoLibre,proxIteracion.getRandom())));
            proxIteracion.getClientes().add(new Cliente(EstadoCliente.SIENDO_ATENDIDO, empleadoLibre));
        } else {
            Empleado empleadoConMenorCola = getEmpleadoOcupadoConMenorCola(empleados);
            if (empleadoConMenorCola.getCola()<0) throw new Exception("Empleado ocupado con cola < 0");
            empleadoConMenorCola.addCola(1);
            proxIteracion.getClientes().add(new Cliente(EstadoCliente.ESPERANDO_ATENCION, empleadoConMenorCola));
        }
    }

    private Empleado getEmpleadoOcupadoConMenorCola(List<Empleado> empleados) {
        return empleados.stream().filter(Empleado::isOcupado).min(Comparator.comparing(Empleado::getCola)).get();
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
        Iteracion iteracion = new Iteracion();
        iteracion.setClientes(new LinkedList<>());
        iteracion.setIntervalos(params.getIntervalos());
        List<Empleado> empleados = new ArrayList<>();
        empleados.add(new Empleado(0,"Empleado 0",0,null, EstadoEmpleado.LIBRE, params.getAtencion()));
        empleados.add(new Empleado(1,"Empleado 1",0,null, EstadoEmpleado.LIBRE, params.getAtencion()));
        iteracion.setEmpleados(empleados);
        iteracion.setTiempo(LocalTime.of(params.getHoraComienzo(),0,0));
        iteracion.setProxArrivo(iteracion.getTiempo().plusSeconds(getProxArrivo(getIntervaloActual(iteracion), iteracion.getRandom())));
        iteracion.setNroIteracion(0);
        iteracion.setEvento(new Evento(iteracion.getTiempo(),TipoEvento.INICIALIZACION));
        IntervaloDTO intervaloDTO = getIntervaloActual(iteracion);
        iteracion.setFrecArrivos((long)intervaloDTO.getMedia() + MAS_MENOS + (long)intervaloDTO.getVarianza());
        return iteracion;
    }

    public IntervaloDTO getIntervaloActual(Iteracion iteracion) {
        IntervaloDTO intervaloDTO = null;
        for (IntervaloDTO intervalo: iteracion.getIntervalos()) {
            if ((LocalTime.parse(intervalo.getHoraDesde()).isBefore(iteracion.getTiempo()) || (LocalTime.parse(intervalo.getHoraDesde()).equals(iteracion.getTiempo()))
                    && (LocalTime.parse(intervalo.getHoraHasta()).isAfter(iteracion.getTiempo())) || (LocalTime.parse(intervalo.getHoraHasta()).equals(iteracion.getTiempo())))){
                intervaloDTO = intervalo;
            }
        }
        return intervaloDTO;
    }

    public long getProxArrivo(IntervaloDTO intervaloDTO, double rnd) {
        return (long) getDistribucionUniforme(rnd, intervaloDTO.getMedia(), intervaloDTO.getVarianza());
    }

    public double getDistribucionUniforme(double rnd, double media, double varianza) { //TODO: ES DISTRIBUCION NORMAL, NO UNIFORME!
        return (rnd*(varianza-media))+media;
    }

    public long getFinAtencion(Empleado empleado, double rnd) {
        return (long) getDistribucionUniforme(rnd, empleado.getAtencion().getMedia(), empleado.getAtencion().getVarianza());
    }

    public double getRandom() {
        Random r = new Random();
        int low = 0;
        int high = 100;
        return (r.nextInt(high-low) + low)*0.01;
    }

    public ParametrosDTO getParametrosFromJson() {
        String json = FileUtil.getFileAsString("parametros.json");
        return getParametrosDTOJSONUtils().convertJsonToObject(json, ParametrosDTO.class);
    }

    public JSONUtils<ParametrosDTO> getParametrosDTOJSONUtils() {
        if (parametrosDTOJSONUtils == null) {
            setParametrosDTOJSONUtils(new JSONUtils<>());
        }
        return parametrosDTOJSONUtils;
    }

    public void setParametrosDTOJSONUtils(JSONUtils<ParametrosDTO> parametrosDTOJSONUtils) {
        this.parametrosDTOJSONUtils = parametrosDTOJSONUtils;
    }


}
