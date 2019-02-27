package guille.eventos;


import guille.Factura;
import guille.MainLoop;
import guille.empleados.EmpleadoRecepcion;
import guille.empleados.EmpleadoRegistro;
import guille.empleados.RegistroFacturas;
import guille.eventos.generators.RecepcionGenerator;
import guille.eventos.generators.RegistroGenerator;

import java.util.Date;

/**
 * Created by Guillermo on 3/5/2017.
 */
public class EventoFinRecepcionFacturaCredito extends Evento {
    private Factura factura;
    private EmpleadoRecepcion empleadoRecepcion;

    public EventoFinRecepcionFacturaCredito(Factura factura, EmpleadoRecepcion empleadoRecepcion, Date horaLlegada) {
        super(horaLlegada);
        this.factura = factura;
        this.empleadoRecepcion = empleadoRecepcion;
    }

    @Override
    public void consume() {
        factura.setHoraFinAtencionRecepcion(getHora());
        if (MainLoop.isRegistroQueueEmpty()) {
            EmpleadoRegistro empleadoRegistroLibre = RegistroFacturas.getEmpleadoLibre();
            if (null != empleadoRegistroLibre) {
                empleadoRegistroLibre.setOcupado();
                empleadoRegistroLibre.setFactura(factura);
                factura.setHoraInicioAtencionRegistro(getHora());
                RegistroGenerator.generate(getHora(), factura, empleadoRegistroLibre);
            } else {
                factura.setHoraEntradaColaRegistro(getHora());
                MainLoop.addToRegistroQueue(factura);
            }
        } else {
            factura.setHoraEntradaColaRegistro(getHora());
            MainLoop.addToRegistroQueue(factura);
        }
        if(MainLoop.isRecepcionQueueEmpty()) {
            empleadoRecepcion.setLibre();
        } else {
            Factura proximaFactura = MainLoop.popRecepcionQueue();
            empleadoRecepcion.setOcupado();
            empleadoRecepcion.setFactura(proximaFactura);
            proximaFactura.setHoraInicioAtencionRecepcion(getHora());
            RecepcionGenerator.generate(getHora(), proximaFactura, empleadoRecepcion);
        }
    }

    @Override
    public String getName() {
        return "Fin atención Recepción";
    }
}
