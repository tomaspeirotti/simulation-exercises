package guille;


import guille.empleados.*;
import guille.eventos.Evento;
import guille.eventos.generators.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Guillermo on 3/5/2017.
 */
public class MainLoop {

    private static final int cantEmpleadosRecepcion = 1;
    private static final int cantEmpleadosRegistro = 1;
    private static final int cantTerminales = 1;
    private static final int cantEmpleadosMesaEntrada = 1;
    private static final int cantEmpleadosAdmin = 2;

    private static int nroEvento;

    private static PriorityQueue<Evento> eventosQueue;

    private static LinkedList<Factura> recepcionQueue;
    private static LinkedList<Factura> registroQueue;
    private static LinkedList<EmpleadoRegistro> terminalesQueue;

    private static LinkedList<Factura> mesaEntradaQueue;
    private static LinkedList<Factura> adminQueue;

    public static int maxRecepcionQueueSize;
    public static int maxAdminQueueSize;
    public static long tiempoEnColaRegistro;

    public static PriorityQueue<Evento> getQueue() {
        return eventosQueue;
    }

    public static void addToRecepcionQueue(Factura factura) {
        recepcionQueue.add(factura);
        if (maxRecepcionQueueSize < recepcionQueue.size()) {
            maxRecepcionQueueSize = recepcionQueue.size();
        }
    }

    public static void addToRegistroQueue(Factura factura) {
        registroQueue.add(factura);
    }

    public static void addToTerminalesQueue(EmpleadoRegistro empleadoRegistro) {
        terminalesQueue.add(empleadoRegistro);
    }

    public static void addToMesaEntradaQueue(Factura factura) {
        mesaEntradaQueue.add(factura);
    }

    public static void addToAdminQueue(Factura factura) {
        adminQueue.add(factura);
        if (maxAdminQueueSize < adminQueue.size()) {
            maxAdminQueueSize = adminQueue.size();
        }
    }

    public static Factura popRecepcionQueue() {
        if (!recepcionQueue.isEmpty()) {
            return recepcionQueue.remove(0);
        } else {
            return null;
        }
    }

    public static Factura popRegistroQueue() {
        if (!registroQueue.isEmpty()) {
            return registroQueue.remove(0);
        } else {
            return null;
        }
    }

    public static EmpleadoRegistro popTerminalesQueue() {
        if (!terminalesQueue.isEmpty()) {
            return terminalesQueue.remove(0);
        } else {
            return null;
        }
    }

    public static Factura popMesaEntradaQueue() {
        if (!mesaEntradaQueue.isEmpty()) {
            return mesaEntradaQueue.remove(0);
        } else {
            return null;
        }
    }

    public static Factura popAdminQueue() {
        if (!adminQueue.isEmpty()) {
            return adminQueue.remove(0);
        } else {
            return null;
        }
    }

    public static boolean isRecepcionQueueEmpty() {
        return recepcionQueue.isEmpty();
    }

    public static boolean isRegistroQueueEmpty() {
        return registroQueue.isEmpty();
    }

    public static boolean isTerminalesQueueEmpty() {
        return terminalesQueue.isEmpty();
    }

    public static boolean isMesaEntradaQueueEmpty() {
        return mesaEntradaQueue.isEmpty();
    }

    public static boolean isAdminQueueEmpty() {
        return adminQueue.isEmpty();
    }

    public static void start(int desdeEvento, int hastaEvento, Date desdeHora, Date hastaHora, JTable tableResults) {
        initializeVariables();

        DefaultTableModel tableModel = new DefaultTableModel();
        initializeTableColumns(tableModel);

        boolean businessHours = true;

        FacturaCreditoGenerator.generate(getHoraInicio().getTime());
        FacturaContadoGenerator.generate(getHoraInicio().getTime());

        while (!eventosQueue.isEmpty() && businessHours && nroEvento < hastaEvento) {
            Evento evento = eventosQueue.poll();

            // break if time limit for showing events has been reached
            if (compareTimes(evento.getHora(), hastaHora) > 0 || evento.getHora().compareTo(getHoraFin()) > 0) {
                break;
            }

            evento.consume();
            nroEvento++;
            if (nroEvento >= desdeEvento && compareTimes(evento.getHora(), desdeHora) > 0) {
                addToResults(tableModel, evento);
            }

        }
        tableResults.setModel(tableModel);

    }

