package Controller;

import Model.BD;
import View.Cliente.*;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ControllerCliente {

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
        String tNombre = formulario.gettNombre().getText();
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
        if (algunoNulo(tCIF, tNombre, tCalle, tPlantaPuertaOficina, tCiudad, tPais, tNumero, tRegion, tCP, passwordField1, passwordField2) || !passwordField1.equals(passwordField2) || !soloLetras(tNombre, tCalle, tCiudad, tPais, tRegion) || !esInteger(tNumero) || tCP.length() != 5 || !esInteger(tCP) || !cifCorrecto(tCIF)) {
            ErrorDatosRegistroDialog dialog = new ErrorDatosRegistroDialog();
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        try {
            BD miBD = new BD();
            Date today = new Date();

            miBD.Modify("INSERT INTO Cliente (numeroIdentificacion, estado, fechaInicio) VALUES('" + tCIF + "', 'Activo', '" + formatter.format(today) + "');");
            int clt = (int) miBD.SelectEscalar("SELECT MAX(id) FROM Cliente");
            miBD.Modify("INSERT INTO Empresa (cliente, nombre) VALUES(" + clt + ", '" + tNombre + "');");
            miBD.Modify("INSERT INTO Direccion (calle, numero, plantaPuertaOficina, ciudad, region, codigoPostal, pais, valida, cliente)" + " VALUES('" + tCalle + "', " + tNumero + ", '" + tPlantaPuertaOficina + "', '" + tCiudad + "', '" + tRegion + "', " + tCP + ", '" + tPais + "', " + (validaDireccionActual ? 1 : 0) + ", " + clt + ");");

            formulario.dispose();

            GestionRelEmpresa relEmpresa = new GestionRelEmpresa("Ebury");
            relEmpresa.pack();
            relEmpresa.setLocationRelativeTo(null);
            relEmpresa.setVisible(true);
        } catch (SQLException ex) {
            ErrorBDRegistroDialog dialog = new ErrorBDRegistroDialog();
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        }
    }

    private boolean algunoNulo(String... args) {
        for (String arg : args) {
            if (arg.equals("")) {
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
        return cif.length() == 9 && letraCifCorrecta(cif) && esInteger(cif.substring(1, 8)) && ultimoCaracterValido(cif);
    }

    private boolean ultimoCaracterValido(String cif) {
        char primerCaracter = Character.toUpperCase(cif.charAt(0));
        char ultimoCaracter = cif.charAt(8);
        return Character.isDigit(ultimoCaracter) || ((primerCaracter == 'P' || primerCaracter == 'X') && Character.isLetter(ultimoCaracter));
    }

    private boolean letraCifCorrecta(String cif) {
        String letrasCorrectas = "ABCDEFGHPQSKLMX";
        String letra = String.valueOf(Character.toUpperCase(cif.charAt(0)));
        return letrasCorrectas.contains(letra);
    }

    public void onCompletarRegistroPersona(RegistroPersona formulario) {
        String nif = formulario.getNif().getText();
        String nombre = formulario.gettPrimerNombre().getText();
        String primerApellido = formulario.gettPrimerApellido().getText();
        String segundoApellido = formulario.gettSegundoApellido().getText();
        String segundoNombre = formulario.gettSegundoNombre().getText();
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

        //Contraseñas no iguales
        if (algunoNulo(nif, nombre, primerApellido, tCalle, tPlantaPuertaOficina, tCiudad, tPais, tNumero, tCP, passwordField1, passwordField2) || !passwordField1.equals(passwordField2) || !soloLetras(nombre, segundoNombre, primerApellido, segundoApellido, tCiudad, tPais, tRegion) || !esInteger(tNumero) || tCP.length() != 5 || !esInteger(tCP) || !nifCorrecto(nif) || !calleCorrecta(tCalle) || fechaNacimiento == null) {
            ErrorDatosRegistroDialog dialog = new ErrorDatosRegistroDialog();
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        } else {
            //insertar cliente en BD
            try {
                BD miBD = new BD();

                Date today = new Date();

                miBD.Modify("INSERT INTO Cliente (numeroIdentificacion,estado,fechaInicio)" + " VALUES('" + nif + "', 'Activo', '" + formatter.format(today) + "');");
                miBD.Modify("INSERT INTO Persona (nombre,segundoNombre,apellido,segundoApellido,fechaNacimiento)" + " VALUES('" + nombre + "', '" + segundoNombre + "', '" + primerApellido + "', '" + segundoApellido + "', '" + formatter.format(fechaNacimiento) + "');");
                int clt = (int) miBD.SelectEscalar("SELECT MAX(id) FROM Cliente");
                int pers = (int) miBD.SelectEscalar("SELECT MAX(id) FROM Persona");
                miBD.Modify("INSERT INTO Individual (cliente, persona) VALUES(" + clt + ", " + pers + ");");
                miBD.Modify("INSERT INTO Direccion (calle, numero, plantaPuertaOficina, ciudad, region, codigoPostal, pais, valida, cliente)" + " VALUES('" + tCalle + "', " + tNumero + ", '" + tPlantaPuertaOficina + "', '" + tCiudad + "', '" + tRegion + "', " + tCP + ", '" + tPais + "', " + (validaDireccionActual ? 1 : 0) + ", " + clt + ");");

                ExitoRegistroDialog dialog = new ExitoRegistroDialog(formulario);
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);

            } catch (SQLException ex) {
                ex.printStackTrace();
                ErrorBDRegistroDialog dialog = new ErrorBDRegistroDialog();
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }

        }
    }

    private boolean calleCorrecta(String calle) {
        boolean res = true;
        int i = 0;
        while (res && i < calle.length()) {
            char caracter = calle.charAt(i);
            res = Character.isLetter(caracter) || Character.isWhitespace(caracter);
            i++;
        }
        return res;
    }

    private boolean nifCorrecto(String nif) {
        return nif.length() == 9 && esInteger(nif.substring(0, 8)) && letraNifCorrecta(nif);
    }

    private boolean letraNifCorrecta(String nif) {
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        int numero = Integer.parseInt(nif.substring(0, 8));
        return letras.charAt(numero % 23) == Character.toUpperCase(nif.charAt(8));
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

    public void onBorrarPersonaRelacionada(GestionRelEmpresa frame) {
    }

    public void onAñadirPersonaRelacionada(GestionRelEmpresa frame) {
        String nif = frame.getNif().getText();
        String nombre = frame.gettPrimerNombre().getText();
        String primerApellido = frame.gettPrimerApellido().getText();
        String segundoApellido = frame.gettSegundoApellido().getText();
        String segundoNombre = frame.gettSegundoNombre().getText();
        JDateChooser datechooser = frame.getJDateChooser1();
        String tCalle = frame.gettCalle().getText();
        String tPlantaPuertaOficina = frame.gettPlantaPuertaOficina().getText();
        String tCiudad = frame.gettCiudad().getText();
        String tPais = frame.gettPais().getText();
        String tNumero = frame.gettNumero().getText();
        String tRegion = frame.gettRegion().getText();
        String tCP = frame.getCP().getText();

        boolean validaDireccionActual = frame.getValidaDireccionActualCheckBox().isSelected();

        String passwordField1 = new String(frame.getPasswordField1().getPassword());
        String passwordField2 = new String(frame.getPasswordField2().getPassword());

        Date fechaNacimiento = datechooser.getDate();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");


        if (algunoNulo(nif, nombre, primerApellido, tCalle, tPlantaPuertaOficina, tCiudad, tPais, tNumero, tCP, passwordField1, passwordField2) || !passwordField1.equals(passwordField2) || !soloLetras(nombre, segundoNombre, primerApellido, segundoApellido, tCiudad, tPais, tRegion) || !esInteger(tNumero) || tCP.length() != 5 || !esInteger(tCP) || !nifCorrecto(nif) || !calleCorrecta(tCalle) || fechaNacimiento == null) {
            ErrorDatosRegistroDialog dialog = new ErrorDatosRegistroDialog();
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        } else {
            try {
                BD miBD = new BD();

                Date today = new Date();

                Object[] query = miBD.Select("SELECT * FROM Cliente WHERE nif = '" + nif + "', nombre = '" + nombre + "', apellido = '" + primerApellido + "';").get(0);

                if (query != null) {
                    //Se encuentra en la bd
                    //Aqui hay que añadir en personasrelacionadas la persona y relacionarla con la empresa

                } else {
                    //No se encuentra en la bd
                    miBD.Modify("INSERT INTO Cliente (numeroIdentificacion,estado,fechaInicio)" + " VALUES('" + nif + "', 'Activo', '" + formatter.format(today) + "');");
                    miBD.Modify("INSERT INTO Persona (nombre,segundoNombre,apellido,segundoApellido,fechaNacimiento)" + " VALUES('" + nombre + "', '" + segundoNombre + "', '" + primerApellido + "', '" + segundoApellido + "', '" + formatter.format(fechaNacimiento) + "');");
                    int clt = (int) miBD.SelectEscalar("SELECT MAX(id) FROM Cliente;");
                    int pers = (int) miBD.SelectEscalar("SELECT MAX(id) FROM Persona;");
                    miBD.Modify("INSERT INTO Individual (cliente, persona) VALUES(" + clt + ", " + pers + ");");
                    miBD.Modify("INSERT INTO Direccion (calle, numero, plantaPuertaOficina, ciudad, region, codigoPostal, pais, valida, cliente)" + " VALUES('" + tCalle + "', " + tNumero + ", '" + tPlantaPuertaOficina + "', '" + tCiudad + "', '" + tRegion + "', " + tCP + ", '" + tPais + "', " + (validaDireccionActual ? 1 : 0) + ", " + clt + ");");
                    //Falta hacer que sean relacionados con la empresa (añadir a tabla PersonasRelacionadas)
                }
            } catch (SQLException e) {
                e.printStackTrace();
                ErrorBDRegistroDialog dialog = new ErrorBDRegistroDialog();
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        }
    }
}
