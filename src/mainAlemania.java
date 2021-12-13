import View.Alemania.PrincipalAlemania;

import javax.swing.*;

public class mainAlemania {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PrincipalAlemania frame = new PrincipalAlemania("Ebury");
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
