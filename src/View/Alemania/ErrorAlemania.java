package View.Alemania;

import javax.swing.*;
import java.awt.event.*;

public class ErrorAlemania extends JDialog {
    private JPanel contentPane;
    private JButton buttonCancel;
    private JLabel mensajeError;

    public ErrorAlemania() {
        setContentPane(contentPane);
        setModal(true);
        setIconImage(new ImageIcon("resources/Ebury.png").getImage());

        buttonCancel.addActionListener(e -> onVolver());

        // call onVolver() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onVolver();
            }
        });

        // call onVolver() on ESCAPE
        contentPane.registerKeyboardAction(e -> onVolver(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onVolver() {
        // add your code here if necessary
        dispose();
    }
}
