package guille.eventos.generators;


import guille.MainLoop;
import guille.empleados.EmpleadoAdmin;
import guille.eventos.EventoFinArchivar;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Guillermo on 3/5/2017.
 */
public class ArchivarGenerator {
    private static final int min = 10; //20 - 10
    private static final int max = 30; //20 + 10

    private static Calendar horaUltimaGeneracion;
    private static Date demoraUltimaGeneracion;
    private static int randomUltimaGeneracion;

    public static void generate(Date horaActual, EmpleadoAdmin empleadoAdmin) {
        Date hora = calcularProximaHora(horaActual);
        empleadoAdmin.setRandomArchivar(randomUltimaGeneracion);
        empleadoAdmin.setTiempohastaProxFinArchivar(demoraUltimaGeneracion);
        empleadoAdmin.setHoraProxFinArchivar(horaUltimaGeneracion.getTime());
        MainLoop.getQueue().add(new EventoFinArchivar(empleadoAdmin, hora));
    }

    public static Date calcularProximaHora(Date horaActual) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(horaActual);
        int delayInSeconds = getDelayInSeconds();
        setDemoraUltimaGeneracion(delayInSeconds);
        calendar.add(Calendar.SECOND, delayInSeconds);
        horaUltimaGeneracion = calendar;
        return calendar.getTime();
    }

    private static void setDemoraUltimaGeneracion(int delayInSeconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.SECOND, delayInSeconds);
        demoraUltimaGeneracion = calendar.getTime();
    }

    public static void initialize(Date horaInicial) {
        horaUltimaGeneracion = Calendar.getInstance();
        horaUltimaGeneracion.setTime(horaInicial);
    }

    private static int getDelayInSeconds() {
        int random = ThreadLocalRandom.current().nextInt(min, max + 1); //tambien se puede obtener mediante min + (max - min) * nextInt()
        randomUltimaGeneracion = random;
        return random;
    }
    public static Calendar getHoraUltimaGeneracion() {
        return horaUltimaGeneracion;
    }

    public static Date getDemoraUltimaGeneracion() {
        return demoraUltimaGeneracion;
    }

    public static int getRandomUltimaGeneracion() {
        return randomUltimaGeneracion;
    }
}
