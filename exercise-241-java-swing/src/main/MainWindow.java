package main;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Guillermo on 3/5/2017.
 */
public class MainWindow {
    private JButton buttonSimular;
    private JTable tableResults;
    private JPanel mainWindow;
    private JTextField txtDesdeEvento;
    private JTextField txtHastaEvento;
    private JTextField txtDesdeHora;
    private JTextField txtHastaHora;

    public MainWindow() {
        PlainDocument docTxtDesdeEvento = (PlainDocument) txtDesdeEvento.getDocument();
        docTxtDesdeEvento.setDocumentFilter(new MyIntFilter());
        PlainDocument docTxtHastaEvento = (PlainDocument) txtHastaEvento.getDocument();
        docTxtHastaEvento.setDocumentFilter(new MyIntFilter());

        PlainDocument docTxtDesdeHora = (PlainDocument) txtDesdeHora.getDocument();
        docTxtDesdeHora.setDocumentFilter(new MyHourFilter());
        PlainDocument docTxtHastaHora = (PlainDocument) txtHastaHora.getDocument();
        docTxtHastaHora.setDocumentFilter(new MyHourFilter());

        buttonSimular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableResults.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                //agarra inputs y valida formato
                int desdeEvento;
                try {
                    desdeEvento = Integer.parseInt(txtDesdeEvento.getText());
                } catch (NumberFormatException nfe) {
                    desdeEvento = 0;
                }
                int hastaEvento;
                try {
                    hastaEvento = Integer.parseInt(txtHastaEvento.getText());
                } catch (NumberFormatException nfe) {
                    hastaEvento = Integer.MAX_VALUE;
                }
                DateFormat df = new SimpleDateFormat("hh:mm");
                Date desdeHora = null, hastaHora = null;
                try {
                    desdeHora = df.parse(txtDesdeHora.getText());
                    hastaHora = df.parse(txtHastaHora.getText());
                } catch (ParseException pe) {
                    JOptionPane.showMessageDialog(null, "Error en el formato de las fechas");
                    return;
                }
                Simulation.start(desdeEvento, hastaEvento, desdeHora, hastaHora, tableResults);
                resizeColumnWidth(tableResults);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainWindow");
        frame.setContentPane(new MainWindow().mainWindow);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 150; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            if (width > 300)
                width = 300;
            columnModel.getColumn(column).setPreferredWidth(width);
        }
        columnModel.getColumn(0).setPreferredWidth(38);
    }




    class MyIntFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string,
                                 AttributeSet attr) throws BadLocationException {

            Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder();
            sb.append(doc.getText(0, doc.getLength()));
            sb.insert(offset, string);

            if (test(sb.toString())) {
                super.insertString(fb, offset, string, attr);
            } else {
                // warn the user and don't allow the insert
            }
        }

        private boolean test(String text) {
            try {
                Integer.parseInt(text);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text,
                            AttributeSet attrs) throws BadLocationException {

            Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder();
            sb.append(doc.getText(0, doc.getLength()));
            sb.replace(offset, offset + length, text);

            if (test(sb.toString())) {
                super.replace(fb, offset, length, text, attrs);
            } else {
                // warn the user and don't allow the insert
            }

        }

        @Override
        public void remove(FilterBypass fb, int offset, int length)
                throws BadLocationException {
            Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder();
            sb.append(doc.getText(0, doc.getLength()));
            sb.delete(offset, offset + length);

            if (test(sb.toString())) {
                super.remove(fb, offset, length);
            } else {
                // warn the user and don't allow the insert
            }

        }
    }

    class MyHourFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string,
                                 AttributeSet attr) throws BadLocationException {

            Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder();
            sb.append(doc.getText(0, doc.getLength()));
            sb.insert(offset, string);

            if (test(sb.toString())) {
                super.insertString(fb, offset, string, attr);
            } else {
                // warn the user and don't allow the insert
            }
        }

        private boolean test(String text) {
            String textToParse = text.replace(":", "");
            try {
                Integer.parseInt(textToParse);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text,
                            AttributeSet attrs) throws BadLocationException {

            Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder();
            sb.append(doc.getText(0, doc.getLength()));
            sb.replace(offset, offset + length, text);

            if (test(sb.toString())) {
                super.replace(fb, offset, length, text, attrs);
            } else {
                // warn the user and don't allow the insert
            }

        }

        @Override
        public void remove(FilterBypass fb, int offset, int length)
                throws BadLocationException {
            Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder();
            sb.append(doc.getText(0, doc.getLength()));
            sb.delete(offset, offset + length);

            if (test(sb.toString())) {
                super.remove(fb, offset, length);
            } else {
                // warn the user and don't allow the insert
            }

        }
    }
}
