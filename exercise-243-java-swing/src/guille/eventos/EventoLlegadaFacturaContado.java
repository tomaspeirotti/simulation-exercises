package guille.eventos;


import guille.Factura;
import guille.MainLoop;
import guille.empleados.EmpleadoMesaEntrada;
import guille.empleados.MesaEntrada;
import guille.eventos.generators.FacturaContadoGenerator;
import guille.eventos.generators.MesaEntradaGenerator;

import java.util.Date;

/**
 * Created by Guillermo on 3/5/2017.
 */
public class EventoLlegadaFacturaContado extends Evento {
    @Override
    public void consume() {
        Factura factura = new Factura(this.getHora());
        EmpleadoMesaEntrada empleadoLibre = MesaEntrada.getEmpleadoLibre();
        if (null != empleadoLibre) {
            empleadoLibre.setOcupado();
            empleadoLibre.setFactura(factura);
            factura.setHoraInicioAtencionMesaEntrada(getHora());
            MesaEntradaGenerator.generate(getHora(), factura, empleadoLibre);
        } else {
            factura.setHoraEntradaColaMesaEntrada(getHora());
            MainLoop.addToMesaEntradaQueue(factura);
        }
        FacturaContadoGenerator.generate(getHora());
    }

    @Override
    public String getName() {
        return "Llegada de Factura Contado";
    }

    public EventoLlegadaFacturaContado(Date hora) {
        super(hora);
    }
}
