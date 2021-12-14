package View.Holanda;

import Controller.ControllerHolanda;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class FiltrarPorCliente {

    private JPanel panel1;
    private JTextField TextField;
    private JScrollPane resultados;
    private JComboBox comboBox1;
    private JButton volverButton;
    private JButton buscarButton;
    private JTable table1;

    public JPanel getPanel1() {
        return panel1;
    }

    public FiltrarPorCliente(JFrame frame) {
        ControllerHolanda controlador = new ControllerHolanda();
        volverButton.addActionListener(e -> controlador.onVolver(frame));
        buscarButton.addActionListener(e -> controlador.onBuscarCliente(comboBox1.getSelectedItem().toString(), TextField.getText(), table1));
        panel1.registerKeyboardAction(e -> controlador.onBuscarCliente(comboBox1.getSelectedItem().toString(), TextField.getText(), table1), KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }
}