    public static int compareTimes(Date d1, Date d2) {
        int t1;
        int t2;

        t1 = (int) (d1.getTime() % (24 * 60 * 60 * 1000L));
        t2 = (int) (d2.getTime() % (24 * 60 * 60 * 1000L));
        return (t1 - t2);
    }

    private static void addToResults(DefaultTableModel tableModel, Evento evento) {
        DateFormat df = new SimpleDateFormat("HH:mm:ss");

        String[] row = new String[8 + 3 * cantEmpleadosRecepcion + 2 + 3 * cantEmpleadosRegistro + 2 + 3 * cantTerminales + 3 * cantEmpleadosRegistro + 3 * cantEmpleadosRegistro + 5 + 3 * cantEmpleadosMesaEntrada + 2 + 3 * cantEmpleadosAdmin + 3 * cantEmpleadosAdmin + 7 + cantEmpleadosRecepcion + cantEmpleadosRegistro + cantTerminales + cantEmpleadosMesaEntrada + cantEmpleadosAdmin];

        for (int i = 0; i < row.length; i++) {
            row[i] = "";
        }

        row[0] = "" + nroEvento;
        row[1] = evento.getName();
        row[2] = df.format(evento.getHora());

        row[3] = "" + FacturaCreditoGenerator.getRandomUltimaGeneracion();//random factura credito
        row[4] = "" + df.format(FacturaCreditoGenerator.getDemoraUltimaGeneracion());//Tiempo hasta prox Factura Crédito
        row[5] = "" + df.format(FacturaCreditoGenerator.getHoraUltimaGeneracion().getTime());//Hora prox Factura Crédito

        row[6] = "" + recepcionQueue.size();
        row[7] = "" + RecepcionFacturas.getEmpleadosOcupados();

        int inicioEmpleadosRecepcion = 8;
        int finEmpleadosRecepcion = 3 * cantEmpleadosRecepcion + inicioEmpleadosRecepcion;
        Iterator<EmpleadoRecepcion> empleadoRecepcionIterator = RecepcionFacturas.getEmpleadosIterator();
        for (int i = inicioEmpleadosRecepcion; i < finEmpleadosRecepcion; i += 3) {
            EmpleadoRecepcion empleadoRecepcion = empleadoRecepcionIterator.next();
            if (empleadoRecepcion.isOcupado() && empleadoRecepcion.getTiempohastaProxFinAt() != null) {
                row[i] = "" + empleadoRecepcion.getRandomAtencion();
                row[i + 1] = "" + df.format(empleadoRecepcion.getTiempohastaProxFinAt());
                row[i + 2] = "" + df.format(empleadoRecepcion.getHoraProxFinAt());
            } else {
                row[i] = "";
                row[i + 1] = "";
                row[i + 2] = "";
            }
        }

        row[finEmpleadosRecepcion] = "" + registroQueue.size();
        row[finEmpleadosRecepcion + 1] = "" + RegistroFacturas.getEmpleadosOcupados();

        int inicioEmpleadosRegistro = finEmpleadosRecepcion + 2;
        int finEmpleadosRegistro = 3 * cantEmpleadosRegistro + inicioEmpleadosRegistro;
        Iterator<EmpleadoRegistro> empleadoRegistroIterator = RegistroFacturas.getEmpleadosIterator();
        for (int i = inicioEmpleadosRegistro; i < finEmpleadosRegistro; i += 3) {
            EmpleadoRegistro empleadoRegistro = empleadoRegistroIterator.next();
            if (empleadoRegistro.isOcupado() && null != empleadoRegistro.getTiempohastaProxFinAt()) {
                row[i] = "" + empleadoRegistro.getRandomAtencion();
                row[i + 1] = "" + df.format(empleadoRegistro.getTiempohastaProxFinAt());
                row[i + 2] = "" + df.format(empleadoRegistro.getHoraProxFinAt());
            } else {
                row[i] = "";
                row[i + 1] = "";
                row[i + 2] = "";
            }
        }

        row[finEmpleadosRegistro] = "" + terminalesQueue.size();
        row[finEmpleadosRegistro + 1] = "" + Terminales.getTerminalesOcupadas();

        int inicioTerminales = finEmpleadosRegistro + 2;
        int finTerminales = 3 * cantTerminales + inicioTerminales;
        Iterator<Terminal> terminalIterator = Terminales.getTerminalesIterator();
        for (int i = inicioTerminales; i < finTerminales; i += 3) {
            Terminal terminal = terminalIterator.next();
            if (terminal.isOcupado()) {
                row[i] = "" + terminal.getRandomAtencion();
                row[i + 1] = "" + df.format(terminal.getTiempohastaProxFinAt());
                row[i + 2] = "" + df.format(terminal.getHoraProxFinAt());
            } else {
                row[i] = "";
                row[i + 1] = "";
                row[i + 2] = "";
            }
        }

        int inicioSelloAprobado = finTerminales;
        int finSelloAprobado = 3 * cantEmpleadosRegistro + inicioSelloAprobado;
        Iterator<EmpleadoRegistro> selloAprobadoIterator = RegistroFacturas.getEmpleadosIterator();
        for (int i = inicioSelloAprobado; i < finSelloAprobado; i += 3) {
            EmpleadoRegistro empleadoRegistro = selloAprobadoIterator.next();
            if (empleadoRegistro.isOcupado() && null != empleadoRegistro.getTiempohastaProxFinSelloAprobado()) {
                row[i] = "" + empleadoRegistro.getRandomSelloAprobado();
                row[i + 1] = "" + df.format(empleadoRegistro.getTiempohastaProxFinSelloAprobado());
                row[i + 2] = "" + df.format(empleadoRegistro.getHoraProxFinSelloAprobado());
            } else {
                row[i] = "";
                row[i + 1] = "";
                row[i + 2] = "";
            }
        }

        int inicioSelloRechazado = finSelloAprobado;
        int finSelloRechazado = 3 * cantEmpleadosRegistro + inicioSelloRechazado;
        Iterator<EmpleadoRegistro> selloRechazadoIterator = RegistroFacturas.getEmpleadosIterator();
        for (int i = inicioSelloRechazado; i < finSelloRechazado; i += 3) {
            EmpleadoRegistro empleadoRegistro = selloRechazadoIterator.next();
            if (empleadoRegistro.isOcupado() && null != empleadoRegistro.getTiempohastaProxFinSelloRechazado()) {
                row[i] = "" + empleadoRegistro.getRandomSelloRechazado();
                row[i + 1] = "" + df.format(empleadoRegistro.getTiempohastaProxFinSelloRechazado());
                row[i + 2] = "" + df.format(empleadoRegistro.getHoraProxFinSelloRechazado());
            } else {
                row[i] = "";
                row[i + 1] = "";
                row[i + 2] = "";
            }
        }

        row[finSelloRechazado] = "" + FacturaContadoGenerator.getRandomUltimaGeneracion();//random factura credito
        row[finSelloRechazado + 1] = "" + df.format(FacturaContadoGenerator.getDemoraUltimaGeneracion());//Tiempo hasta prox Factura Crédito
        row[finSelloRechazado + 2] = "" + df.format(FacturaContadoGenerator.getHoraUltimaGeneracion().getTime());//Hora prox Factura Crédito

        row[finSelloRechazado + 3] = "" + mesaEntradaQueue.size();
        row[finSelloRechazado + 4] = "" + MesaEntrada.getEmpleadosOcupados();

        int inicioEmpleadosMesaEntrada = finSelloRechazado + 5;
        int finEmpleadosMesaEntrada = 3 * cantEmpleadosMesaEntrada + inicioEmpleadosMesaEntrada;
        Iterator<EmpleadoMesaEntrada> empleadoMesaEntradaIterator = MesaEntrada.getEmpleadosIterator();
        for (int i = inicioEmpleadosMesaEntrada; i < finEmpleadosMesaEntrada; i += 3) {
            EmpleadoMesaEntrada empleadoMesaEntrada = empleadoMesaEntradaIterator.next();
            if (empleadoMesaEntrada.isOcupado() && empleadoMesaEntrada.getTiempohastaProxFinAt() != null) {
                row[i] = "" + empleadoMesaEntrada.getRandomAtencion();
                row[i + 1] = "" + df.format(empleadoMesaEntrada.getTiempohastaProxFinAt());
                row[i + 2] = "" + df.format(empleadoMesaEntrada.getHoraProxFinAt());
            } else {
                row[i] = "";
                row[i + 1] = "";
                row[i + 2] = "";
            }
        }

        row[finEmpleadosMesaEntrada] = "" + adminQueue.size();
        row[finEmpleadosMesaEntrada + 1] = "" + Administracion.getEmpleadosOcupados();

        int inicioEmpleadosAdmin = finEmpleadosMesaEntrada + 2;
        int finEmpleadosAdmin = 3 * cantEmpleadosAdmin + inicioEmpleadosAdmin;
        Iterator<EmpleadoAdmin> empleadoAdminIterator = Administracion.getEmpleadosIterator();
        for (int i = inicioEmpleadosAdmin; i < finEmpleadosAdmin; i += 3) {
            EmpleadoAdmin empleadoAdmin = empleadoAdminIterator.next();
            if (empleadoAdmin.isOcupado() && null != empleadoAdmin.getTiempohastaProxFinAt()) {
                row[i] = "" + empleadoAdmin.getRandomAtencion();
                row[i + 1] = "" + df.format(empleadoAdmin.getTiempohastaProxFinAt());
                row[i + 2] = "" + df.format(empleadoAdmin.getHoraProxFinAt());
            } else {
                row[i] = "";
                row[i + 1] = "";
                row[i + 2] = "";
            }
        }

        int inicioArchivar = finEmpleadosAdmin;
        int finArchivar = 3 * cantEmpleadosAdmin + inicioArchivar;
        Iterator<EmpleadoAdmin> archivarIterator = Administracion.getEmpleadosIterator();
        for (int i = inicioArchivar; i < finArchivar; i += 3) {
            EmpleadoAdmin empleadoAdmin = archivarIterator.next();
            if (empleadoAdmin.isOcupado() && null != empleadoAdmin.getTiempohastaProxFinArchivar()) {
                row[i] = "" + empleadoAdmin.getRandomArchivar();
                row[i + 1] = "" + df.format(empleadoAdmin.getTiempohastaProxFinArchivar());
                row[i + 2] = "" + df.format(empleadoAdmin.getHoraProxFinArchivar());
            } else {
                row[i] = "";
                row[i + 1] = "";
                row[i + 2] = "";
            }
        }

        row[finArchivar] = "" + maxRecepcionQueueSize;
        row[finArchivar + 1] = "" + millisToHoursMinutesSeconds(tiempoEnColaRegistro);
        row[finArchivar + 2] = "" + getFacturasForCola(recepcionQueue);
        row[finArchivar + 3] = "" + getFacturasForCola(registroQueue);
        row[finArchivar + 4] = "" + getEmpleadosForCola(terminalesQueue);
        row[finArchivar + 5] = "" + getFacturasForCola(mesaEntradaQueue);
        row[finArchivar + 6] = "" + getFacturasForCola(adminQueue);

        int inicioFacturasEmpleadosRecepcion = finArchivar + 7;
        int finFacturasEmpleadosRecepcion = cantEmpleadosRecepcion + inicioFacturasEmpleadosRecepcion;
        Iterator<EmpleadoRecepcion> empleadosRecepcionIterator = RecepcionFacturas.getEmpleadosIterator();
        for (int i = inicioFacturasEmpleadosRecepcion; i < finFacturasEmpleadosRecepcion; i ++) {
            EmpleadoRecepcion empleadoRecepcion = empleadosRecepcionIterator.next();
            if (empleadoRecepcion.isOcupado()) {
                row[i] = "Factura " + empleadoRecepcion.getFactura().getId();
            } else {
                row[i] = "";
            }
        }

        int inicioFacturasEmpleadosRegistro = finFacturasEmpleadosRecepcion;
        int finFacturasEmpleadosRegistro = cantEmpleadosRegistro + inicioFacturasEmpleadosRegistro;
        Iterator<EmpleadoRegistro> empleadosRegistroIterator = RegistroFacturas.getEmpleadosIterator();
        for (int i = inicioFacturasEmpleadosRegistro; i < finFacturasEmpleadosRegistro; i ++) {
            EmpleadoRegistro empleadoRegistro = empleadosRegistroIterator.next();
            if (empleadoRegistro.isOcupado()) {
                row[i] = "Factura " + empleadoRegistro.getFactura().getId();
            } else {
                row[i] = "";
            }
        }

        int inicioEmpleadosTerminales = finFacturasEmpleadosRegistro;
        int finEmpleadosTerminales = cantTerminales + inicioEmpleadosTerminales;
        Iterator<Terminal> empleadosTerminalesIterator = Terminales.getTerminalesIterator();
        for (int i = inicioEmpleadosTerminales; i < finEmpleadosTerminales; i ++) {
            Terminal terminal = empleadosTerminalesIterator.next();
            if (terminal.isOcupado()) {
                row[i] = "Empleado " + terminal.getEmpleadoRegistro().getId() + " con Factura " + terminal.getEmpleadoRegistro().getFactura().getId();
            } else {
                row[i] = "";
            }
        }

        int inicioFacturasEmpleadosMesaEntrada = finEmpleadosTerminales;
        int finFacturasEmpleadosMesaEntrada = cantEmpleadosMesaEntrada + inicioFacturasEmpleadosMesaEntrada;
        Iterator<EmpleadoMesaEntrada> empleadosMesaEntradaIterator = MesaEntrada.getEmpleadosIterator();
        for (int i = inicioFacturasEmpleadosMesaEntrada; i < finFacturasEmpleadosMesaEntrada; i ++) {
            EmpleadoMesaEntrada empleadoMesaEntrada = empleadosMesaEntradaIterator.next();
            if (empleadoMesaEntrada.isOcupado()) {
                row[i] = "Factura " + empleadoMesaEntrada.getFactura().getId();
            } else {
                row[i] = "";
            }
        }

        int inicioFacturasEmpleadosAdmin = finFacturasEmpleadosMesaEntrada;
        int finFacturasEmpleadosAdmin = cantEmpleadosAdmin + inicioFacturasEmpleadosAdmin;
        Iterator<EmpleadoAdmin> empleadosAdminIterator = Administracion.getEmpleadosIterator();
        for (int i = inicioFacturasEmpleadosAdmin; i < finFacturasEmpleadosAdmin; i ++) {
            EmpleadoAdmin empleadoAdmin = empleadosAdminIterator.next();
            if (empleadoAdmin.isOcupado()) {
                row[i] = "Factura " + empleadoAdmin.getFactura().getId();
            } else {
                row[i] = "";
            }
        }

        tableModel.addRow(row);
    }

