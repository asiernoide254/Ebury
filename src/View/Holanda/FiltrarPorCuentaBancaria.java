package View.Holanda;

import Controller.ControllerHolanda;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class FiltrarPorCuentaBancaria {
    private JTextField TextField;
    private JPanel panel1;
    private JButton volverButton;
    private JComboBox comboBox2;
    private JButton buscarButton;
    private JButton descargarResultados;
    private JScrollPane panelRes;
    private JTable table1;

    public JPanel getPanel1() {
        return panel1;
    }

    public FiltrarPorCuentaBancaria(JFrame frame) {
        ControllerHolanda controlador = new ControllerHolanda();
        volverButton.addActionListener(e -> controlador.onVolver(frame));
        buscarButton.addActionListener(e -> controlador.onBuscarCuentaBancaria(comboBox2.getSelectedItem().toString(), TextField.getText(), table1));
        panel1.registerKeyboardAction(e -> controlador.onBuscarCuentaBancaria(comboBox2.getSelectedItem().toString(), TextField.getText(), table1), KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        descargarResultados.addActionListener(e -> controlador.onDescargarResultadosCuentaBancaria(table1));
    }
}
