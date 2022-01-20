package View.Cliente;

import Controller.ControllerCliente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrincipalCliente extends JFrame {
    private JPanel panel1;
    private JButton Registrarse;

    public PrincipalCliente(String title) {
        super(title);
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("resources/Ebury.png").getImage());

        ControllerCliente controlador = new ControllerCliente();
        Registrarse.addActionListener(e -> controlador.onRegistrarse());
    }
}
