package ihm.action;

import exception.AddFailedException;
import exception.CommitFailedException;
import git.Depot;
import ihm.Gestion;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class CommitAction extends AbstractAction{

    public CommitAction (String s, Gestion ges)
    {
        super(s);
        text=ges.getTextArea();
        dep=ges.getDepot();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        try {
            String comment = text.getText();
            dep.add();
            dep.commit(comment);
            lastComment.setText("\nCommentaire du dernier commit connu : " + comment);
            JOptionPane.showMessageDialog(null,"Commit effectué","Succes Commit",JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            Logger.getLogger(CommitAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CommitFailedException ex) {
            JOptionPane.showMessageDialog(null,"Echec du commit. Vérifier que vous avez modifié le dépôt et que celui ci est à jour.","Echec",JOptionPane.INFORMATION_MESSAGE);
            Logger.getLogger(CommitAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AddFailedException ex) {
            Logger.getLogger(CommitAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private JTextArea text;
    private Depot dep;
    private JLabel lastComment;
    
    
}
