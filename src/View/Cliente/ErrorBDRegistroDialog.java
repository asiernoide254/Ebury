package View.Cliente;

import Controller.ControllerCliente;

import javax.swing.*;

public class ErrorBDRegistroDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel textoError;

    private JFrame frame;

    public ErrorBDRegistroDialog(JFrame frame) {
        setTitle("Ebury");
        setContentPane(contentPane);
        setIconImage(new ImageIcon("resources/Ebury.png").getImage());
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        this.frame = frame;

        textoError.setText("<html><center>No se ha podido crear la cuenta por problemas<br>de acceso a la base de datos.</center></html>");

        buttonOK.addActionListener(e -> onOK());
    }

    private void onOK() {
        if (frame != null) {
            new ControllerCliente().onCargarPrincipalCliente(frame);
        }
        dispose();
    }
}
