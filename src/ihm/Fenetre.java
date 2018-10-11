
package ihm;

import ihm.action.WindowAction;
import git.Depot;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Fenetre extends JFrame
{
    public Fenetre()
    {
        super();
        
        this.setTitle("Projet IHM - Interface de gestion de mes dépôt GIT");
        this.setSize(new Dimension(1000, 800));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        home = new Home(this);
        this.addWindowListener(new WindowAction(home.getListPanel()));
        this.setVisible(true);
        this.setContentPane(home);
    }
    
    private void manageDepot(Depot d) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(new Gestion(new Depot(),frame));
        frame.pack();
        frame.setVisible(true);
    }
    
    private Home home;
}