    private static String getEmpleadosForCola(LinkedList<EmpleadoRegistro> terminalesQueue) {
        StringBuilder sb = new StringBuilder();
        for (EmpleadoRegistro empleadoRegistro : terminalesQueue) {
            sb.append(empleadoRegistro.getId());
            sb.append(", ");
        }
        String stringToReturn = sb.toString();
        if (stringToReturn.length() == 0) {
            return stringToReturn;
        } else {
            return stringToReturn.substring(0, stringToReturn.length() - 2);
        }
    }

    private static String getFacturasForCola(LinkedList<Factura> queue) {
        StringBuilder sb = new StringBuilder();
        for (Factura factura : queue) {
            sb.append(factura.getId());
            sb.append(", ");
        }
        String stringToReturn = sb.toString();
        if (stringToReturn.length() == 0) {
            return stringToReturn;
        } else {
            return stringToReturn.substring(0, stringToReturn.length() - 2);
        }
    }

    private static String millisToHoursMinutesSeconds(long tiempoEnColaRegistro) {
        final int SECOND = 1000;
        final int MINUTE = 60 * SECOND;
        final int HOUR = 60 * MINUTE;

        long ms = tiempoEnColaRegistro;
        StringBuilder sb = new StringBuilder();
        if (ms >= HOUR) {
            long value = ms / HOUR;
            if (value < 10) {
                sb.append("0");
            }
            sb.append(value);
            sb.append(":");
            ms %= HOUR;
        } else {
            sb.append("00:");
        }
        if (ms >= MINUTE) {
            long value = ms / MINUTE;
            if (value < 10) {
                sb.append("0");
            }
            sb.append(value);
            sb.append(":");
            ms %= MINUTE;
        } else {
            sb.append("00:");
        }
        if (ms >= SECOND) {
            long value = ms / SECOND;
            if (value < 10) {
                sb.append("0");
            }
            sb.append(value);
            ms %= SECOND;
        } else {
            sb.append("00");
        }
        return sb.toString();
    }

