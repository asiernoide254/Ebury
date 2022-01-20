import View.Cliente.PrincipalCliente;

import javax.swing.*;

public class mainCliente {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PrincipalCliente frame = new PrincipalCliente("Ebury");
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
