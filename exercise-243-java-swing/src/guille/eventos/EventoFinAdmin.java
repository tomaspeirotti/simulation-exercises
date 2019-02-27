package guille.eventos;


import guille.Factura;
import guille.empleados.EmpleadoAdmin;
import guille.eventos.generators.ArchivarGenerator;

import java.util.Date;

/**
 * Created by Guillermo on 3/5/2017.
 */
public class EventoFinAdmin extends Evento {
    private Factura factura;
    private EmpleadoAdmin empleadoAdmin;

    public EventoFinAdmin(Factura factura, EmpleadoAdmin empleadoAdmin, Date horaLlegada) {
        super(horaLlegada);
        this.factura = factura;
        this.empleadoAdmin = empleadoAdmin;
    }

    @Override
    public void consume() {
        factura.setHoraFinAtencionAdmin(getHora());
        factura.setHoraInicioAtencionArchivar(getHora());
        ArchivarGenerator.generate(getHora(), empleadoAdmin);
    }

    @Override
    public String getName() {
        return "Fin de atención Admin";
    }


}
