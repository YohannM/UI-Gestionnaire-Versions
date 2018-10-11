
package ihm.action;

import exception.CloneFailedException;
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

public class CloneAction extends AbstractAction{
     
    public CloneAction(String s, Home h){
        super(s);
        this.liste = h.getListPanel();
        this.pane = h.getScrollPane();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            
            String s = JOptionPane.showInputDialog("Saisir le lien distant du dépot");
            JOptionPane.showMessageDialog(null,"Veuillez choisir un emplacement pour le dépôt.","Clone de dépôt",JOptionPane.INFORMATION_MESSAGE);
            JFileChooser file = new JFileChooser();
            file.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            
            if(file.showOpenDialog(null) == JFileChooser.APPROVE_OPTION && ! s.equals("")){
                String path = nettoyerPath(file.getSelectedFile().getAbsolutePath());
                Depot d = Depot.gitClone(path,s);
                liste.addDepot(d);
                pane.revalidate();
                pane.repaint();
                JOptionPane.showMessageDialog(null,"Clone réussi","Succes Clone",JOptionPane.INFORMATION_MESSAGE);

            }     
        } catch (IOException ex) {
            Logger.getLogger(InitAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CloneFailedException ex) {
            Logger.getLogger(CloneAction.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Echec du clone. Veuillez vérifier votre lien distant.","Clone échoué",JOptionPane.INFORMATION_MESSAGE);

        }
    }
    ListDrawDepot liste;
    JScrollPane pane;
}
