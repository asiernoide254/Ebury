package View.Cliente;

import Controller.ControllerCliente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ErrorBDRegistroDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;

    private JFrame frame;

    public ErrorBDRegistroDialog(JFrame frame) {
        this.frame = frame;

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> onOK());
    }

    private void onOK() {
        if(frame != null) {
            new ControllerCliente().onCargarPrincipalCliente(frame);
        }
        dispose();
    }
}
