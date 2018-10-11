
package ihm.action;

import ihm.Home;
import ihm.draw.ListDrawDepot;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JScrollPane;

public class UnSelectAllAction extends AbstractAction {

    public UnSelectAllAction(String s, Home h){
        super(s);
        this.liste = h.getListPanel();
        this.pane = h.getScrollPane();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        liste.unSelectedAllDepot();
        pane.revalidate();
        pane.repaint();
    }
    
    ListDrawDepot liste;
    JScrollPane pane;
    
}
