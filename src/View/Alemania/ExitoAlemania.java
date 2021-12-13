package View.Alemania;

import javax.swing.*;
import java.awt.event.*;

public class ExitoAlemania extends JDialog {
    private JPanel contentPane;
    private JButton buttonCancel;

    public ExitoAlemania() {
        setContentPane(contentPane);
        setModal(true);
        setIconImage(new ImageIcon("resources/Ebury.png").getImage());

        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
