package com.simulation.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

public class IntervaloDTO extends ParametroDTO {

    @JsonFormat(pattern = "HH:mm:ss")
    private String horaDesde;
    @JsonFormat(pattern = "HH:mm:ss")
    private String horaHasta;

    public String getHoraDesde() {
        return horaDesde;
    }

    public void setHoraDesde(String horaDesde) {
        this.horaDesde = horaDesde;
    }

    public String getHoraHasta() {
        return horaHasta;
    }

    public void setHoraHasta(String horaHasta) {
        this.horaHasta = horaHasta;
    }
}
