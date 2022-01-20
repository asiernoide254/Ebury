package Controller;

import View.Cliente.RegistroPrincipal;

import javax.swing.*;

public class ControllerCliente {
    public void onRegistrarse() {

    }

    public void onRegistrarseEmpresa() {
    }

    public void onRegistrarsePersona() {
    }

    public void onCompletarRegistroPersona() {
    }

    public void onCancelarRegistro(JFrame frame) {
        frame.dispose();

        RegistroPrincipal rp = new RegistroPrincipal("Ebury");
        rp.pack();
        rp.setLocationRelativeTo(null);
        rp.setVisible(true);
    }
}
