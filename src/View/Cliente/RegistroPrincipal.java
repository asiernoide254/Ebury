package View.Cliente;

import Controller.ControllerCliente;

import javax.swing.*;

public class RegistroPrincipal extends JFrame {
    private JPanel panel1;
    private JButton personaFísicaButton;
    private JButton empresaButton;
    private JTextArea seleccioneSiQuiereCrearTextArea;
    private JPanel panelTexto;

    public RegistroPrincipal(String title) {
        super(title);
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("resources/Ebury.png").getImage());

        ControllerCliente controlador = new ControllerCliente();
        empresaButton.addActionListener(e -> controlador.onRegistrarseEmpresa(this));
        personaFísicaButton.addActionListener(e -> controlador.onRegistrarsePersona(this));
    }
}
