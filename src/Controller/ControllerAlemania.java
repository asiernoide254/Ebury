package Controller;

import Model.BD;
import View.Alemania.ErrorAlemania;
import View.Alemania.ExitoAlemania;

import java.io.File;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;

public class ControllerAlemania {
    private final String EMP =
            "SELECT s.cuentaReferencia, null, e.nombre, d.calle, d.ciudad, d.codigoPostal, d.pais, e.cliente, ce.fechaApertura FROM Empresa e " +
                    "JOIN Cliente c ON  e.cliente=c.id " +
                    "JOIN CuentaEbury ce ON ce.propietario=c.id " +
                    "JOIN " +
                    "(SELECT id, cuentaReferencia FROM Segregada " +
                    "UNION " +
                    "SELECT id, cuentaReferencia FROM Dedicada " +
                    "UNION " +
                    "SELECT id, cuentaReferencia FROM Pooled) s ON s.id = ce.id " +
                    "JOIN Direccion d ON d.cliente = c.id " +
                    "JOIN CuentaBanco cb ON s.cuentaReferencia = cb.ibanCuenta " +
                    "WHERE cb.pais = 'Alemania' AND d.valida = 1";
    private final String IND =
            "SELECT s.cuentaReferencia, concat(p.apellido, ' ', COALESCE(p.segundoApellido, '')) 'apellidos', concat(p.nombre, ' ', COALESCE(p.segundoNombre, '')) 'nombre', d.calle, d.ciudad, d.codigoPostal, d.pais, i.cliente , p.fechaNacimiento FROM Individual i JOIN Persona p ON p.id = i.persona " +
                    "JOIN Cliente c ON c.id = i.cliente " +
                    "JOIN Direccion d ON d.cliente = c.id " +
                    "JOIN CuentaEbury ce ON ce.propietario = c.id " +
                    "JOIN " +
                    "(SELECT id, cuentaReferencia FROM Segregada " +
                    "UNION " +
                    "SELECT id, cuentaReferencia FROM Dedicada " +
                    "UNION " +
                    "SELECT id, cuentaReferencia FROM Pooled) s ON s.id = ce.id " +
                    "JOIN CuentaBanco cb ON cb.ibanCuenta = s.cuentaReferencia " +
                    "WHERE cb.pais = 'Alemania' AND d.valida = 1";

    public void onInicial() {
        try {
            BD myBD = new BD();
            //implementacion boton
            //Lista con todas las empresas
            List<Object[]> resultado = myBD.Select(EMP + ";");
            //Añado la lista con todos los individuos a la de empresas
            resultado.addAll(myBD.Select(IND + ";"));
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
            //Lista con todas las empresas
            List<Object[]> resultado = myBD.Select(EMP + " AND ce.estadoCuenta = 'Activa';");
            //Añado la lista con todos los individuos a la de empresas
            resultado.addAll(myBD.Select(IND + " AND ce.estadoCuenta = 'Activa';"));
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
        String nFichero = "Ebury_IBAN_" + sd.format(fecha) + ".csv";

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

            //Enseñar dialogo de exito
            ExitoAlemania dialog = new ExitoAlemania();
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);

        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }
}
