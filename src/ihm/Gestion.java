package ihm;

import git.Depot;
import ihm.action.CommitAction;
import ihm.action.PullAction;
import ihm.action.PushAction;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import static tools.JLabelTools.changeFontSize;


public class Gestion extends JTabbedPane {
    
    private JTextArea field;
    private Depot dep;
    private JFrame fen;
    private JLabel lastComment;
    
    public Gestion (Depot dep, JFrame fen)
    {
        super();
        //buildTab();
        fen.setTitle("Gestion du dépôt " + dep.getProjectName());
        fen.setSize(new Dimension(700, 400));
        fen.setResizable(false);
        this.dep=dep;
        this.fen = fen;
        
        buildPan();
        
    }
    

    private void buildPan() {
       this.addTab("Resumé",resumPan());
        this.addTab("Commit",commitPane());
        if(! dep.getDistantLink().contains("Inconnu")){
            this.addTab("Push/Pull",ppPane());
        }
    }

    private JPanel commitPane() {
        JPanel pan = new JPanel();
        JPanel panTop = new JPanel();
        JPanel panBot = new JPanel();
        JLabel label =  new JLabel ("Quel est votre message de commit : ");
        field = new JTextArea(10, 60);
        field.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        JButton button = new JButton(new CommitAction("Commit",this));
        panTop.add(label);
        pan.add(panTop);
        pan.add(panBot);
        
        pan.add(field);
        
        panBot.setPreferredSize(new Dimension(600,150));
        panBot.add(button);
        pan.add(panBot);
        
        return pan;
        
        
    }

    private Component ppPane() {
        JPanel pan = new JPanel();
        
        JButton but1 = new JButton(new PushAction("Push",this));
        JButton but2 = new JButton(new PullAction("Pull",this));
        
        pan.add(but1);
        pan.add(but2);
        
        return pan;
    }

    private Component resumPan() {
        JPanel pan = new JPanel();
        pan.setLayout(new BoxLayout(pan, BoxLayout.PAGE_AXIS));
        
        JLabel name = new JLabel();
        name.setText("Nom projet : " + dep.getProjectName());
        changeFontSize(name, 15f);
        
        JLabel path = new JLabel();
        path.setText("\nChemin d'accès au projet : " + dep.getPath());
        changeFontSize(path, 15f);
        
        JLabel domain = new JLabel();
        domain.setText("\nNom de domaine du dépôt distant associé : " + dep.getDomain());
        changeFontSize(domain, 15f);
        
        JLabel login = new JLabel();
        login.setText("\nLogin du propriétaire du projet : " + dep.getLogin());
        changeFontSize(login, 15f);
        
        lastComment = new JLabel();
        lastComment.setText("\nCommentaire du dernier commit connu : " + dep.getLastComment());
        changeFontSize(lastComment, 15f);
        
        pan.add(Box.createRigidArea(new Dimension(25,25)));
        pan.add(name);
        pan.add(Box.createRigidArea(new Dimension(25,25)));
        pan.add(path);
        pan.add(Box.createRigidArea(new Dimension(25,25)));
        pan.add(domain);
        pan.add(Box.createRigidArea(new Dimension(25,25)));
        pan.add(login);
        pan.add(Box.createRigidArea(new Dimension(25,25)));
        pan.add(lastComment);
        return pan;
    }


    public JTextArea getTextArea()
    {
      return field;  
    }
    
    public Depot getDepot()
    {
      return dep;  
    }
    
    public JLabel getLastComment()
    {
      return lastComment;  
    }
    
}
