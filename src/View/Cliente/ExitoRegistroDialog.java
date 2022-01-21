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
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        frame = fr;

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
        ControllerCliente cl = new ControllerCliente();
        cl.onCargarPrincipalCliente(frame);
    }
}
