package Controller;

import Model.BD;
import View.Cliente.*;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        if (!notNull(tCIF, tNombre, tCalle, tPlantaPuertaOficina, tCiudad, tPais, tNumero, tRegion, tCP, passwordField1, passwordField2) || !passwordField1.equals(passwordField2)
        || !checkearString(tNombre, tCalle, tCiudad, tPais, tRegion) || !checkearNumeros(tNumero) || !(tCP.length() == 5 && checkearNumeros(tCP))) {
            ErrorDatosRegistroDialog dialog = new ErrorDatosRegistroDialog();
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        }

        try {
            BD miBD = new BD();
            miBD.Modify("INSERT INTO Empresa (nombre) VALUES();");
        } catch (SQLException ex) {
            ErrorBDRegistroDialog dialog = new ErrorBDRegistroDialog();
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        }



    }

    private boolean notNull(String... args) {
        for (String arg : args) {
            if (arg.equals("")) {
                return false;
            }
        }
        return true;
    }

    private boolean checkearNumeros(String str) {
        try {
            Integer.parseInt(str);
            return true;
        }catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean checkearString(String... str) {

        /**Querido Carlos, si tienes tiempo libre y quieres matar el tiempo, te propongo cambiar esto por un
         while para que cuando haya un valor se detenga el metodo, gracias por todo.**/
        boolean res = true;
        int i = 0;
        while(res && i < str.length){
            String aux = str[i];
            int j = 0;
            while (res && j < aux.length()){
                res = Character.isLetter(aux.charAt(j));
                j++;
            }
            i++;
        }
        return res;
    }

    private boolean CIFcorrecto(String cif) {
        Boolean res = true;
        char letra = cif.charAt(0);

        if(cif.length() != 9 || (letra < 65 || letra > 90)) {
            res = false;
        }

        int i = 1;
        while (i < 9 && res) {
            char c = cif.charAt(i);

            if (c < 48 || c > 57) {
                res = false;
            }
            i++;
        }

        return res;
    }

    public void onCompletarRegistroPersona(RegistroPersona formulario) {
        String nif = formulario.getNif().getText();
        String nombre = formulario.gettPrimerNombre().getText();
        String primerApellido = formulario.gettPrimerApellido().getText();
        String segundoApellido  = formulario.gettSegundoApellido().getText();
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
        //falta por poner que es obligatoria la fecha de nacimiento
        if (!notNull(nif, nombre, primerApellido, fechaNacimiento.toString(), tCalle, tPlantaPuertaOficina, tCiudad, tPais, tNumero, tCP, passwordField1, passwordField2)
                || !passwordField1.equals(passwordField2) || !checkearString(nombre, segundoNombre, primerApellido, segundoApellido, tCalle, tCiudad, tPais, tRegion)
                || !checkearNumeros(tNumero) || !(tCP.length() == 5 && checkearNumeros(tCP) || !nifCorrecto(nif) )){
            ErrorDatosRegistroDialog dialog = new ErrorDatosRegistroDialog();
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        }else{
            //insertar cliente en BD
            try {
                BD miBD = new BD();

                Date today = new Date();

                miBD.Modify("INSERT INTO Cliente (numeroIdentificacion,estado,fechaInicio)" +
                        " VALUES('" + nif + "', 'Activo', '" + formatter.format(today) + "');");
                miBD.Modify("INSERT INTO Persona (nombre,segundoNombre,apellido,segundoApellido,fechaNacimiento)" +
                        " VALUES('" + nombre + "', '" + segundoNombre + "', '" + primerApellido
                + "', '" + segundoApellido + "', '" + formatter.format(fechaNacimiento) + "');");
                int clt = (int)miBD.SelectEscalar("SELECT MAX(id) FROM Cliente");
                int pers = (int)miBD.SelectEscalar("SELECT MAX(id) FROM Persona");
                miBD.Modify("INSERT INTO Individual (cliente, persona) VALUES(" + clt + ", " + pers + ");");
                miBD.Modify("INSERT INTO Direccion (calle, numero, plantaPuertaOficina, ciudad, region, codigoPostal, pais, valida, cliente)" +
                        " VALUES('" + tCalle + "', " + tNumero + ", '" + tPlantaPuertaOficina + "', '" + tCiudad + "', '" + tRegion
                                + "', " + tCP + ", '" + tPais + "', " + (validaDireccionActual?1:0) + ", " + clt + ");");

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

    private Boolean nifCorrecto (String nif){
        boolean res = false;
        if (nif.length() !=9){
            return res;
        }
        int i = 0;
        while ( nif.charAt(i) >= 47 && nif.charAt(i) <= 57  && i < 8){
            i++;
        }

        if (i < 8){
            return res;
        }

        if (nif.charAt(8) < 65  || nif.charAt(8) > 90){
            return res;
        }
        res = true;
        return res;

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
}
