package View.Holanda;

import javax.swing.*;

public class PrincipalHolanda {

    private JPanel panel1;
    private JPanel botones;
    private JButton filtrarPorClienteButton;
    private JButton filtrarPorCuentaBancariaButton;
    private JButton comprobarConexionButton;
    private JPanel imagen;
    private JLabel imagenAFM;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ebury");
        frame.setContentPane(new PrincipalHolanda().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(new ImageIcon("Recursos/Ebury.png").getImage());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
