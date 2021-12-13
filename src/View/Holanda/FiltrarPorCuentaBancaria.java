package View.Holanda;

import Controller.ControllerHolanda;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FiltrarPorCuentaBancaria {
    private JTextField TextField;
    private JComboBox comboBox1;
    private JPanel panel1;
    private JButton volverButton;

    public JPanel getPanel1() {
        return panel1;
    }

    public FiltrarPorCuentaBancaria(JFrame frame) {
        ControllerHolanda controlador = new ControllerHolanda();
        volverButton.addActionListener((ActionListener) e -> controlador.onVolver(frame));
    }

}
