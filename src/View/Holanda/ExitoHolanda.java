package View.Holanda;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitoHolanda extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;

    public ExitoHolanda() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setTitle("Exito");
        setIconImage(new ImageIcon("resources/Ebury.png").getImage());
        setResizable(false);

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
}
