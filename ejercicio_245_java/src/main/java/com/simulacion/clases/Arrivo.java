package com.simulacion.clases;

import java.time.LocalTime;

public class Arrivo {

    private LocalTime proxArrivo;

    public Arrivo(LocalTime proxArrivo) {
        this.proxArrivo = proxArrivo;
    }

    public LocalTime getProxArrivo() {
        return proxArrivo;
    }

    public void setProxArrivo(LocalTime proxArrivo) {
        this.proxArrivo = proxArrivo;
    }
}
