package Controller;

import View.Cliente.RegistroEmpresa;
import View.Cliente.RegistroPersona;
import View.Cliente.RegistroPrincipal;

import javax.swing.*;

public class ControllerCliente {

    public void onRegistrarseEmpresa(JFrame frame) {
        frame.dispose();

        RegistroEmpresa rp = new RegistroEmpresa("Ebury");
        rp.pack();
        rp.setLocationRelativeTo(null);
        rp.setVisible(true);
    }

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
    }

    public void onCompletarRegistroPersona(JFrame frame) {
    }

    public void onCargarRegistroPrincipal(JFrame frame) {
        frame.dispose();

        RegistroPrincipal rp = new RegistroPrincipal("Ebury");
        rp.pack();
        rp.setLocationRelativeTo(null);
        rp.setVisible(true);
    }
}
