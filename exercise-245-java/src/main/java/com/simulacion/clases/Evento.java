package com.simulacion.clases;

import java.time.LocalTime;

public class Evento {
    private LocalTime tiempo;
    private TipoEvento tipo;

    public Evento(LocalTime tiempo, TipoEvento tipo) {
        this.tiempo = tiempo;
        this.tipo = tipo;
    }

    public LocalTime getTiempo() {
        return tiempo;
    }

    public void setTiempo(LocalTime tiempo) {
        this.tiempo = tiempo;
    }

    public TipoEvento getTipo() {
        return tipo;
    }

    public void setTipo(TipoEvento tipo) {
        this.tipo = tipo;
    }
}
