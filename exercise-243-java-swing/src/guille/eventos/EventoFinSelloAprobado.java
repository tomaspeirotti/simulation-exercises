package guille.eventos;


import guille.Factura;
import guille.MainLoop;
import guille.empleados.EmpleadoRegistro;
import guille.eventos.generators.RegistroGenerator;

import java.util.Date;

/**
 * Created by Guillermo on 3/5/2017.
 */
public class EventoFinSelloAprobado extends Evento {
    private EmpleadoRegistro empleadoRegistro;


    public EventoFinSelloAprobado(EmpleadoRegistro empleadoRegistro, Date horaLlegada) {
        super(horaLlegada);
        this.empleadoRegistro = empleadoRegistro;
    }

    @Override
    public void consume() {
        empleadoRegistro.getFactura().setHoraFinSelloAprobado(getHora());
        if(MainLoop.isRegistroQueueEmpty()) {
            empleadoRegistro.setLibre();
        } else {
            Factura proximaFactura = MainLoop.popRegistroQueue();
            empleadoRegistro.setOcupado();
            proximaFactura.setHoraInicioAtencionRegistro(getHora());
            empleadoRegistro.setFactura(proximaFactura);
            MainLoop.addToTiempoEnColaRegistro(proximaFactura);
            RegistroGenerator.generate(getHora(), proximaFactura, empleadoRegistro);
        }
    }

    @Override
    public String getName() {
        return "Fin Atención Sello Aprobado";
    }
}
