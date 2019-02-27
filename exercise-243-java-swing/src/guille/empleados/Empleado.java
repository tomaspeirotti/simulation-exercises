package guille.empleados;

import java.util.Date;

/**
 * Created by Guillermo on 3/6/2017.
 */
public class Empleado {
    private int randomAtencion;
    private Date tiempohastaProxFinAt;
    private Date horaProxFinAt;

    public int getRandomAtencion() {
        return randomAtencion;
    }

    public Date getTiempohastaProxFinAt() {
        return tiempohastaProxFinAt;
    }

    public Date getHoraProxFinAt() {
        return horaProxFinAt;
    }

    public void setRandomAtencion(int randomAtencion) {
        this.randomAtencion = randomAtencion;
    }

    public void setTiempohastaProxFinAt(Date tiempohastaProxFinAt) {
        this.tiempohastaProxFinAt = tiempohastaProxFinAt;
    }

    public void setHoraProxFinAt(Date horaProxFinAt) {
        this.horaProxFinAt = horaProxFinAt;
    }

}
