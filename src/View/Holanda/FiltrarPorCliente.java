package View.Holanda;

import Controller.ControllerHolanda;

import javax.swing.*;
import java.awt.event.ActionListener;

public class FiltrarPorCliente {

    private JPanel panel1;
    private JTextField TextField;
    private JScrollPane resultados;
    private JComboBox comboBox1;
    private JButton volverButton;
    private JButton buscarButton;

    public JPanel getPanel1() {
        return panel1;
    }

    public FiltrarPorCliente(JFrame frame) {
        ControllerHolanda controlador = new ControllerHolanda();
        volverButton.addActionListener((ActionListener) e -> controlador.onVolver(frame));
    }
}
