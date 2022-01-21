package Controller;

import Model.BD;
import View.Cliente.*;

import javax.swing.*;
import java.sql.SQLException;

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
            if (arg == null) {
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
        Boolean res = true;
        for(String s : str) {
            int len = s.length();

            int i = 0;
            while (i < len && ((i >= 65 && i <= 90) || (i >= 97 && i <= 122))) {
                i++;
            }

            if (i < len) {
                res = false;
            }
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
        while (i < 9 && res == true) {
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

        //Contraseñas no iguales
        if (!notNull(nif, nombre, primerApellido, segundoApellido, segundoNombre, tCalle, tPlantaPuertaOficina, tCiudad, tPais, tNumero, tRegion, tCP, passwordField1, passwordField2)
                || !passwordField1.equals(passwordField2) || nifCorrecto(nif)  ){
            ErrorDatosRegistroDialog dialog = new ErrorDatosRegistroDialog();
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        }
            //insertar cliente en BD
        try {
            BD miBD = new BD();
            miBD.Modify("INSERT INTO Invidual VALUES();");
        } catch (SQLException ex) {
            ErrorBDRegistroDialog dialog = new ErrorBDRegistroDialog();
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        }

            ExitoRegistroDialog dialog = new ExitoRegistroDialog();
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);




    }
    private Boolean nifCorrecto (String nif){
        Boolean res = true;
        if (nif.length() !=9 ){
            res = false;
        }
        int i =0;
        while ( nif.charAt(i) >= 47 && nif.charAt(i) <= 57  && i < 8){
            i++;
        }

        if (i < 8){
            res = false;
        }
        if (nif.charAt(8) < 65  || nif.charAt(8) > 90){
            res = false;
        }
        return res;

    }

    public void onCargarRegistroPrincipal(JFrame frame) {
        frame.dispose();

        RegistroPrincipal rp = new RegistroPrincipal("Ebury");
        rp.pack();
        rp.setLocationRelativeTo(null);
        rp.setVisible(true);
    }
}
