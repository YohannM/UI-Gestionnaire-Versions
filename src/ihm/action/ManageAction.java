
package ihm.action;

import ihm.draw.DrawDepot;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class ManageAction extends AbstractAction{
    
    public ManageAction(String s, DrawDepot d){
        super(s);
        this.depot = d;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        depot.manageDepot();
    }
    
    DrawDepot depot;
    
}
