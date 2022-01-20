package View.Cliente;

import Controller.ControllerCliente;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;

public class RegistroPersona extends JFrame {
    private JPanel panel1;
    private JLabel DatosP;
    private JTextField tNIF;
    private JTextField tPrimerNombre;
    private JTextField tPrimerApellido;
    private JTextField tSegundoNombre;
    private JTextField tSegundoApellido;
    private JPanel panelCal;
    private JDateChooser JDateChooser1;
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

    public RegistroPersona(String title) {
        super(title);
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("resources/Ebury.png").getImage());

        ControllerCliente controlador = new ControllerCliente();
        registrarseButton.addActionListener(e -> controlador.onCompletarRegistroPersona(this));
        cancelarButton.addActionListener(e -> controlador.onCargarRegistroPrincipal(this));
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
        JDateChooser1 = new JDateChooser();
    }
}
