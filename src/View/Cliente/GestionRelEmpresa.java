package View.Cliente;

import Controller.ControllerCliente;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GestionRelEmpresa extends JFrame {
    private JPanel panel1;
    private JLabel DatosP;
    private JTextField tNIF;
    private JTextField tNombre;

    private JTextField tApellido;
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
    private JCheckBox tValida;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JButton anyadirButton;
    private JButton cancelarButton;
    private JComboBox comboBox1;
    private JTable table1;
    private JButton borrarButton;
    private JButton finalizarButton;

    private void createUIComponents() {
        JDateChooser1 = new JDateChooser();
        JDateChooser1.setDateFormatString("dd/MM/yyyy");
    }

    public GestionRelEmpresa(String title, int idEmpresa) {
        super(title);
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("resources/Ebury.png").getImage());

        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.setColumnCount(3);
        model.addRow(new String[]{"Nombre", "NIF", "Tipo"});
        model.fireTableRowsInserted(0, 0);

        ControllerCliente controlador = new ControllerCliente();
        //A la hora de borrar hay que actualizar el grid (OJO)
        anyadirButton.addActionListener(e -> controlador.onAnyadirPersonaRelacionada(idEmpresa, this));
        cancelarButton.addActionListener(e -> controlador.onLimpiarFormulario(this));
        borrarButton.addActionListener(e -> controlador.onBorrarPersonaRelacionada(table1));
        finalizarButton.addActionListener(e -> controlador.onFinalizarRegistroEmpresa(this));
    }

    public JTextField getNif() {
        return tNIF;
    }

    public JTextField gettPrimerApellido() {
        return tApellido;
    }

    public JTextField gettSegundoNombre() {
        return tSegundoNombre;
    }

    public JTextField gettPrimerNombre() {
        return tNombre;
    }

    public JTextField gettSegundoApellido() {
        return tSegundoApellido;
    }

    public JDateChooser getJDateChooser1() {
        return JDateChooser1;
    }

    public JComboBox getComboBox1() {
        return comboBox1;
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

    public JTextField getCP() {
        return tCP;
    }

    public JCheckBox getValidaDireccionActualCheckBox() {
        return tValida;
    }

    public JPasswordField getPasswordField1() {
        return passwordField1;
    }

    public JPasswordField getPasswordField2() {
        return passwordField2;
    }

    public JTable getTable1() {
        return table1;
    }
}
