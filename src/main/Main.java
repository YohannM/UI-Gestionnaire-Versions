
package main;

import exception.CloneFailedException;
import exception.InitFailedException;
import javax.swing.SwingUtilities;
import ihm.Fenetre;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InitFailedException, CloneFailedException 
    {
        SwingUtilities.invokeLater(() -> {
            Fenetre f = new Fenetre();
       });
    }
    
}
