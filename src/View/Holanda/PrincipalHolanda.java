package View.Holanda;

import Controller.ControllerHolanda;

import javax.swing.*;

public class PrincipalHolanda extends JFrame {

    private JPanel panel1;
    private JPanel botones;
    private JButton filtrarPorClienteButton;
    private JButton filtrarPorCuentaBancariaButton;
    private JButton comprobarConexionButton;
    private JPanel imagen;
    private JLabel imagenAFM;

    public PrincipalHolanda(String title) {
        super(title);
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("Recursos/Ebury.png").getImage());

        ControllerHolanda controlador = new ControllerHolanda();
        filtrarPorClienteButton.addActionListener(e -> controlador.onFiltrarPorCliente(this));
        filtrarPorCuentaBancariaButton.addActionListener(e -> controlador.onFiltrarPorCuenta());
        comprobarConexionButton.addActionListener(e -> controlador.onComprobarConexion());
    }
}
