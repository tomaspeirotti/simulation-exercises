package com.simulation.model.dto;

public class ParametrosDTO {

    private int arrivos;
    private int horaComienzo;
    private ParametroDTO atencion;
    private double frecuenciaArrivos;
    private boolean peluqueroExtra;

    public ParametroDTO getAtencion() {
        return atencion;
    }

    public void setAtencion(ParametroDTO atencion) {
        this.atencion = atencion;
    }

    public int getArrivos() {
        return arrivos;
    }

    public void setArrivos(int arrivos) {
        this.arrivos = arrivos;
    }

    public int getHoraComienzo() {
        return horaComienzo;
    }

    public void setHoraComienzo(int horaComienzo) {
        this.horaComienzo = horaComienzo;
    }

    public double getFrecuenciaArrivos() {
        return frecuenciaArrivos;
    }

    public void setFrecuenciaArrivos(double frecuenciaArrivos) {
        this.frecuenciaArrivos = frecuenciaArrivos;
    }

    public boolean isPeluqueroExtra() {
        return peluqueroExtra;
    }

    public void setPeluqueroExtra(boolean peluqueroExtra) {
        this.peluqueroExtra = peluqueroExtra;
    }
}