    private static void initializeVariables() {
        nroEvento = 0;

        eventosQueue = new PriorityQueue();
        recepcionQueue = new LinkedList<>();
        registroQueue = new LinkedList<>();
        terminalesQueue = new LinkedList<>();
        mesaEntradaQueue = new LinkedList<>();
        adminQueue = new LinkedList<>();

        maxRecepcionQueueSize = 0;
        maxAdminQueueSize = 0;

        RecepcionFacturas.inicializar(cantEmpleadosRecepcion);
        RegistroFacturas.inicializar(cantEmpleadosRegistro);
        Terminales.inicializar(cantTerminales);
        MesaEntrada.inicializar(cantEmpleadosMesaEntrada);
        Administracion.inicializar(cantEmpleadosAdmin);

        AdministracionGenerator.initialize(getHoraInicio().getTime());
        ArchivarGenerator.initialize(getHoraInicio().getTime());
        FacturaContadoGenerator.initialize(getHoraInicio().getTime());
        FacturaCreditoGenerator.initialize(getHoraInicio().getTime());
        MesaEntradaGenerator.initialize(getHoraInicio().getTime());
        RecepcionGenerator.initialize(getHoraInicio().getTime());
        RegistroGenerator.initialize(getHoraInicio().getTime());
        SelloAprobadoGenerator.initialize(getHoraInicio().getTime());
        SelloRechazadoGenerator.initialize(getHoraInicio().getTime());
        TerminalGenerator.initialize(getHoraInicio().getTime());
    }

