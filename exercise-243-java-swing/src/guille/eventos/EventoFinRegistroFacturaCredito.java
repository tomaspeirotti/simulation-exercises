package guille.eventos;


import guille.Factura;
import guille.MainLoop;
import guille.empleados.EmpleadoRegistro;
import guille.empleados.Terminal;
import guille.empleados.Terminales;
import guille.eventos.generators.TerminalGenerator;

import java.util.Date;

/**
 * Created by Guillermo on 3/5/2017.
 */
public class EventoFinRegistroFacturaCredito extends Evento {
    private Factura factura;
    private EmpleadoRegistro empleadoRegistro;

    public EventoFinRegistroFacturaCredito(Factura factura, EmpleadoRegistro empleadoRegistro, Date horaLlegada) {
        super(horaLlegada);
        this.factura = factura;
        this.empleadoRegistro = empleadoRegistro;
    }

    @Override
    public void consume() {
        factura.setHoraFinAtencionRegistro(getHora());
        if (MainLoop.isTerminalesQueueEmpty()) {
            Terminal terminalLibre = Terminales.getTerminalLibre();
            if (null != terminalLibre) {
                terminalLibre.setOcupado();
                terminalLibre.setEmpleadoRegistro(empleadoRegistro);
                factura.setHoraInicioAtencionTerminales(getHora());
                TerminalGenerator.generate(getHora(), empleadoRegistro, terminalLibre);
            } else {
                factura.setHoraEntradaColaTerminales(getHora());
                MainLoop.addToTerminalesQueue(empleadoRegistro);
            }
        } else {
            factura.setHoraEntradaColaTerminales(getHora());
            MainLoop.addToTerminalesQueue(empleadoRegistro);
        }
    }

    @Override
    public String getName() {
        return "Fin Atención Registro";
    }
}
