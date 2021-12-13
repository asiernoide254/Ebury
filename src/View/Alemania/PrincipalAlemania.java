package View.Alemania;

import Model.BD;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

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
        setIconImage(new ImageIcon("resources/Ebury.png").getImage());

        informeInicialButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onInicial();
            }
        });

        informeSemanalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onSemanal();
            }
        });

    }

    private void onInicial() {
        BD b = null;
        try {
            b = new BD();
            //implementacion boton
            b.Modify("sentencia SQL");
        } catch (SQLException e) {
            ErrorAlemania dialog = new ErrorAlemania();
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
            //throw new RuntimeException("Error relacionado con la base de datos");
        }
    }

    private void onSemanal() {
        BD b = null;
        try {
            b = new BD();
            //implementacion boton
            b.Modify("sentencia SQL");
        } catch (SQLException e) {
            ErrorAlemania dialog = new ErrorAlemania();
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
            //throw new RuntimeException("Error relacionado con la base de datos");
        }
    }

    public static void main(String[] args) {
        PrincipalAlemania frame = new PrincipalAlemania("Ebury");

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
