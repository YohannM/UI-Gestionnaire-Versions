
package ihm.action;

import exception.AddRepositoryInitializedFailedException;
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

public class AddInitializedRepoAction extends AbstractAction{
    
    public AddInitializedRepoAction(String s, Home h){
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
                String path = nettoyerPath(file.getSelectedFile().getAbsolutePath());
                System.out.println(path);
                Depot d = Depot.addInitializedRepository(path);
                liste.addDepot(d);
                pane.revalidate();
                pane.repaint();
                JOptionPane.showMessageDialog(null,"Dépôt ajouté avec succès","Succès de l'ajout",JOptionPane.INFORMATION_MESSAGE);

            }     
        } catch (IOException ex) {
            Logger.getLogger(InitAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AddRepositoryInitializedFailedException ex) {
            JOptionPane.showMessageDialog(null,"Il semble que le dossier que vous essayiez d'ajouter ne soit pas un dépôt git...","Echec Ajout",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    ListDrawDepot liste;
    JScrollPane pane;
}
