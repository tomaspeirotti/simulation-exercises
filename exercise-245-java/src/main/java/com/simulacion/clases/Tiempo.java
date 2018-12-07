package com.simulacion.clases;

import java.time.LocalTime;

public class Tiempo {

    private LocalTime tiempo;

    public Tiempo(LocalTime tiempo) {
        this.tiempo = tiempo;
    }

    public LocalTime getTiempo() {
        return tiempo;
    }

    public void setTiempo(LocalTime tiempo) {
        this.tiempo = tiempo;
    }
}