    private static void initializeTableColumns(DefaultTableModel tableModel) {
        tableModel.addColumn("Nº");
        tableModel.addColumn("Evento");
        tableModel.addColumn("Reloj");

        tableModel.addColumn("Random Factura Crédito");
        tableModel.addColumn("Tiempo hasta prox Factura Crédito");
        tableModel.addColumn("Hora prox Factura Crédito");

        tableModel.addColumn("Facturas en cola Recepción");
        tableModel.addColumn("Empleados ocupados Recepción");

        for (int i = 0; i < cantEmpleadosRecepcion; i++) {
            tableModel.addColumn("Random atencion Recepción empleado " + i);
            tableModel.addColumn("Tiempo hasta prox fin at Recepción empleado " + i);
            tableModel.addColumn("Hora prox fin at Recepción empleado " + i);
        }

        tableModel.addColumn("Facturas en cola Registro");
        tableModel.addColumn("Empleados ocupados Registro");

        for (int i = 0; i < cantEmpleadosRegistro; i++) {
            tableModel.addColumn("Random atencion Registro empleado " + i);
            tableModel.addColumn("Tiempo hasta prox fin at Registro empleado " + i);
            tableModel.addColumn("Hora prox fin at Registro empleado " + i);
        }

        tableModel.addColumn("Empleados Registro en cola Terminales");
        tableModel.addColumn("Terminales ocupadas");

        for (int i = 0; i < cantTerminales; i++) {
            tableModel.addColumn("Random atencion Terminal " + i);
            tableModel.addColumn("Tiempo hasta prox fin at Terminal " + i);
            tableModel.addColumn("Hora prox fin at Terminal " + i);
        }

        for (int i = 0; i < cantEmpleadosRegistro; i++) {
            tableModel.addColumn("Random atencion Sello Aprobado empleado " + i);
            tableModel.addColumn("Tiempo hasta prox fin at Sello Aprobado empleado " + i);
            tableModel.addColumn("Hora prox fin at Sello Aprobado empleado " + i);
        }

        for (int i = 0; i < cantEmpleadosRegistro; i++) {
            tableModel.addColumn("Random atencion Sello Rechazado empleado " + i);
            tableModel.addColumn("Tiempo hasta prox fin at Sello Rechazado empleado " + i);
            tableModel.addColumn("Hora prox fin at Sello Rechazado empleado " + i);
        }

        tableModel.addColumn("Random Factura Contado");
        tableModel.addColumn("Tiempo hasta prox Factura Contado");
        tableModel.addColumn("Hora prox Factura Contado");

        tableModel.addColumn("Facturas en cola Mesa Entrada");
        tableModel.addColumn("Empleados ocupados Mesa Entrada");

        for (int i = 0; i < cantEmpleadosMesaEntrada; i++) {
            tableModel.addColumn("Random atencion Mesa Entrada empleado " + i);
            tableModel.addColumn("Tiempo hasta prox fin at Mesa Entrada empleado " + i);
            tableModel.addColumn("Hora prox fin at Mesa Entrada empleado " + i);
        }

        tableModel.addColumn("Facturas en cola Admin");
        tableModel.addColumn("Empleados ocupados Admin");

        for (int i = 0; i < cantEmpleadosAdmin; i++) {
            tableModel.addColumn("Random atencion Admin empleado " + i);
            tableModel.addColumn("Tiempo hasta prox fin at Admin empleado " + i);
            tableModel.addColumn("Hora prox fin at Admin empleado " + i);
        }

        for (int i = 0; i < cantEmpleadosAdmin; i++) {
            tableModel.addColumn("Random atencion Archivar empleado " + i);
            tableModel.addColumn("Tiempo hasta prox fin at Archivar empleado " + i);
            tableModel.addColumn("Hora prox fin at Archivar empleado " + i);
        }

        tableModel.addColumn("Máx facturas en cola Recepción");
        tableModel.addColumn("Tiempo en cola Registro");

        tableModel.addColumn("Facturas en cola Recepción");
        tableModel.addColumn("Facturas en cola Registro");
        tableModel.addColumn("Empleados Registro en cola Terminales");
        tableModel.addColumn("Facturas en cola Mesa Entrada");
        tableModel.addColumn("Facturas en cola Administración");

        for (int i = 0; i < cantEmpleadosRecepcion; i++) {
            tableModel.addColumn("Empleado Recepcion " + i + " tiene factura");
        }

        for (int i = 0; i < cantEmpleadosRegistro; i++) {
            tableModel.addColumn("Empleado Registro " + i + " tiene factura");
        }

        for (int i = 0; i < cantTerminales; i++) {
            tableModel.addColumn("Terminal " + i + " tiene empleado");
        }

        for (int i = 0; i < cantEmpleadosMesaEntrada; i++) {
            tableModel.addColumn("Empleado Mesa Entrada " + i + " tiene factura");
        }

        for (int i = 0; i < cantEmpleadosAdmin; i++) {
            tableModel.addColumn("Empleado Admin " + i + " tiene factura");
        }
    }

    public static Date getHoraFin() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 18);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

    public static Calendar getHoraInicio() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 9);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal;
    }

    public static void addToTiempoEnColaRegistro(Factura factura) {
        tiempoEnColaRegistro += (factura.getRecorridoActual().getHoraInicioAtencionRegistro().getTime() - factura.getRecorridoActual().getHoraEntradaColaRegistro().getTime());

    }


    
    //TODO cambiar Máx facturas en cola Recepción por Máx facturas en cola Registro
    //TODO ponerle color a las columnas de las colas y los eventos

    //TODO hacer parametrizable
}
