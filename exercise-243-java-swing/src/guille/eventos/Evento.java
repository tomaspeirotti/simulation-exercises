package guille.eventos;

import java.util.Date;

/**
 * Created by Guillermo on 3/5/2017.
 */
public abstract class Evento implements Comparable<Evento> {
    private Date horaGenerado;

    public Evento(Date hora) {
        this.horaGenerado = hora;
    }

    public abstract void consume() ;


    public Date getHora() {
        return horaGenerado;
    }

    @Override
    public int compareTo(Evento otroEvento) {
        return this.horaGenerado.compareTo(otroEvento.horaGenerado);
    }

    public abstract String getName();
}
