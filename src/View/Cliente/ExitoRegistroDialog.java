package View.Cliente;

import Controller.ControllerCliente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitoRegistroDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JFrame frame;

    public ExitoRegistroDialog(JFrame fr) {
        setTitle("Ebury");
        setContentPane(contentPane);
        setIconImage(new ImageIcon("resources/Ebury.png").getImage());
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        frame = fr;

        buttonOK.addActionListener(e -> onOK());
    }

    private void onOK() {
        dispose();
        ControllerCliente cl = new ControllerCliente();
        cl.onCargarPrincipalCliente(frame);
    }
}
