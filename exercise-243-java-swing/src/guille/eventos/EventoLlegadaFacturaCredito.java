package guille.eventos;


import guille.Factura;
import guille.MainLoop;
import guille.empleados.EmpleadoRecepcion;
import guille.empleados.RecepcionFacturas;
import guille.eventos.generators.FacturaCreditoGenerator;
import guille.eventos.generators.RecepcionGenerator;

import java.util.Date;

/**
 * Created by Guillermo on 3/5/2017.
 */
public class EventoLlegadaFacturaCredito extends Evento {
    @Override
    public void consume() {
        Factura factura = new Factura(this.getHora());
        EmpleadoRecepcion empleadoRecepcion = RecepcionFacturas.getEmpleadoLibre();
        if (null != empleadoRecepcion) {
            empleadoRecepcion.setOcupado();
            empleadoRecepcion.setFactura(factura);
            factura.setHoraInicioAtencionRecepcion(getHora());
            RecepcionGenerator.generate(getHora(), factura, empleadoRecepcion);
        } else {
            factura.setHoraEntradaColaRecepcion(getHora());
            MainLoop.addToRecepcionQueue(factura);
        }
        FacturaCreditoGenerator.generate(getHora());
    }

    @Override
    public String getName() {
        return "Llegada de Factura Crédito";
    }

    public EventoLlegadaFacturaCredito(Date hora) {
        super(hora);
    }
}
