package View.Alemania;

import javax.swing.*;

public class PrincipalAlemania {

    private JPanel panel1;
    private JButton informeInicialButton;
    private JButton informeSemanalButton;
    private JLabel Imagen;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ebury");
        frame.setContentPane(new PrincipalAlemania().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setIconImage(new ImageIcon("Recursos/Ebury.png").getImage());
        //sus

        frame.pack();
        frame.setVisible(true);
    }
}
