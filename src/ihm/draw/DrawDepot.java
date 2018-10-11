
package ihm.draw;

import git.Depot;
import ihm.Gestion;
import ihm.action.ManageAction;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class DrawDepot extends JPanel implements Serializable{
    
    public DrawDepot(Depot depot){
        this.depot = depot;
        this.panelButton = buildPanelButton(depot);
        this.panelLeft = buildPanelLeft();
        
        this.borderLayout = new BorderLayout();
        this.setLayout(borderLayout);
        
        this.dim = new Dimension(500,75);
        this.setPreferredSize(dim);
        
        this.add(panelLeft,BorderLayout.WEST);
        this.add(panelButton,BorderLayout.EAST);
        this.setBorder(BorderFactory.createMatteBorder(0,0,1,1,Color.gray));
        
        this.isManaged = false;
        
    }
    
    private JPanel buildPanelButton(Depot d){
        
        JPanel p = new JPanel(new FlowLayout());
        button = new JButton(new ManageAction("Gérer",this));
        p.add(button);
        
        return p;
    }
    
    private JPanel buildPanelLeft(){
        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        this.checkBox = new JCheckBox();
        p.add(checkBox);
        this.panelTexte = buildPanelText();
        p.add(panelTexte);
        return p;
    }
    
    private JPanel buildPanelText(){
        
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
        title = new JLabel(depot.getProjectName());
        JLabel localLink = new JLabel("Lien local: "+depot.getPath());
        p.add(title);
        p.add(localLink);
        if(! depot.getDistantLink().contains("Inconnu")){
            JLabel distantLink = new JLabel("Lien distant: "+depot.getDistantLink());
            p.add(distantLink);
        }
        return p;
    }

    public boolean isSelected() {
        return checkBox.isSelected();
    }
    
    public void selected() {
        checkBox.setSelected(true);
        checkBox.revalidate();
        checkBox.repaint();
    }
    
    public void unSelected() {
        checkBox.setSelected(false);
        checkBox.revalidate();
        checkBox.repaint();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (! (o instanceof DrawDepot)){
            return false;
        }
        DrawDepot other = (DrawDepot) o;
        return depot.equals(other.getDepot());
    }
    
    public Depot getDepot(){
        return depot;
    }
    
    public boolean isManaged(){
        return isManaged;
    }
    
    public void manageDepot() {
        if(! isManaged){
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            frame.addWindowListener(new WindowAdapter() {
                
                @Override
                public void windowClosing(WindowEvent e) {
                        isManaged = false;
                        frame.setVisible(false);
                        frame.dispose();
                }
            });

            frame.add(new Gestion(depot,frame));
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
            isManaged = true;
        }
    }

    private boolean isManaged;
    private Depot depot;
    private JPanel panelLeft;
    private JCheckBox checkBox;
    private JPanel panelTexte;
    private JLabel title;
    private JPanel panelButton;
    private JButton button;
    private BorderLayout borderLayout;
    private Dimension dim;
}
