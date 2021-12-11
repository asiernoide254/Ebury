package Controller;

import View.Holanda.FiltrarPorCliente;

import javax.swing.*;

public class ControllerHolanda {
    public void onFiltrarPorCliente(JFrame frame) {
        frame.setContentPane(new FiltrarPorCliente(frame));
    }

    public void onFiltrarPorCuenta() {
    }

    public void onComprobarConexion() {
    }
}
