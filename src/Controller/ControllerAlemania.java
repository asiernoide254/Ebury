package Controller;

import Model.BD;
import View.Alemania.ErrorAlemania;

import java.io.File;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;

public class ControllerAlemania {

    public void onInicial() {
        try {
            BD b = new BD();
            //implementacion boton
            b.Modify("sentencia SQL");
        } catch (SQLException e) {
            ErrorAlemania dialog = new ErrorAlemania();
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        }
    }

    public void onSemanal() {
        try {
            BD myBD = new BD();
            //implementacion boton
            List<Object[]> resultado = myBD.Select("SELECT * FROM CuentaBanco;");

            Date fecha = new Date();
            SimpleDateFormat sd = new SimpleDateFormat("ddMMyyyyHHmmss");
            crearFicheroCSV("Ebury_IBAN_" + sd.format(fecha), resultado);

        } catch (SQLException e) {
            ErrorAlemania dialog = new ErrorAlemania();
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        }
    }

    private void crearFicheroCSV(String nFichero, List<Object[]> resultado) {
        File fd = new File(nFichero);
        try (PrintWriter pw = new PrintWriter(fd)) {

            //Printear en pw lo que quieras en el archivo
            for (Object[] tupla : resultado) {
                StringJoiner sj = new StringJoiner(",");
                for (Object objeto : tupla) {
                    if (objeto == null) {
                        sj.add("No Existente");
                    } else {
                        sj.add(objeto.toString());
                    }
                }
                pw.println(sj);
            }

        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }
}
