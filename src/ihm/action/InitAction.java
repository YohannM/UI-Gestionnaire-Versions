
package ihm.action;

import exception.InitFailedException;
import git.Depot;
import ihm.Home;
import ihm.draw.ListDrawDepot;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import static tools.PathTools.nettoyerPath;

public class InitAction extends AbstractAction{
    
    public InitAction(String s, Home h){
        super(s);
        this.liste = h.getListPanel();
        this.pane = h.getScrollPane();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            
            JFileChooser file = new JFileChooser();
            file.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            
            if(file.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                String repo = nettoyerPath(file.getSelectedFile().getAbsolutePath());
                Depot d = Depot.init(repo);
                liste.addDepot(d);
                pane.revalidate();
                pane.repaint();
                JOptionPane.showMessageDialog(null,"Dépôt git crée","Init réussi",JOptionPane.INFORMATION_MESSAGE);
            }     
        } catch (IOException ex) {
            Logger.getLogger(InitAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InitFailedException ex) {
            JOptionPane.showMessageDialog(null,"Il y a eu un problème lors de la création du dépôt","Init échoué",JOptionPane.INFORMATION_MESSAGE);
            Logger.getLogger(InitAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    ListDrawDepot liste;
    JScrollPane pane;
}
