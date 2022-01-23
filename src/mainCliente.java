import View.Cliente.PrincipalCliente;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class mainCliente {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new NimbusLookAndFeel());
            } catch (UnsupportedLookAndFeelException e) {
            }
            PrincipalCliente frame = new PrincipalCliente("Ebury");
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
