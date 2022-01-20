package View.Cliente;

import Controller.ControllerCliente;

import javax.swing.*;

public class RegistroEmpresa extends JFrame {
    private JPanel panel1;
    private JLabel DatosP;
    private JTextField tCIF;
    private JTextField tNombre;
    private JTextField tCalle;
    private JTextField tPlantaPuertaOficina;
    private JTextField tCiudad;
    private JTextField tPais;
    private JTextField tNumero;
    private JTextField tRegion;
    private JTextField tCP;
    private JCheckBox validaDireccionActualCheckBox;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JButton registrarseButton;
    private JButton cancelarButton;
    private JPanel panelBotones;
    private JPanel panelInterior;

    public RegistroEmpresa(String title) {
        super(title);
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("resources/Ebury.png").getImage());

        ControllerCliente controlador = new ControllerCliente();
        registrarseButton.addActionListener(e -> controlador.onCompletarRegistroEmpresa(this));
        cancelarButton.addActionListener(e -> controlador.onCargarRegistroPrincipal(this));
    }

    public JTextField gettCIF() {
        return tCIF;
    }

    public JTextField gettNombre() {
        return tNombre;
    }

    public JTextField gettCalle() {
        return tCalle;
    }

    public JTextField gettPlantaPuertaOficina() {
        return tPlantaPuertaOficina;
    }

    public JTextField gettCiudad() {
        return tCiudad;
    }

    public JTextField gettPais() {
        return tPais;
    }

    public JTextField gettNumero() {
        return tNumero;
    }

    public JTextField gettRegion() {
        return tRegion;
    }

    public JTextField gettCP() {
        return tCP;
    }

    public JCheckBox getValidaDireccionActualCheckBox() {
        return validaDireccionActualCheckBox;
    }

    public JPasswordField getPasswordField1() {
        return passwordField1;
    }

    public JPasswordField getPasswordField2() {
        return passwordField2;
    }
}
