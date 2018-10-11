
package ihm.action;

import exception.PullFailedException;
import git.Depot;
import ihm.Gestion;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

public class PullAction extends AbstractAction{

    private Depot dep;
    
    public PullAction(String s, Gestion ges)
    {
        super(s);
        dep=ges.getDepot();
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        try {
            dep.pull();
            JOptionPane.showMessageDialog(null,"Pull effectué","Succes Pull",JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            Logger.getLogger(PullAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PullFailedException ex) {
            Logger.getLogger(PullAction.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Il semble y avoir un problème... Réessayez plus tard","Echec Pull",JOptionPane.INFORMATION_MESSAGE);
        }
            
      
    }
}