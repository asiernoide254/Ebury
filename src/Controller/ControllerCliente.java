package Controller;

import Model.BD;
import View.Cliente.*;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ControllerCliente {
    private List<Integer> idsPersonasRelacionadas;

    public ControllerCliente() {
        idsPersonasRelacionadas = new ArrayList<>();
    }

    //Boton de registro empresa clicado
    public void onRegistrarseEmpresa(JFrame frame) {
        frame.dispose();

        RegistroEmpresa rp = new RegistroEmpresa("Ebury");
        rp.pack();
        rp.setLocationRelativeTo(null);
        rp.setVisible(true);
    }

    //Boton de registro persona fisica clicado
    public void onRegistrarsePersona(JFrame frame) {
        frame.dispose();

        RegistroPersona rp = new RegistroPersona("Ebury");
        rp.pack();
        rp.setLocationRelativeTo(null);
        rp.setVisible(true);
    }

    public void onCompletarRegistroEmpresa(RegistroEmpresa formulario) {
        String tCIF = formulario.gettCIF().getText();
        String tNombre = formulario.gettNombre().getText().replace("'", "\\'");
        String tCalle = formulario.gettCalle().getText();
        String tPlantaPuertaOficina = formulario.gettPlantaPuertaOficina().getText();
        String tCiudad = formulario.gettCiudad().getText();
        String tPais = formulario.gettPais().getText();
        String tNumero = formulario.gettNumero().getText();
        String tRegion = formulario.gettRegion().getText();
        String tCP = formulario.gettCP().getText();
        boolean validaDireccionActual = formulario.getValidaDireccionActualCheckBox().isSelected();
        String passwordField1 = new String(formulario.getPasswordField1().getPassword());
        String passwordField2 = new String(formulario.getPasswordField2().getPassword());

        //Insertar en la base de datos
        if (algunoNulo(tCIF, tNombre, tCalle, tPlantaPuertaOficina, tCiudad, tPais, tNumero, tCP) || passwordField1.isEmpty() || !passwordField1.equals(passwordField2) || !soloLetras(tCalle, tCiudad, tPais, tRegion) || !esInteger(tNumero) || tCP.length() != 5 || !esInteger(tCP) || !cifCorrecto(tCIF)) {
            ErrorDatosRegistroDialog dialog = new ErrorDatosRegistroDialog();
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            try {
                BD miBD = new BD();
                Date today = new Date();

                miBD.Modify("INSERT INTO Cliente(numeroIdentificacion, estado, fechaInicio, password) VALUES('" + tCIF + "', 'Activo', '" + formatter.format(today) + "', '" + passwordField1 + "');");
                int clt = (int) miBD.SelectEscalar("SELECT MAX(id) FROM Cliente;");
                miBD.Modify("INSERT INTO Empresa(cliente, nombre) VALUES(" + clt + ", '" + tNombre + "');");
                miBD.Modify("INSERT INTO Direccion(calle, numero, plantaPuertaOficina, ciudad, region, codigoPostal, pais, valida, cliente) VALUES('" + tCalle + "', " + tNumero + ", '" + tPlantaPuertaOficina + "', '" + tCiudad + "', '" + tRegion + "', " + tCP + ", '" + tPais + "', " + (validaDireccionActual ? 1 : 0) + ", " + clt + ");");

                formulario.dispose();

                GestionRelEmpresa relEmpresa = new GestionRelEmpresa("Ebury", clt);
                relEmpresa.pack();
                relEmpresa.setLocationRelativeTo(null);
                relEmpresa.setVisible(true);
            } catch (SQLException e) {
                ErrorBDRegistroDialog dialog = new ErrorBDRegistroDialog(formulario);
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        }
    }

    private boolean algunoNulo(String... args) {
        for (String arg : args) {
            if (arg.isBlank()) {
                return true;
            }
        }
        return false;
    }

    private boolean esInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean soloLetras(String... str) {
        boolean res = true;
        int i = 0;
        while (res && i < str.length) {
            String aux = str[i];
            int j = 0;
            while (res && j < aux.length()) {
                res = Character.isLetter(aux.charAt(j)) || aux.charAt(j) == ' ';
                j++;
            }
            i++;
        }
        return res;
    }

    private boolean cifCorrecto(String cif) {
        return cif.length() == 9 && letraCifCorrecta(cif) && esInteger(cif.substring(1, 9));
    }

    private boolean letraCifCorrecta(String cif) {
        String letrasCorrectas = "ABCDEFGHPQSKLMX";
        String letra = String.valueOf(Character.toUpperCase(cif.charAt(0)));
        return letrasCorrectas.contains(letra);
    }

    public void onCompletarRegistroPersona(RegistroPersona formulario) {
        String nif = formulario.getNif().getText();
        String nombre = formulario.gettPrimerNombre().getText();
        String segundoNombre = formulario.gettSegundoNombre().getText();
        String primerApellido = formulario.gettPrimerApellido().getText();
        String segundoApellido = formulario.gettSegundoApellido().getText();
        JDateChooser datechooser = formulario.getJDateChooser1();
        String tCalle = formulario.gettCalle().getText();
        String tPlantaPuertaOficina = formulario.gettPlantaPuertaOficina().getText();
        String tCiudad = formulario.gettCiudad().getText();
        String tPais = formulario.gettPais().getText();
        String tNumero = formulario.gettNumero().getText();
        String tRegion = formulario.gettRegion().getText();
        String tCP = formulario.getCP().getText();

        boolean validaDireccionActual = formulario.getValidaDireccionActualCheckBox().isSelected();

        String passwordField1 = new String(formulario.getPasswordField1().getPassword());
        String passwordField2 = new String(formulario.getPasswordField2().getPassword());

        Date fechaNacimiento = datechooser.getDate();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();

        //Contraseñas no iguales
        if (algunoNulo(nif, nombre, primerApellido, tCalle, tPlantaPuertaOficina, tCiudad, tPais, tNumero, tCP) || passwordField1.isEmpty() || !passwordField1.equals(passwordField2) || !soloLetras(nombre, segundoNombre, primerApellido, segundoApellido, tCalle, tCiudad, tPais, tRegion) || !esInteger(tNumero) || tCP.length() != 5 || !esInteger(tCP) || !nifCorrecto(nif) || fechaNacimiento == null || fechaNacimiento.after(today)) {
            ErrorDatosRegistroDialog dialog = new ErrorDatosRegistroDialog();
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        } else {
            //insertar cliente en BD
            try {
                BD miBD = new BD();

                miBD.Modify("INSERT INTO Cliente(numeroIdentificacion, estado, fechaInicio, password)" + " VALUES('" + nif + "', 'Activo', '" + formatter.format(today) + "', '" + passwordField1 + "');");
                miBD.Modify("INSERT INTO Persona(nombre, segundoNombre, apellido, segundoApellido, fechaNacimiento)" + " VALUES('" + nombre + "', '" + segundoNombre + "', '" + primerApellido + "', '" + segundoApellido + "', '" + formatter.format(fechaNacimiento) + "');");
                int clt = (int) miBD.SelectEscalar("SELECT MAX(id) FROM Cliente;");
                int pers = (int) miBD.SelectEscalar("SELECT MAX(id) FROM Persona;");
                miBD.Modify("INSERT INTO Individual(cliente, persona) VALUES(" + clt + ", " + pers + ");");
                miBD.Modify("INSERT INTO Direccion(calle, numero, plantaPuertaOficina, ciudad, region, codigoPostal, pais, valida, cliente)" + " VALUES('" + tCalle + "', " + tNumero + ", '" + tPlantaPuertaOficina + "', '" + tCiudad + "', '" + tRegion + "', " + tCP + ", '" + tPais + "', " + (validaDireccionActual ? 1 : 0) + ", " + clt + ");");

                ExitoRegistroDialog dialog = new ExitoRegistroDialog(formulario);
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);

            } catch (SQLException e) {
                ErrorBDRegistroDialog dialog = new ErrorBDRegistroDialog(formulario);
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }

        }
    }

    private boolean nifCorrecto(String nif) {
        return nif.length() == 9 && esInteger(nif.substring(0, 8)) && Character.isLetter(nif.charAt(8));
    }

    public void onCargarRegistroPrincipal(JFrame frame) {
        frame.dispose();

        RegistroPrincipal rp = new RegistroPrincipal("Ebury");
        rp.pack();
        rp.setLocationRelativeTo(null);
        rp.setVisible(true);
    }

    public void onCargarPrincipalCliente(JFrame frame) {
        frame.dispose();

        PrincipalCliente pc = new PrincipalCliente("Ebury");
        pc.pack();
        pc.setLocationRelativeTo(null);
        pc.setVisible(true);
    }

    public void onFinalizarRegistroEmpresa(GestionRelEmpresa frame) {
        //Supongo que aqui enseña el dialog y punto, porque realmente al darle a añadir ya añadiste todas las personas relacionadas

        ExitoRegistroDialog dialog = new ExitoRegistroDialog(frame);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    public void onBorrarPersonaRelacionada(JTable table) {
        int fila = table.getSelectedRow();
        if (fila >= 1) {
            try {
                BD miBD = new BD();
                int idCliente = idsPersonasRelacionadas.remove(fila - 1);
                int idPersona = (int) miBD.SelectEscalar("SELECT P.id FROM Persona P, Individual I, Cliente C WHERE C.id = " + idCliente + " AND C.id = I.cliente AND P.id = I.persona;");
                miBD.Modify("DELETE FROM Cliente WHERE id = " + idCliente + ";");
                miBD.Modify("DELETE FROM Persona WHERE id = " + idPersona + ";");
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.removeRow(fila);
            } catch (SQLException e) {
                ErrorBDRegistroDialog dialog = new ErrorBDRegistroDialog(null);
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        }
    }

    public void onAnyadirPersonaRelacionada(int idEmpresa, GestionRelEmpresa formulario) {
        String nif = formulario.getNif().getText();
        String nombre = formulario.gettPrimerNombre().getText();
        String segundoNombre = formulario.gettSegundoNombre().getText();
        String primerApellido = formulario.gettPrimerApellido().getText();
        String segundoApellido = formulario.gettSegundoApellido().getText();
        JDateChooser datechooser = formulario.getJDateChooser1();
        String tipoPersonaRelacionada = formulario.getComboBox1().getSelectedItem().toString();
        String tCalle = formulario.gettCalle().getText();
        String tPlantaPuertaOficina = formulario.gettPlantaPuertaOficina().getText();
        String tCiudad = formulario.gettCiudad().getText();
        String tPais = formulario.gettPais().getText();
        String tNumero = formulario.gettNumero().getText();
        String tRegion = formulario.gettRegion().getText();
        String tCP = formulario.getCP().getText();

        boolean validaDireccionActual = formulario.getValidaDireccionActualCheckBox().isSelected();

        String passwordField1 = new String(formulario.getPasswordField1().getPassword());
        String passwordField2 = new String(formulario.getPasswordField2().getPassword());

        Date fechaNacimiento = datechooser.getDate();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();

        if (algunoNulo(nif, nombre, primerApellido, tCalle, tPlantaPuertaOficina, tCiudad, tPais, tNumero, tCP) || passwordField1.isEmpty() || !passwordField1.equals(passwordField2) || !soloLetras(nombre, segundoNombre, primerApellido, segundoApellido, tCalle, tCiudad, tPais, tRegion) || !esInteger(tNumero) || tCP.length() != 5 || !esInteger(tCP) || !nifCorrecto(nif) || fechaNacimiento == null || fechaNacimiento.after(today)) {
            ErrorDatosRegistroDialog dialog = new ErrorDatosRegistroDialog();
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        } else {
            try {
                BD miBD = new BD();
                miBD.Modify("INSERT INTO Cliente(numeroIdentificacion, estado, fechaInicio, password) VALUES('" + nif + "', 'Activo', '" + formatter.format(today) + "', '" + passwordField1 + "');");
                miBD.Modify("INSERT INTO Persona(nombre, segundoNombre, apellido, segundoApellido, fechaNacimiento) VALUES('" + nombre + "', '" + segundoNombre + "', '" + primerApellido + "', '" + segundoApellido + "', '" + formatter.format(fechaNacimiento) + "');");
                int clt = (int) miBD.SelectEscalar("SELECT MAX(id) FROM Cliente;");
                int pers = (int) miBD.SelectEscalar("SELECT MAX(id) FROM Persona;");
                miBD.Modify("INSERT INTO Individual(cliente, persona) VALUES(" + clt + ", " + pers + ");");
                miBD.Modify("INSERT INTO Direccion(calle, numero, plantaPuertaOficina, ciudad, region, codigoPostal, pais, valida, cliente) VALUES('" + tCalle + "', " + tNumero + ", '" + tPlantaPuertaOficina + "', '" + tCiudad + "', '" + tRegion + "', " + tCP + ", '" + tPais + "', " + (validaDireccionActual ? 1 : 0) + ", " + clt + ");");
                miBD.Modify("INSERT INTO PersonaRelacionada(cliente, persona, tipoClienteRelacionado, clienteRelacionado) VALUES(" + clt + ", " + pers + ", '" + tipoPersonaRelacionada + "', " + idEmpresa + ");");

                DefaultTableModel model = (DefaultTableModel) formulario.getTable1().getModel();
                int rowCount = model.getRowCount();
                String nombreCompleto = getNombreCompleto(nombre, segundoNombre, primerApellido, segundoApellido);
                model.addRow(new String[]{nombreCompleto, nif, tipoPersonaRelacionada});
                model.fireTableRowsInserted(rowCount, rowCount);
                idsPersonasRelacionadas.add(clt);

                onLimpiarFormulario(formulario);
            } catch (SQLException e) {
                ErrorBDRegistroDialog dialog = new ErrorBDRegistroDialog(null);
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        }
    }

    private String getNombreCompleto(String nombre, String segundoNombre, String primerApellido, String segundoApellido) {
        return nombre + (segundoNombre.isEmpty() ? "" : " " + segundoNombre)
                + " " + primerApellido + (segundoApellido.isEmpty() ? "" : " " + segundoApellido);
    }

    public void onLimpiarFormulario(GestionRelEmpresa formulario) {
        formulario.getNif().setText("");
        formulario.gettPrimerNombre().setText("");
        formulario.gettPrimerApellido().setText("");
        formulario.gettSegundoApellido().setText("");
        formulario.gettSegundoNombre().setText("");
        formulario.getJDateChooser1().setDate(null);
        formulario.getComboBox1().setSelectedIndex(0);
        formulario.gettCalle().setText("");
        formulario.gettPlantaPuertaOficina().setText("");
        formulario.gettCiudad().setText("");
        formulario.gettPais().setText("");
        formulario.gettNumero().setText("");
        formulario.gettRegion().setText("");
        formulario.getCP().setText("");
        formulario.getValidaDireccionActualCheckBox().setSelected(false);
        formulario.getPasswordField1().setText("");
        formulario.getPasswordField2().setText("");
    }
}
