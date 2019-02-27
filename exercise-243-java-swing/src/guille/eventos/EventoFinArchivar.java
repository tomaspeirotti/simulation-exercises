package guille.eventos;


import guille.Factura;
import guille.MainLoop;
import guille.empleados.EmpleadoAdmin;
import guille.eventos.generators.AdministracionGenerator;

import java.util.Date;

/**
 * Created by Guillermo on 3/5/2017.
 */
public class EventoFinArchivar extends Evento {
    private EmpleadoAdmin empleadoAdmin;


    public EventoFinArchivar(EmpleadoAdmin empleadoAdmin, Date horaLlegada) {
        super(horaLlegada);
        this.empleadoAdmin = empleadoAdmin;
    }

    @Override
    public void consume() {
        empleadoAdmin.getFactura().setHoraFinAtencionArchivar(getHora());
        if(MainLoop.isAdminQueueEmpty()) {
            empleadoAdmin.setLibre();
        } else {
            Factura factura = MainLoop.popAdminQueue();
            empleadoAdmin.setOcupado();
            empleadoAdmin.setFactura(factura);
            factura.setHoraInicioAtencionAdmin(getHora());
            AdministracionGenerator.generate(getHora(), factura, empleadoAdmin);
        }
    }

    @Override
    public String getName() {
        return "Fin atención Archivar";
    }
}
