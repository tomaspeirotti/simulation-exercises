package guille.eventos;


import guille.MainLoop;
import guille.empleados.EmpleadoRegistro;
import guille.empleados.Terminal;
import guille.eventos.generators.SelloAprobadoGenerator;
import guille.eventos.generators.SelloRechazadoGenerator;
import guille.eventos.generators.TerminalGenerator;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Guillermo on 3/5/2017.
 */
public class EventoFinTerminal extends Evento {
    private static final double probabilidadRechazar = 0.1; //10%

    private Terminal terminal;
    private EmpleadoRegistro empleadoRegistro;

    public EventoFinTerminal(Terminal terminal, Date horaLlegada) {
        super(horaLlegada);
        this.terminal = terminal;
    }

    public EventoFinTerminal(EmpleadoRegistro empleadoRegistro, Terminal terminal, Date horaLlegada) {
        super(horaLlegada);
        this.empleadoRegistro = empleadoRegistro;
        this.terminal = terminal;
    }

    @Override
    public void consume() {
        empleadoRegistro.getFactura().setHoraFinAtencionTerminales(getHora());
        if (!rechazarFactura()) {
            empleadoRegistro.setOcupado();
            empleadoRegistro.getFactura().setHoraInicioSelloAprobado(getHora());
            SelloAprobadoGenerator.generate(getHora(), empleadoRegistro);
        } else {
            empleadoRegistro.setOcupado();
            empleadoRegistro.getFactura().setHoraInicioSelloRechazado(getHora());
            SelloRechazadoGenerator.generate(getHora(), empleadoRegistro);
        }
        if(MainLoop.isTerminalesQueueEmpty()) {
            terminal.setLibre();
        } else {
            empleadoRegistro.getFactura().setHoraInicioAtencionTerminales(getHora());
            terminal.setOcupado();
            terminal.setEmpleadoRegistro(empleadoRegistro);
            TerminalGenerator.generate(getHora(), MainLoop.popTerminalesQueue(), terminal);
        }
    }

    @Override
    public String getName() {
        return "Fin Atención Terminal";
    }

    private boolean rechazarFactura() {
        return ThreadLocalRandom.current().nextDouble() < probabilidadRechazar;
    }
}
