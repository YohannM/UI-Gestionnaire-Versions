
package ihm.action;


import exception.PushFailedException;
import git.Depot;
import ihm.Gestion;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

public class PushAction extends AbstractAction{

    private Depot dep;
    
    public PushAction(String s, Gestion ges)
    {
        super(s);
        dep=ges.getDepot();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            dep.push();
            JOptionPane.showMessageDialog(null,"Push effectué","Succes Push",JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            Logger.getLogger(PushAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PushFailedException ex) {
            JOptionPane.showMessageDialog(null,"Etes-vous à jour par rapport au dépôt distant ? \nAvez-vous commit vos changements ?","Echec du push",JOptionPane.INFORMATION_MESSAGE);
            Logger.getLogger(PushAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
