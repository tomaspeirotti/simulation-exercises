package guille;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Guillermo on 3/5/2017.
 */
public class Factura {
    private int id;
    private static int nextId = 0;
    private List<Recorrido> recorridoList;
    private int nextIdRecorrido;
    private Recorrido recorridoActual;

    public Factura(Date hora) {
        this.id = nextId;
        nextId++;
        this.recorridoList = new LinkedList<>();
        newRecorrido(hora);
    }

    public int getId() {
        return this.id;
    }

    public Recorrido getRecorridoActual() {
        return recorridoActual;
    }

    public void setRecorridoActual(Recorrido recorridoActual) {
        this.recorridoActual = recorridoActual;
    }

    public void setHoraGenerado(Date horaGenerado) {
        this.recorridoActual.horaGenerado = horaGenerado;
    }

    public void setHoraEntradaColaRecepcion(Date horaEntradaColaRecepcion) {
        this.recorridoActual.horaEntradaColaRecepcion = horaEntradaColaRecepcion;
    }

    public void setHoraInicioAtencionRecepcion(Date horaInicioAtencionRecepcion) {
        this.recorridoActual.horaInicioAtencionRecepcion = horaInicioAtencionRecepcion;
    }

    public void setHoraFinAtencionRecepcion(Date horaFinAtencionRecepcion) {
        this.recorridoActual.horaFinAtencionRecepcion = horaFinAtencionRecepcion;
    }

    public void setHoraEntradaColaRegistro(Date horaEntradaColaRegistro) {
        this.recorridoActual.horaEntradaColaRegistro = horaEntradaColaRegistro;
    }

    public void setHoraInicioAtencionRegistro(Date horaInicioAtencionRegistro) {
        this.recorridoActual.horaInicioAtencionRegistro = horaInicioAtencionRegistro;
    }

    public void setHoraFinAtencionRegistro(Date horaFinAtencionRegistro) {
        this.recorridoActual.horaFinAtencionRegistro = horaFinAtencionRegistro;
    }

    public void setHoraEntradaColaTerminales(Date horaEntradaColaTerminales) {
        this.recorridoActual.horaEntradaColaTerminales = horaEntradaColaTerminales;
    }

    public void setHoraInicioAtencionTerminales(Date horaInicioAtencionTerminales) {
        this.recorridoActual.horaInicioAtencionTerminales = horaInicioAtencionTerminales;
    }

    public void setHoraFinAtencionTerminales(Date horaFinAtencionTerminales) {
        this.recorridoActual.horaFinAtencionTerminales = horaFinAtencionTerminales;
    }

    public void setHoraInicioSelloAprobado(Date horaInicioSelloAprobado) {
        this.recorridoActual.horaInicioSelloAprobado = horaInicioSelloAprobado;
    }

    public void setHoraFinSelloAprobado(Date horaFinSelloAprobado) {
        this.recorridoActual.horaFinSelloAprobado = horaFinSelloAprobado;
    }

    public void setHoraInicioSelloRechazado(Date horaInicioSelloRechazado) {
        this.recorridoActual.horaInicioSelloRechazado = horaInicioSelloRechazado;
    }

    public void setHoraFinSelloRechazado(Date horaFinSelloRechazado) {
        this.recorridoActual.horaFinSelloRechazado = horaFinSelloRechazado;
    }

    public void setHoraEntradaColaMesaEntrada(Date horaEntradaColaMesaEntrada) {
        this.recorridoActual.horaEntradaColaMesaEntrada = horaEntradaColaMesaEntrada;
    }

    public void setHoraInicioAtencionMesaEntrada(Date horaInicioAtencionMesaEntrada) {
        this.recorridoActual.horaInicioAtencionMesaEntrada = horaInicioAtencionMesaEntrada;
    }

    public void setHoraFinAtencionMesaEntrada(Date horaFinAtencionMesaEntrada) {
        this.recorridoActual.horaFinAtencionMesaEntrada = horaFinAtencionMesaEntrada;
    }

    public void setHoraEntradaColaAdmin(Date horaEntradaColaAdmin) {
        this.recorridoActual.horaEntradaColaAdmin = horaEntradaColaAdmin;
    }

