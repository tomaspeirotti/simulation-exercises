package guille.eventos;


import guille.Factura;
import guille.MainLoop;
import guille.empleados.Administracion;
import guille.empleados.EmpleadoAdmin;
import guille.empleados.EmpleadoMesaEntrada;
import guille.eventos.generators.AdministracionGenerator;
import guille.eventos.generators.MesaEntradaGenerator;

import java.util.Date;

/**
 * Created by Guillermo on 3/5/2017.
 */
public class EventoFinMesaEntrada extends Evento {
    private Factura factura;
    private EmpleadoMesaEntrada empleadoMesaEntrada;

    public EventoFinMesaEntrada(Factura factura, EmpleadoMesaEntrada empleadoMesaEntrada, Date horaLlegada) {
        super(horaLlegada);
        this.factura = factura;
        this.empleadoMesaEntrada = empleadoMesaEntrada;
    }

    @Override
    public void consume() {
        factura.setHoraFinAtencionMesaEntrada(getHora());
        if (MainLoop.isAdminQueueEmpty()) {
            EmpleadoAdmin empleadoAdminLibre = Administracion.getEmpleadoLibre();
            if (null != empleadoAdminLibre) {
                empleadoAdminLibre.setOcupado();
                empleadoAdminLibre.setFactura(factura);
                factura.setHoraInicioAtencionAdmin(getHora());
                AdministracionGenerator.generate(getHora(), factura, empleadoAdminLibre);
            } else {
                factura.setHoraEntradaColaAdmin(getHora());
                MainLoop.addToAdminQueue(factura);
            }
        } else {
            factura.setHoraEntradaColaAdmin(getHora());
            MainLoop.addToAdminQueue(factura);
        }
        if (MainLoop.isMesaEntradaQueueEmpty()) {
            empleadoMesaEntrada.setLibre();
        } else {
            Factura proximaFactura = MainLoop.popMesaEntradaQueue();
            empleadoMesaEntrada.setOcupado();
            empleadoMesaEntrada.setFactura(proximaFactura);
            proximaFactura.setHoraInicioAtencionMesaEntrada(getHora());
            MesaEntradaGenerator.generate(getHora(), proximaFactura, empleadoMesaEntrada);
        }
    }

    @Override
    public String getName() {
        return "Fin atención Mesa de Entrada";
    }
}
