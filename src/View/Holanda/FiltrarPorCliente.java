package View.Holanda;

import javax.swing.*;

public class FiltrarPorCliente {

    private JPanel panel1;
    private JTextField TextField;
    private JScrollPane resultados;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Filtrar por cliente");
        frame.setContentPane(new FiltrarPorCliente().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(new ImageIcon("Recursos/Ebury.png").getImage());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
