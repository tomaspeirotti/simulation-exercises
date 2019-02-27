package main;

import entities.Iteracion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import java.util.LinkedList;

public class Simulation {

    public static void start(int desdeEvento, int hastaEvento, Date desdeHora, Date hastaHora, JTable tableResults) {
        DefaultTableModel tableModel = new DefaultTableModel();
        initializeTableColumns(tableModel);
        LinkedList<Iteracion> simulacion = new LinkedList<>();
        while (true) {
            Iteracion iteracion = new Iteracion();
            simulacion.addLast(iteracion);
            tableModel.addRow(parse(iteracion));
        }
//        tableResults.setModel(tableModel);
    }

    private static String[] parse(Iteracion iteracion) {
        String[] row = new String[9999999];
        row[0] = "";
        row[1] = "";
        return row;
    }

    private static void initializeTableColumns(DefaultTableModel tableModel) {
        tableModel.addColumn("Nº");
    }
}
