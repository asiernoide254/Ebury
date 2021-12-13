package Controller;

import Model.BD;
import View.Holanda.*;
import javax.swing.*;
import java.awt.*;
import java.security.Principal;
import java.sql.SQLException;

public class ControllerHolanda {
    public void onFiltrarPorCliente(JFrame frame) {
        FiltrarPorCliente fpc = new FiltrarPorCliente(frame);
        frame.setContentPane(fpc.getPanel1());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void onFiltrarPorCuenta(JFrame frame) {
        FiltrarPorCuentaBancaria fpcb = new FiltrarPorCuentaBancaria(frame);
        frame.setContentPane(fpcb.getPanel1());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void onComprobarConexion() {
        try {
            new BD();
            ExitoHolanda dialog = new ExitoHolanda();
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        } catch (SQLException e) {
            ErrorHolanda dialog = new ErrorHolanda();
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        }
    }

    public void onVolver(JFrame frame) {
        // add your code here
        frame.dispose();

        PrincipalHolanda ph = new PrincipalHolanda("Ebury");
        ph.pack();
        ph.setLocationRelativeTo(null);
        ph.setVisible(true);

    }

    public void onBuscarCuentaBancaria(String str, String text) {
        switch (str){
            case "Todas":
                break;

            case "Cuentas activas":
                break;

            default:
        }
    }

    public void onBuscarCliente(String str, String text) {

        switch(str){
            case "Nombre": //lo que haya que hacer
                break;

            case "Apellidos":
                break;

            case "Fecha de inicio":
                break;

            case "Fecha de cierre":
                break;

            default:
        }

    }
}