    public void setHoraInicioAtencionAdmin(Date horaInicioAtencionAdmin) {
        this.recorridoActual.horaInicioAtencionAdmin = horaInicioAtencionAdmin;
    }

    public void setHoraFinAtencionAdmin(Date horaFinAtencionAdmin) {
        this.recorridoActual.horaFinAtencionAdmin = horaFinAtencionAdmin;
    }

    public void setHoraInicioAtencionArchivar(Date horaInicioAtencionArchivar) {
        this.recorridoActual.horaInicioAtencionArchivar = horaInicioAtencionArchivar;
    }

    public void setHoraFinAtencionArchivar(Date horaFinAtencionArchivar) {
        this.recorridoActual.horaFinAtencionArchivar = horaFinAtencionArchivar;
    }

    public List<Recorrido> getRecorridos() {
        return recorridoList;
    }

    public void newRecorrido(Date hora) {
        this.recorridoActual = new Recorrido(nextIdRecorrido, hora);
        this.recorridoList.add(recorridoActual);
        nextIdRecorrido++;
    }

    public static class Recorrido {
        public int idRecorrido;

        private Date horaGenerado;
        private Date horaEntradaColaRecepcion;
        private Date horaInicioAtencionRecepcion;
        private Date horaFinAtencionRecepcion;
        private Date horaEntradaColaRegistro;
        private Date horaInicioAtencionRegistro;
        private Date horaFinAtencionRegistro;
        private Date horaEntradaColaTerminales;
        private Date horaInicioAtencionTerminales;
        private Date horaFinAtencionTerminales;
        private Date horaInicioSelloAprobado;
        private Date horaFinSelloAprobado;
        private Date horaInicioSelloRechazado;
        private Date horaFinSelloRechazado;

        private Date horaEntradaColaMesaEntrada;
        private Date horaInicioAtencionMesaEntrada;
        private Date horaFinAtencionMesaEntrada;
        private Date horaEntradaColaAdmin;
        private Date horaInicioAtencionAdmin;
        private Date horaFinAtencionAdmin;
        private Date horaInicioAtencionArchivar;
        private Date horaFinAtencionArchivar;

        Recorrido(int idRecorrido, Date hora) {
            this.idRecorrido = idRecorrido;
            this.horaGenerado = hora;
        }

        public int getIdRecorrido() {
            return idRecorrido;
        }

        public void setIdRecorrido(int idRecorrido) {
            this.idRecorrido = idRecorrido;
        }

        public Date getHoraGenerado() {
            return horaGenerado;
        }

        public void setHoraGenerado(Date horaGenerado) {
            this.horaGenerado = horaGenerado;
        }

        public Date getHoraEntradaColaRecepcion() {
            return horaEntradaColaRecepcion;
        }

        public void setHoraEntradaColaRecepcion(Date horaEntradaColaRecepcion) {
            this.horaEntradaColaRecepcion = horaEntradaColaRecepcion;
        }

        public Date getHoraInicioAtencionRecepcion() {
            return horaInicioAtencionRecepcion;
        }

        public void setHoraInicioAtencionRecepcion(Date horaInicioAtencionRecepcion) {
            this.horaInicioAtencionRecepcion = horaInicioAtencionRecepcion;
        }

        public Date getHoraFinAtencionRecepcion() {
            return horaFinAtencionRecepcion;
        }

        public void setHoraFinAtencionRecepcion(Date horaFinAtencionRecepcion) {
            this.horaFinAtencionRecepcion = horaFinAtencionRecepcion;
        }

        public Date getHoraEntradaColaRegistro() {
            return horaEntradaColaRegistro;
        }

        public void setHoraEntradaColaRegistro(Date horaEntradaColaRegistro) {
            this.horaEntradaColaRegistro = horaEntradaColaRegistro;
        }

        public Date getHoraInicioAtencionRegistro() {
            return horaInicioAtencionRegistro;
        }

        public void setHoraInicioAtencionRegistro(Date horaInicioAtencionRegistro) {
            this.horaInicioAtencionRegistro = horaInicioAtencionRegistro;
        }

        public Date getHoraFinAtencionRegistro() {
            return horaFinAtencionRegistro;
        }

        public void setHoraFinAtencionRegistro(Date horaFinAtencionRegistro) {
            this.horaFinAtencionRegistro = horaFinAtencionRegistro;
        }

