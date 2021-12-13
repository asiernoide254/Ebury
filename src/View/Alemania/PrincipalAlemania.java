package View.Alemania;

import Controller.ControllerAlemania;
import Controller.ControllerHolanda;
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

        ControllerAlemania controlador = new ControllerAlemania();
        informeInicialButton.addActionListener(e -> controlador.onInicial());
        informeSemanalButton.addActionListener(e -> controlador.onSemanal());
    }



}
