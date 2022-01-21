package View.Cliente;

import Controller.ControllerCliente;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.net.PasswordAuthentication;

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
    public JTextField getNif() {
        return tNIF;
    }

    public JTextField gettPrimerApellido(){return tPrimerApellido;}
    public JTextField gettSegundoNombre() {return tSegundoNombre; }
    public JTextField gettPrimerNombre(){return tPrimerNombre;}
    public JTextField gettSegundoApellido(){return tSegundoApellido;}

    public JDateChooser getJDateChooser1 (){return JDateChooser1;}
    public JTextField gettCalle (){return tCalle;}
    public JTextField gettPlantaPuertaOficina (){return tPlantaPuertaOficina;}
    public JTextField gettCiudad (){return tCiudad;}
    public JTextField gettPais (){return tPais;}
    public JTextField gettNumero(){return tNumero;}
    public JTextField gettRegion(){return tRegion;}
    public JTextField getCP() {return tCP;}
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
