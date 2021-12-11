package View.Alemania;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrincipalAlemania extends JFrame{

    private JPanel panel1;
    private JButton informeInicialButton;
    private JButton informeSemanalButton;
    private JLabel Imagen;

    /**
    //no hereda de JFrame
    public static void main(String[] args) {
        JFrame frame = new JFrame("Ebury");
        frame.setContentPane(new PrincipalAlemania().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(new ImageIcon("Recursos/Ebury.png").getImage());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    */

    public PrincipalAlemania(String title){
        super(title);
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("Recursos/Ebury.png").getImage());

        informeInicialButton.addActionListener(e -> onInicial());

        informeSemanalButton.addActionListener(e -> onSemanal());
    }

    private void onInicial() {
        // add your code here
        Error dialog = new Error();
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    private void onSemanal() {
        // add your code here if necessary
        dispose();
    }

}
