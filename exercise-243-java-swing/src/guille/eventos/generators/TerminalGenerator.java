package guille.eventos.generators;


import guille.MainLoop;
import guille.empleados.EmpleadoRegistro;
import guille.empleados.Terminal;
import guille.eventos.EventoFinTerminal;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Guillermo on 3/5/2017.
 */
public class TerminalGenerator {
    private static final int min = 30; //60 - 30
    private static final int max = 90; //60 + 30

    private static Calendar horaUltimaGeneracion;
    private static Date demoraUltimaGeneracion;
    private static int randomUltimaGeneracion;

    public static void generate(Date horaActual, EmpleadoRegistro empleadoRegistro, Terminal terminal) {
        Date hora = calcularProximaHora(horaActual);
        terminal.setRandomAtencion(randomUltimaGeneracion);
        terminal.setTiempohastaProxFinAt(demoraUltimaGeneracion);
        terminal.setHoraProxFinAt(horaUltimaGeneracion.getTime());
        MainLoop.getQueue().add(new EventoFinTerminal(empleadoRegistro, terminal, hora));
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
