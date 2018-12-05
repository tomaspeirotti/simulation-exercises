package com.simulacion.clases;

import com.simulacion.dto.IntervaloDTO;
import com.simulacion.dto.ParametrosDTO;
import com.simulacion.util.FileUtil;
import com.simulacion.util.JSONUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class SimulationBusiness {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimulationBusiness.class);
    public static final String MAS_MENOS = " +/- ";
    private JSONUtils<ParametrosDTO> parametrosDTOJSONUtils;
    private final ParametrosDTO params = getParametrosFromJson();
    private int arrivos = 0;
    private int nroCliente = 0;
    private int longMaximaCola = 0;
    private boolean x2used = false;

    public List<String> getColumnasPredeterminadas() {
        String[] columnasPredeterminadas = {"Tiempo","Frec de arrivos","Evento","RND1","RND2","Prox arrivo","Emp 0 - Estado","Emp 0 - fin At","Emp 0 - Cola","Emp 1 - Estado", "Emp 1 - fin At", "Emp 1 - Cola","Long. Max. Cola"};
        return new ArrayList<>(Arrays.asList(columnasPredeterminadas).subList(0, columnasPredeterminadas.length));
    }

    public List<Iteracion> startSimulation() throws Exception {
        LinkedList<Iteracion> iteraciones = new LinkedList<>();
        iteraciones.add(getPrimeraIteracion());
        while (arrivos <= params.getArrivos()) {
            Iteracion iteracion = getProximaIteracion(iteraciones.getFirst());
            iteraciones.addFirst(iteracion);
        }
        LOGGER.info("Longitud de cola maxima: " + longMaximaCola);
        return iteraciones.stream().sorted(Comparator.comparing(Iteracion::getNroIteracion)).collect(Collectors.toList());
    }

    private Iteracion getProximaIteracion(Iteracion ultimaIteracion) throws Exception {
        Iteracion proxIteracion = new Iteracion();
        proxIteracion.setNroIteracion(ultimaIteracion.getNroIteracion()+1);
        proxIteracion.setIntervalos(ultimaIteracion.getIntervalos());

        proxIteracion.setEmpleados(getEmpleadosUltimaIteracion(ultimaIteracion));
        proxIteracion.setClientes(getClientesUltimaIteracion(ultimaIteracion));

        Evento proxEvent = getProximoEvento(ultimaIteracion);
        proxIteracion.setEvento(proxEvent);
        proxIteracion.setTiempo(proxEvent.getTiempo());
        proxIteracion.setRandom1(getRandom());
        proxIteracion.setRandom2(getRandom());
        if (proxEvent.getTipo().equals(TipoEvento.ARRIVA_CLIENTE)) {
            proxIteracion.setProxArrivo(proxIteracion.getTiempo().plusSeconds(getProxArrivo(getIntervaloActual(proxIteracion),proxIteracion.getRandom1(),proxIteracion.getRandom2())));
        } else {
            proxIteracion.setProxArrivo(ultimaIteracion.getProxArrivo());
        }
        IntervaloDTO intervalo = getIntervaloActual(proxIteracion);
        proxIteracion.setFrecArrivos((long)intervalo.getMedia() + MAS_MENOS + (long)intervalo.getVarianza());
        proxIteracion.setLongMaximaCola(longMaximaCola);
        handleEvent(proxIteracion);

        return proxIteracion;
    }

    private LinkedList<Cliente> getClientesUltimaIteracion(Iteracion ultimaIteracion) {
        return new LinkedList<>(ultimaIteracion.getClientes());
    }

    private List<Empleado> getEmpleadosUltimaIteracion(Iteracion ultimaIteracion) {
        LinkedList<Empleado> empleadosUltimaIteracion = new LinkedList<>();
        ultimaIteracion.getEmpleados().forEach(x ->{
            Empleado empleado = new Empleado();
            if (x.getClienteSiendoAtendido() != null) empleado.setClienteSiendoAtendido(new Cliente(x.getClienteSiendoAtendido().getEstado(),empleado,x.getClienteSiendoAtendido().getNroCliente(), x.getClienteSiendoAtendido().getTiempoArrivo()));
            empleado.setFinAtencion(x.getFinAtencion());
            empleado.setEstadoEmpleado(x.getEstadoEmpleado());
            empleado.setCola(new LinkedList<>(x.getCola()));
            empleado.setAtencion(x.getAtencion());
            empleado.setNombre(x.getNombre());
            empleado.setAtencion(x.getAtencion());
            empleado.setNumero(x.getNumero());
            empleadosUltimaIteracion.add(empleado);
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
        Empleado empleado = proxIteracion.getEmpleados().stream().filter(emp -> emp.getNumero()==numero).findFirst().get();
        Cliente clienteAtendido = proxIteracion.getClientes().stream().filter(cliente -> cliente.getNroCliente() == empleado.getClienteSiendoAtendido().getNroCliente()).findFirst().get();
        proxIteracion.getClientes().remove(clienteAtendido);
        Cliente clienteDestruido = new Cliente();
        mapCliente(clienteAtendido, clienteDestruido);
        clienteDestruido.setEstado(EstadoCliente.DESTRUIDO);
        proxIteracion.getClientes().add(clienteDestruido);
        if (empleado.getCola().isEmpty()) {
            empleado.setEstadoEmpleado(EstadoEmpleado.LIBRE);
            empleado.setFinAtencion(null);
            empleado.setClienteSiendoAtendido(null);
        } else {
            empleado.setEstadoEmpleado(EstadoEmpleado.OCUPADO);
            empleado.setFinAtencion(proxIteracion.getTiempo().plusSeconds(getFinAtencion(empleado,proxIteracion.getRandom1(),proxIteracion.getRandom2())));
            empleado.setClienteSiendoAtendido(getPrimerClienteEnLaCola(empleado));
            empleado.getCola().remove(empleado.getClienteSiendoAtendido());
        }
    }

    private Cliente getPrimerClienteEnLaCola(Empleado empleado) {
        return empleado.getCola().stream().min(Comparator.comparing(Cliente::getTiempoArrivo)).get();
    }

    private void handleArrivoCliente(Iteracion proxIteracion) {
        arrivos += 1;
        List<Empleado> empleados = proxIteracion.getEmpleados();
        Empleado empleadoLibre = getPrimerEmpleadoLibre(empleados);
        Cliente cliente;
        if (empleadoLibre != null) {
            empleadoLibre.setEstadoEmpleado(EstadoEmpleado.OCUPADO);
            empleadoLibre.setFinAtencion(proxIteracion.getTiempo().plusSeconds(getFinAtencion(empleadoLibre,proxIteracion.getRandom1(), proxIteracion.getRandom2())));
            cliente = new Cliente(EstadoCliente.SIENDO_ATENDIDO, empleadoLibre, getNroCliente());
            cliente.setTiempoArrivo(proxIteracion.getTiempo());
            empleadoLibre.setClienteSiendoAtendido(cliente);
        } else {
            Empleado empleadoConMenorCola = getEmpleadoOcupadoConMenorCola(empleados);
            cliente = new Cliente(EstadoCliente.ESPERANDO_ATENCION, empleadoConMenorCola, getNroCliente());
            cliente.setTiempoArrivo(proxIteracion.getTiempo());
            empleadoConMenorCola.getCola().add(cliente);

            Empleado empleadoConMayorCola = getEmpleadoConMayorCola(empleados);
            int longActualCola = empleadoConMayorCola.getCola().size();
            if (longActualCola>longMaximaCola) longMaximaCola = longActualCola;

        }
        proxIteracion.getClientes().add(cliente);
    }

    private Empleado getEmpleadoOcupadoConMenorCola(List<Empleado> empleados) {
        return empleados.stream().filter(Empleado::isOcupado).min(Comparator.comparing(Empleado::getColaSize)).get();
    }

    private Empleado getEmpleadoConMayorCola(List<Empleado> empleados) {
        return empleados.stream().max(Comparator.comparing(Empleado::getColaSize)).get();
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
        iteracion.setRandom1(getRandom());
        iteracion.setRandom2(getRandom());
        iteracion.setClientes(new LinkedList<>());
        iteracion.setIntervalos(params.getIntervalos());
        List<Empleado> empleados = new ArrayList<>();
        empleados.add(new Empleado(0,"Empleado 0", null, EstadoEmpleado.LIBRE, params.getAtencion()));
        empleados.add(new Empleado(1,"Empleado 1", null, EstadoEmpleado.LIBRE, params.getAtencion()));
        iteracion.setEmpleados(empleados);
        iteracion.setTiempo(LocalTime.of(params.getHoraComienzo(),0,0));
        iteracion.setProxArrivo(iteracion.getTiempo().plusSeconds(getProxArrivo(getIntervaloActual(iteracion), iteracion.getRandom1(), iteracion.getRandom2())));
        iteracion.setNroIteracion(0);
        iteracion.setEvento(new Evento(iteracion.getTiempo(),TipoEvento.INICIALIZACION));
        IntervaloDTO intervaloDTO = getIntervaloActual(iteracion);
        iteracion.setFrecArrivos((long)intervaloDTO.getMedia() + MAS_MENOS + (long)intervaloDTO.getVarianza());
        iteracion.setLongMaximaCola(longMaximaCola);

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

    public long getProxArrivo(IntervaloDTO intervaloDTO, double rnd1, double rnd2) {
        long returnValue = (long) getDistribucionNormal(rnd1, rnd2,intervaloDTO.getMedia(), intervaloDTO.getVarianza());
        long limInf = (long) (intervaloDTO.getMedia()-intervaloDTO.getVarianza());
        long limSup = (long) (intervaloDTO.getMedia()+intervaloDTO.getVarianza());
        if (returnValue < limInf) returnValue = limInf;
        if (returnValue > limSup) returnValue = limSup;
        return returnValue;
    }

    public double getDistribucionNormal(double rnd1, double rnd2, double media, double varianza) {
        double x1, x2, value;
        if (x2used) {
            x1 = Math.sqrt(-2*Math.log(rnd1))*Math.cos(2*Math.PI*rnd2);
            value = x1;
            x2used = false;
        } else {
            x2 = Math.sqrt(-2*Math.log(rnd1))*Math.sin(2*Math.PI*rnd2);
            value = x2;
            x2used = true;
        }
        return value*varianza+media;
    }

    public long getFinAtencion(Empleado empleado, double rnd1, double rnd2) {
        long returnValue =  (long) getDistribucionNormal(rnd1, rnd2, empleado.getAtencion().getMedia(), empleado.getAtencion().getVarianza());
        long limSup = (long) (empleado.getAtencion().getMedia()+empleado.getAtencion().getVarianza());
        long limInf = (long) (empleado.getAtencion().getMedia()-empleado.getAtencion().getVarianza());
        if (returnValue < limInf) returnValue = limInf;
        if (returnValue > limSup) returnValue = limSup;
        return returnValue;
    }

    public double getRandom() {
        Random r = new Random();
        int low = 1;
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

    public int getNroCliente() {
       return this.nroCliente += 1;
    }

    public void mapCliente(Cliente source, Cliente destination) {
        destination.setSiendoAtendidoPor(source.getSiendoAtendidoPor());
        destination.setEstado(source.getEstado());
        destination.setTiempoArrivo(source.getTiempoArrivo());
        destination.setNroCliente(source.getNroCliente());
    }


}
