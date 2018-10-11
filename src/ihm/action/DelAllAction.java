
package ihm.action;

import ihm.Home;
import ihm.draw.ListDrawDepot;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

/**
 *
 * @author ______________
 */
public class DelAllAction extends AbstractAction{
    
    public DelAllAction(String s, Home h){
        super(s);
        this.liste = h.getListPanel();
        this.pane = h.getScrollPane();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        liste.removeAllDepot();
        pane.revalidate();
        pane.repaint();
        JOptionPane.showMessageDialog(null,"Tous les dépôts ont été supprimés","Suppression terminée",JOptionPane.INFORMATION_MESSAGE);
    }
    
    ListDrawDepot liste;
    JScrollPane pane;
    
}
