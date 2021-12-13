package Controller;

import Model.BD;
import View.Holanda.ErrorHolanda;
import View.Holanda.ExitoHolanda;
import View.Holanda.FiltrarPorCliente;

import javax.swing.*;
import java.sql.SQLException;

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
}
