
package ihm;

import ihm.action.*;
import ihm.draw.*;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.IOException;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import static tools.JLabelTools.changeFontSize;

public class Home extends JPanel{
    
    public Home(Fenetre fen){
        super();
        fen.setTitle("Projet IHM");
        fen.setSize(850,750);
        fen.setResizable(false);
        this.fen = fen;
        
        build();
    }
    
    private void build(){
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        JPanel panelScroll = new JPanel();
        buildHomeScrollPane();
        JPanel panelDel = buildDelButton();
        JPanel panelSelect = buildSelectButton();
        JPanel panelAdd = buildAddButton();
        
        panelScroll.add(Box.createRigidArea(new Dimension(1,1)));
        panelScroll.add(scrollPane);
        panelScroll.add(Box.createGlue());
        panelScroll.add(panelAdd);
        panelScroll.add(Box.createGlue());
        
        this.add(panelSelect);
        this.add(panelScroll);
        this.add(panelDel);
    }
    
    private void buildHomeScrollPane(){
        scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(600, 500));
        
        listPanel = new ListDrawDepot();
        
        try {
            listPanel.deSerialize();
            JOptionPane.showMessageDialog(null,"Vos dépôts ont été récupérés avec succès","Bienvenue",JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            String str = "Vous n'avez pas de dépôt local sauvegardés sur ce gestionnaire. \nAjoutez vos dépôts git et vous les retrouverez et pourrez les gérer facilement lors de vos prochains passages";
            JOptionPane.showMessageDialog(null,str,"Bienvenue sur votre gestionnaire de dépôt git !",JOptionPane.INFORMATION_MESSAGE);
            listPanel = new ListDrawDepot();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"Il semble qu'il y ait eu un problème lors de la récupération de vos dépôts","Echec de la récupération",JOptionPane.INFORMATION_MESSAGE);
            listPanel = new ListDrawDepot();
        }
        
        scrollPane.setViewportView(listPanel);
    }
    
    private JPanel buildDelButton(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
        panel.setAlignmentY(TOP_ALIGNMENT);

        panel.add(Box.createRigidArea(new Dimension(10,10)));
        panel.add(new JButton(new DelSelectedAction("Supprimer la sélection",this)));
        panel.add(Box.createRigidArea(new Dimension(10,10)));
        panel.add(new JButton(new DelAllAction("Tout supprimer",this)));
        
        return panel;
    }
    
    private JPanel buildSelectButton(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
        
        JButton b1 = new JButton(new SelectAllAction("Tout sélectionner",this));
        JButton b2 = new JButton(new UnSelectAllAction("Tout desélectionner",this));
        
        panel.setMinimumSize(new Dimension(1000,200));
        
        panel.add(Box.createRigidArea(new Dimension(10,10)));
        panel.add(b1);
        panel.add(Box.createRigidArea(new Dimension(10,10)));
        panel.add(b2);

        return panel;
    }
    
    private JPanel buildAddButton(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(200,550));
        
        JButton b1 = new JButton(new InitAction("Init",this));
        JButton b2 = new JButton(new AddInitializedRepoAction("Ajout dépot local existant",this));
        JButton b3 = new JButton(new CloneAction("Clone",this));
        JLabel lAdd = new JLabel("Ajouter un nouveau");
        JLabel lAdd2 = new JLabel("dépôt local :");
        changeFontSize(lAdd, 20f);
        changeFontSize(lAdd2, 20f);
        
        lAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
        lAdd2.setAlignmentX(Component.CENTER_ALIGNMENT);
        b1.setAlignmentX(Component.CENTER_ALIGNMENT);
        b2.setAlignmentX(Component.CENTER_ALIGNMENT);
        b3.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panel.add(Box.createRigidArea(new Dimension(200,150)));
        panel.add(lAdd);
        panel.add(lAdd2);
        panel.add(Box.createRigidArea(new Dimension(25,30)));
        panel.add(b1);
        panel.add(Box.createRigidArea(new Dimension(25,25)));
        panel.add(b3);
        panel.add(Box.createRigidArea(new Dimension(25,25)));
        panel.add(b2);
        
        
        panel.setAlignmentY(Component.TOP_ALIGNMENT);
        
        return panel;
    }
    
    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public ListDrawDepot getListPanel() {
        return listPanel;
    }

    private Fenetre fen;
    private JScrollPane scrollPane;
    private ListDrawDepot listPanel;
    
}
