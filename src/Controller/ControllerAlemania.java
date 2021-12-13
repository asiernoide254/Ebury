package Controller;

import Model.BD;
import View.Alemania.ErrorAlemania;
import com.google.gson.Gson;

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
            BD myBD = new BD();
            //implementacion boton
            List<Object[]> resultado = myBD.Select("SELECT * FROM CuentaBanco;");
            crearFicheroCSV(resultado);
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
            crearFicheroCSV(resultado);
        } catch (SQLException e) {
            ErrorAlemania dialog = new ErrorAlemania();
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        }
    }

    private void crearFicheroCSV(List<Object[]> resultado) {
        //Generar nombre de archivo
        Date fecha = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("ddMMyyyyHHmmss");
        String nFichero = "Ebury_IBAN_" + sd.format(fecha);

        //Generar archivo CSV
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
