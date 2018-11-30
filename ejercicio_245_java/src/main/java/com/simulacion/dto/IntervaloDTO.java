package com.simulacion.dto;

public class IntervaloDTO extends ParametroDTO {

    private int horaDesde;
    private int horaHasta;

    public int getHoraDesde() {
        return horaDesde;
    }

    public void setHoraDesde(int horaDesde) {
        this.horaDesde = horaDesde;
    }

    public int getHoraHasta() {
        return horaHasta;
    }

    public void setHoraHasta(int horaHasta) {
        this.horaHasta = horaHasta;
    }
}
