package View.Holanda;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exito extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;

    public Exito() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    public static void main(String[] args) {
        Exito dialog = new Exito();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
