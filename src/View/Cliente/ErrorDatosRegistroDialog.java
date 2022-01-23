package View.Cliente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ErrorDatosRegistroDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel textoError;

    public ErrorDatosRegistroDialog() {
        setTitle("Ebury");
        setContentPane(contentPane);
        setIconImage(new ImageIcon("resources/Ebury.png").getImage());
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        textoError.setText("<html><center>No se ha podido crear la cuenta porque hay datos obligatorios<br>que no han sido rellenados o datos con formato incorrecto.</center></html>");
        buttonOK.addActionListener(e -> onOK());
    }

    private void onOK() {
        dispose();
    }
}
