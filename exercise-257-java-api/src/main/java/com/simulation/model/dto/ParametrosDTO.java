package com.simulation.model.dto;

import java.util.List;

public class ParametrosDTO {

    private int arrivos;
    private int longitudMaxima;
    private int horaComienzo;
    private List<IntervaloDTO> intervalos;
    private ParametroDTO atencion;
    private boolean acc;

    public boolean isAcc() {
        return acc;
    }

    public void setAcc(boolean acc) {
        this.acc = acc;
    }

    public int getArrivos() {
        return arrivos;
    }

    public void setArrivos(int arrivos) {
        this.arrivos = arrivos;
    }

    public int getLongitudMaxima() {
        return longitudMaxima;
    }

    public void setLongitudMaxima(int longitudMaxima) {
        this.longitudMaxima = longitudMaxima;
    }

    public int getHoraComienzo() {
        return horaComienzo;
    }

    public void setHoraComienzo(int horaComienzo) {
        this.horaComienzo = horaComienzo;
    }

    public List<IntervaloDTO> getIntervalos() {
        return intervalos;
    }

    public void setIntervalos(List<IntervaloDTO> intervalos) {
        this.intervalos = intervalos;
    }

    public ParametroDTO getAtencion() {
        return atencion;
    }

    public void setAtencion(ParametroDTO atencion) {
        this.atencion = atencion;
    }
}

/*
simulation: {
        arrivos: 4500,
        longitudMaxima: 0,
        horaDeComienzo: 7
      },
      atencion: {
        media: 10,
        varianza: 4
      },
      intervaloUno: {
        numero: 1,
        media: 6,
        varianza: 1
      },
      intervaloDos: {
        numero: 2,
        media: 5,
        varianza: 1
      },
      intervaloTres: {
        numero: 3,
        media: 7,
        varianza: 1
      },
      cliente: {
          label: 'Cliente',
          field: 'cliente',
          type: 'text',
          sortable: false
      },
      columns: [
        {
          label: 'Tiempo',
          field: 'tiempo',
          type: 'text',
          sortable: false
        },
        {
          label: 'Frec. de arrivos',
          field: 'frecArrivos',
          type: 'text',
          sortable: false
        },
        {
          label: 'TipoEvento',
          field: 'evento',
          type: 'text',
          sortable: false
        },
        {
          label: 'RND',
          field: 'rnd',
          type: 'text',
          sortable: false
        },
        {
          label: 'Prox. arrivo',
          field: 'proxArrivo',
          type: 'number',
          sortable: false
        },
        {
          label: 'Emp 1 - EstadoEmpleado',
          field: 'emp1Estado',
          type: 'text',
          sortable: false
        },
        {
          label: 'Emp 1 - Tiempo fin At.',
          field: 'emp1FinAt',
          type: 'text',
          sortable: false
        },
        {
          label: 'Emp 1 - Cola',
          field: 'emp1Cola',
          type: 'number',
          sortable: false
        },
        {
          label: 'Emp 2 - EstadoEmpleado',
          field: 'emp2Estado',
          type: 'text',
          sortable: false
        },
        {
          label: 'Emp 2 - Tiempo fin At.',
          field: 'emp2FinAt',
          type: 'text',
          sortable: false
        },
        {
          label: 'Emp 2 - Cola',
          field: 'emp2Cola',
          type: 'number',
          sortable: false
        }
      ]
 */
