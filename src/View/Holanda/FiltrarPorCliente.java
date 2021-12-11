package View.Holanda;

import javax.swing.*;

public class FiltrarPorCliente extends JPanel {

    private JPanel panel1;
    private JTextField TextField;
    private JScrollPane resultados;
    private JComboBox comboBox1;

    public FiltrarPorCliente(JFrame frame) {
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(new ImageIcon("Recursos/Ebury.png").getImage());
        frame.pack();
        frame.setLocationRelativeTo(null);
        setVisible(true);
    }

}
