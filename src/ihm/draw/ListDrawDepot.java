
package ihm.draw;

import git.Depot;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class ListDrawDepot extends JPanel{
    
    public ListDrawDepot(){
        this.list = new ArrayList<>();
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
    }
    
    public ListDrawDepot(List<DrawDepot> list){
        this.list = new ArrayList(list);
        addDrawDepot(this.list);
    }
    
    public void addDepot(Depot d){
        DrawDepot drawD = new DrawDepot(d);
        if(! this.list.contains(drawD)){
            this.list.add(drawD);
            this.add(drawD);
            this.revalidate();
            this.repaint();
        }
        
    }
    
    public void addDepot(List<Depot> l){
        Iterator<Depot> it = l.iterator();
        while(it.hasNext()){
            addDepot(it.next());
        }
    }
    
    public void addDepot(DrawDepot d){
        if(! this.list.contains(d)){
            list.add(d);
            this.add(d);
            this.revalidate();
            this.repaint();
        }
    }
    
    public void addDrawDepot(List<DrawDepot> l){
        Iterator<DrawDepot> it = l.iterator();
        while(it.hasNext()){
            addDepot(it.next());
        }
    }
    
    public void removeDepot(){
        List<DrawDepot> delDepot = new ArrayList();
        Iterator<DrawDepot> it = list.iterator();
        while(it.hasNext()){
            DrawDepot o = it.next();
            if(o.isSelected()){
                it.remove();
                this.remove(o);
                
            }
        }
        this.repaint();
        this.revalidate();
    }
    
    
    public void removeAllDepot(){
        list.clear();
        this.removeAll();
        this.revalidate();
        this.repaint();
    }
    
    public void selectedAllDepot() {
        Iterator<DrawDepot> it = list.iterator();
        while(it.hasNext()){
            DrawDepot o = it.next();
            o.selected();
            o.revalidate();
            o.repaint();   
        }
    }
    
    public void unSelectedAllDepot() {
        Iterator<DrawDepot> it = list.iterator();
        while(it.hasNext()){
            DrawDepot o = it.next();
            o.unSelected();
            o.revalidate();
            o.repaint();
        }
    }
    
    public void serialize() throws IOException
    {
        
        String file = "./MesDepots.dat";
        ObjectOutputStream sortie = new ObjectOutputStream( new FileOutputStream(file));
        
            for(DrawDepot d : list)
            {
                sortie.writeObject(d);
            }
        
            sortie.close();
        
    }
    
    public void deSerialize() throws  ClassNotFoundException, IOException
    {
        String file = "./MesDepots.dat";
        ObjectInputStream entree = new ObjectInputStream(new FileInputStream(file));
        
        boolean eof = false;
        
        while(!eof)
        {
            try
            {
                DrawDepot d = (DrawDepot) entree.readObject();
                list.add(d);
                this.add(d);
            }
            catch(EOFException e)
            {
                eof = true;
            }
            
        }
        
        this.revalidate();
        this.repaint();
    }
    
    private List<DrawDepot> list;
}
