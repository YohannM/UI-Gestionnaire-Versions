
package ihm.action;

import ihm.draw.ListDrawDepot;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.JOptionPane;

public class WindowAction extends WindowAdapter {

    public WindowAction(ListDrawDepot ldd) {
        this.ldd = ldd;
    }


    @Override
    public void windowClosing(WindowEvent e) {
        try {
            ldd.serialize();
            JOptionPane.showMessageDialog(null,"Sauvegarde des dépôt effectuée","Sauvegarde réussie",JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Il y a eu un problème lors de la sauvegarde des dépôts","Sauvegarde échouée",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private ListDrawDepot ldd;
    
}