        public Date getHoraEntradaColaTerminales() {
            return horaEntradaColaTerminales;
        }

        public void setHoraEntradaColaTerminales(Date horaEntradaColaTerminales) {
            this.horaEntradaColaTerminales = horaEntradaColaTerminales;
        }

        public Date getHoraInicioAtencionTerminales() {
            return horaInicioAtencionTerminales;
        }

        public void setHoraInicioAtencionTerminales(Date horaInicioAtencionTerminales) {
            this.horaInicioAtencionTerminales = horaInicioAtencionTerminales;
        }

        public Date getHoraFinAtencionTerminales() {
            return horaFinAtencionTerminales;
        }

        public void setHoraFinAtencionTerminales(Date horaFinAtencionTerminales) {
            this.horaFinAtencionTerminales = horaFinAtencionTerminales;
        }

        public Date getHoraInicioSelloAprobado() {
            return horaInicioSelloAprobado;
        }

        public void setHoraInicioSelloAprobado(Date horaInicioSelloAprobado) {
            this.horaInicioSelloAprobado = horaInicioSelloAprobado;
        }

        public Date getHoraFinSelloAprobado() {
            return horaFinSelloAprobado;
        }

        public void setHoraFinSelloAprobado(Date horaFinSelloAprobado) {
            this.horaFinSelloAprobado = horaFinSelloAprobado;
        }

        public Date getHoraInicioSelloRechazado() {
            return horaInicioSelloRechazado;
        }

        public void setHoraInicioSelloRechazado(Date horaInicioSelloRechazado) {
            this.horaInicioSelloRechazado = horaInicioSelloRechazado;
        }

        public Date getHoraFinSelloRechazado() {
            return horaFinSelloRechazado;
        }

        public void setHoraFinSelloRechazado(Date horaFinSelloRechazado) {
            this.horaFinSelloRechazado = horaFinSelloRechazado;
        }

        public Date getHoraEntradaColaMesaEntrada() {
            return horaEntradaColaMesaEntrada;
        }

        public void setHoraEntradaColaMesaEntrada(Date horaEntradaColaMesaEntrada) {
            this.horaEntradaColaMesaEntrada = horaEntradaColaMesaEntrada;
        }

        public Date getHoraInicioAtencionMesaEntrada() {
            return horaInicioAtencionMesaEntrada;
        }

        public void setHoraInicioAtencionMesaEntrada(Date horaInicioAtencionMesaEntrada) {
            this.horaInicioAtencionMesaEntrada = horaInicioAtencionMesaEntrada;
        }

        public Date getHoraFinAtencionMesaEntrada() {
            return horaFinAtencionMesaEntrada;
        }

        public void setHoraFinAtencionMesaEntrada(Date horaFinAtencionMesaEntrada) {
            this.horaFinAtencionMesaEntrada = horaFinAtencionMesaEntrada;
        }

        public Date getHoraEntradaColaAdmin() {
            return horaEntradaColaAdmin;
        }

        public void setHoraEntradaColaAdmin(Date horaEntradaColaAdmin) {
            this.horaEntradaColaAdmin = horaEntradaColaAdmin;
        }

        public Date getHoraInicioAtencionAdmin() {
            return horaInicioAtencionAdmin;
        }

        public void setHoraInicioAtencionAdmin(Date horaInicioAtencionAdmin) {
            this.horaInicioAtencionAdmin = horaInicioAtencionAdmin;
        }

        public Date getHoraFinAtencionAdmin() {
            return horaFinAtencionAdmin;
        }

        public void setHoraFinAtencionAdmin(Date horaFinAtencionAdmin) {
            this.horaFinAtencionAdmin = horaFinAtencionAdmin;
        }

        public Date getHoraInicioAtencionArchivar() {
            return horaInicioAtencionArchivar;
        }

        public void setHoraInicioAtencionArchivar(Date horaInicioAtencionArchivar) {
            this.horaInicioAtencionArchivar = horaInicioAtencionArchivar;
        }

        public Date getHoraFinAtencionArchivar() {
            return horaFinAtencionArchivar;
        }

        public void setHoraFinAtencionArchivar(Date horaFinAtencionArchivar) {
            this.horaFinAtencionArchivar = horaFinAtencionArchivar;
        }
    }
}
