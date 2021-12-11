package Controller;

import View.Holanda.FiltrarPorCliente;

import javax.swing.*;

public class ControllerHolanda {
    public void onFiltrarPorCliente(JFrame frame) {
        frame.setContentPane(new FiltrarPorCliente());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void onFiltrarPorCuenta() {
    }

    public void onComprobarConexion() {
    }
}
