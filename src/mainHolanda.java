import View.Holanda.PrincipalHolanda;

import javax.swing.*;

public class mainHolanda {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PrincipalHolanda frame = new PrincipalHolanda("Ebury");
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}