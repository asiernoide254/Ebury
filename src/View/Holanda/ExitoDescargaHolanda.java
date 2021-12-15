package View.Holanda;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitoDescargaHolanda extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;

    public ExitoDescargaHolanda() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> onOK());
    }

    private void onOK() {
        // add your code here
        dispose();
    }
}
