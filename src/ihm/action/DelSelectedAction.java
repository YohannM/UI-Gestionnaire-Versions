
package ihm.action;

import ihm.Home;
import ihm.draw.ListDrawDepot;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class DelSelectedAction extends AbstractAction {
    
    public DelSelectedAction(String s, Home h){
        super(s);
        this.liste = h.getListPanel();
        this.pane = h.getScrollPane();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        liste.removeDepot();
        JOptionPane.showMessageDialog(null,"Le dépôt a été supprimé","Suppression terminée",JOptionPane.INFORMATION_MESSAGE);
    }
    
    ListDrawDepot liste;
    JScrollPane pane;
    
}
