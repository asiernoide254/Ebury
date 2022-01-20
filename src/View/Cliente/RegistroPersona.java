package View.Cliente;

import Controller.ControllerCliente;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;

public class RegistroPersona extends JFrame {
    private JPanel panel1;
    private JLabel DatosP;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JPanel panelCal;
    private JDateChooser JDateChooser1;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private JTextField textField11;
    private JTextField textField12;
    private JCheckBox válidaDirecciónActualCheckBox;
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
        registrarseButton.addActionListener(e -> controlador.onCompletarRegistroPersona());
        cancelarButton.addActionListener(e -> controlador.onCancelarRegistro(this));

    }


}
