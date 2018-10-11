
package git;

import static tools.StringTools.testString;
import static tools.StringTools.estValide;
import exception.*;
import exception.AddRepositoryInitializedFailedException;
import java.io.Serializable;
import java.io.IOException;
import java.util.List;

/**
 * @author yohann
 * @version 1
 */

public class Depot implements Serializable
{
    /**
     * Constructeur vide qui initialise tous les champs à des valeurs par défaut.
     * %HOMEPATH% correspond au dossier par défaut contenant les données de l'utilisateur.
     * Exemple si le nom de session est PC : "C:\Users\PC"
     */
    public Depot()
    {
        this.path = "C:\\Users\\Public";
        this.domain = "Inconnu";
        this.lastComment = "Inconnu";
        this.login = "Inconnu";
        this.projectName = "Inconnu";
    }
    
    /**
     * Constructeur à partir du lien au dépot distant 
     * @param distantLink : 
     * exemple : https://forge.univ-lyon1/p1702174/projet-ihm.git 
     * @see constructFromDistantLink()
     */
    public Depot(String distantLink)
    {
        this();
        
        if(estValide(distantLink))
        {
            constructFromDistantLink(distantLink);
        }
       
    }
    
    /**
     * Constructeur à partir d'un chemin d'accès au dépôt et du nom du dépôt
     * @param path : le chemin au répertoire contenant le dépôt
     * @param projectName : nom du dépôt
     */
    public Depot(String path, String projectName)
    {
        this();
        this.path = testString(path);
        this.projectName = testString(projectName);
    }
    
    /**
     * Constructeur à partir des informations complètes
     * @param domain : exemple : forge.univ-lyon1.fr
     * @param login : exemple : p1702174
     * @param projectName : nom du dépôt
     * @param path : le chemin au répertoire contenant le dépôt
     */
    public Depot(String domain, String login, String projectName, String path)
    {
        this();
        this.domain = testString(domain);
        this.login = testString(login);
        this.projectName = testString(projectName);
        this.path = testString(path);
    }
  
    /**
     * Méthode qui à partir du chemin passé en paramètre, crée un dépôt git
     * et renvoie l'objet Depot associé
     * @param localLink : exemple : C:\\Users\\Yohann\\MonProjet
     * @return l'objet associé au chemin local
     * passé en paramètre sinon
     * @throws IOException
     * @throws exception.InitFailedException 
     */
    public static Depot init(String localLink) 
            throws IOException, InitFailedException
    {
        int exitValue = CommandeShell.execShell("cmd /c pushd " + localLink + " && git init");
        
        if(exitValue != 0) throw new InitFailedException();
        
        Depot d = new Depot();
        d.setFromLocalLink(localLink);
        
        return d;
    }
    
     /**
     * Méthode qui à partir du chemin passé en paramètre, crée un dépôt git
     * et renvoie l'objet Depot associé
     * @param localLink : exemple : C:\\Users\\Yohann\\MonProjet
     * @return l'objet associé au chemin local
     * passé en paramètre sinon
     * @throws IOException
     * @throws  AddRepositoryInitializedFailedException 
     */
    public static Depot addInitializedRepository(String localLink) 
            throws IOException, AddRepositoryInitializedFailedException
    {
        System.out.println(localLink);
        int exitValue = CommandeShell.execShell("cmd /c pushd " + localLink + "\\.git");
        
        
        if(exitValue != 0) throw new AddRepositoryInitializedFailedException();
        List<String> list = CommandeShell.ReadexecShell("cmd /c pushd " + localLink 
                + " && git config --get remote.origin.url ");
        Depot d = new Depot();
        d.setFromLocalLink(localLink);
        if(! list.isEmpty())
            d.setFromDistantLink(list.get(0));
        return d;
    }

    /**
     * Permet d'ajouter tous les nouveaux fichiers du dépôt avant commit
     * @throws IOException 
     * @throws exception.AddFailedException 
     */
    public void add() throws IOException, AddFailedException
    {
        System.out.println(this.getLocalLinkWithProjectName());
        int exitValue = CommandeShell.execShell("cmd /c pushd " 
                        + this.getLocalLinkWithProjectName() + " && git add .");
        
        if(exitValue != 0) throw new AddFailedException();
    }
    
    /**
     * Commit l'état actuel du dépot sur le dépot distant
     * @param comment : le commentaire à passer avec le commit
     * @throws IOException
     * @throws CommitFailedException 
     */
    public void commit(String comment) throws IOException, CommitFailedException
    {
        System.out.println(this.getLocalLinkWithProjectName());
        int exitValue = CommandeShell.execShell("cmd /c pushd " 
                + this.getLocalLinkWithProjectName()
                + " && git commit -m \"" + comment +"\"");
        
        if(exitValue != 0) throw new CommitFailedException();
        if(estValide(comment))
            this.lastComment = comment;
    }

    /**
     * Push le dépôt local sur le distant
     * @throws IOException 
     * @throws exception.PushFailedException 
     */
    public void push() throws IOException, PushFailedException
    {
        int exitValue =  CommandeShell.execShell("cmd /c pushd " 
                                                 + this.getLocalLinkWithProjectName() + " && git push");
        if(exitValue != 0) throw new PushFailedException();
    }
    
    /**
     * Pull le dépôt distant sur le local
     * @throws IOException 
     * @throws exception.PullFailedException 
     */
    public void pull() throws IOException, PullFailedException {
        int exitValue =  CommandeShell.execShell("cmd /c pushd " 
                                                 + this.getLocalLinkWithProjectName() + " && git pull");
        if(exitValue != 0) throw new PullFailedException();
    }
    
    /**
     * Méthode clonant le dépôt distant au path en paramètre. Crée et renvoie
     * un objet Depot associé
     * @param path
     * @param distantLink
     * @return Depot d, construit à partir du lien au dépôt distant et du 
     * chemin au dépôt local
     * @throws IOException
     * @throws CloneFailedException 
     */
    public static Depot gitClone(String path, String distantLink) 
                throws IOException, CloneFailedException
    {
        int exitValue = CommandeShell.execShell("cmd /c pushd " + path 
                                                + " && git clone " + distantLink);
        
        if(exitValue != 0) throw new CloneFailedException();
        
        Depot d = new Depot(distantLink);
        d.setPath(path+"\\");
        
        return d;
    }
   
    /**
     * Méthode clonant le dépôt distant composé des informations passées en 
     * paramètre. Crée et renvoie un objet Depot associé
     * @param path
     * @param domain
     * @param login
     * @param projectName
     * @return Depot d crée à partir des paramètres
     * @throws IOException
     * @throws CloneFailedException 
     */
    public static Depot gitClone(String path, String domain, 
                                      String login, String projectName) 
                                        throws IOException, CloneFailedException
    {
        int exitValue = CommandeShell.execShell("cmd /c pushd " + path + " && git clone https://" 
                                                + domain + "/" + login + "/" + projectName);
        
        if(exitValue != 0) throw new CloneFailedException();
        
        Depot d = new Depot(domain, login, projectName, path);
        return d;
    }


    /**
     * Affiche l'état actuel du dépôt
     * @throws IOException
     * @throws StatusFailedException 
     */
    public void status() throws IOException, StatusFailedException
    {
        int exitValue = CommandeShell.execShell("cmd /c pushd " + this.path 
                                                + " && git status");
        
        if(exitValue != 0) throw new StatusFailedException();
    }

    /**
     * Renvoie le chemin jusqu'au répertoire contenant le dépôt
     * @return la répertoire contenant le dépôt
     */
    public String getPath() 
    {
        return path;
    }

    /**
     * Setter du path, le chemin jusqu'au répertoire contenant le dépôt
     * @param path 
     */
    public void setPath(String path) 
    {
        if(estValide(path))
            this.path = path;
    }

    /**
     * Renvoie le dernier commentaire associé aux commit
     * @return lastComment
     */
    public String getLastComment() 
    {
        return lastComment;
    }

    /**
     * Setter de lastComment
     * @param lastComment 
     */
    public void setLastComment(String lastComment) 
    {
        if(estValide(lastComment))
            this.lastComment = lastComment;
    }

    /**
     * Renvoie le nom de domaine du dépôt distant dont dépend le dépôt
     * @return domain, le nom de domaine
     */
    public String getDomain() 
    {
        return domain;
    }

    /**
     * Setter du nom de domaine du dépôt distant dont dépend le dépôt
     * @param domain 
     */
    public void setDomain(String domain) 
    {
        if(estValide(domain))
            this.domain = domain;
    }

    /**
     * Renvoie le login de l'utilisateur ayant crée le dépôt
     * @return login, le login de l'utilisateur
     */
    public String getLogin() 
    {
        return login;
    }

    /**
     * Setter du login de l'utilisateur
     * @param login 
     */
    public void setLogin(String login) 
    {
        if(estValide(login))
            this.login = login;
    }

    
    /**
     * Renvoie le nom du projet, donc du dépôt
     * @return projectName, le nom du dépôt
     */
    public String getProjectName() 
    {
        String str = projectName;
        if((""+projectName.charAt(0)).equals("\""))
            str = projectName.substring(1, projectName.length() -1);
        System.out.println(projectName.charAt(0));
        System.out.println(str);
        return str;
    }

    /**
     * Setter du nom du dépôt
     * @param projectName 
     */
    public void setProjectName(String projectName) 
    {
        if(estValide(projectName))
            this.projectName = projectName;
    }
    
    /**
     * Renvoie le chemin jusqu'à l'intérieur du dépôt
     * @return le chemin pour aller dans le dépôt
     */
    public String getLocalLinkWithProjectName()
    {
        return this.path + "" + this.projectName;
    }
    
    /**
     * Renvoie le lien au dépôt distant dont le dépot distant
     * @return le lien au dépôt distant associé au dépôt
     */
    public String getDistantLink()
    {
        String link = "https://" + this.domain + "/" + this.login + "/" + this.projectName;
        
            return link ;  
    }
    
    /**
     * Setter des valeurs du nom de domaine (domain), du login et du nom du 
     * dépôt (ProjectName). Set fait à partir du lien au dépôt distant
     * @param distantLink 
     */
    public void setFromDistantLink(String distantLink)
    {

        int startIndex = 8;
        int nextSlash = distantLink.indexOf('/',startIndex);
        
        String tmpDomain = distantLink.substring(startIndex, nextSlash);
        
        startIndex = nextSlash + 1;
        nextSlash = distantLink.indexOf('/',startIndex);
        
        String tmpLogin = distantLink.substring(startIndex, nextSlash);
        
        startIndex = nextSlash + 1;
        
        String tmpProjectName = distantLink.substring(startIndex);
        if(tmpProjectName.contains(".git")){
            int endProjectName = tmpProjectName.indexOf(".git");
            tmpProjectName = tmpProjectName.substring(0, endProjectName);
        }
        
        this.domain = tmpDomain;
        this.login = tmpLogin;
        this.projectName = tmpProjectName;
    }
    
    /**
     * Setter des valeurs du nom de domaine (domain), du login et du nom du 
     * dépôt (ProjectName). Set fait à partir du lien au dépôt distant.
     * Le fait de passer par cette méthode privée permet de faire un appel
     * à cette fonctionnalité depuis le constructeur (déconseillée avec les 
     * méthodes non privées).
     * @see la méthode setFromDistantLink()
     * @param distantLink
     * @throws InvalidLinkException 
     */
    private void constructFromDistantLink(String distantLink) 
    {
        this.setFromDistantLink(distantLink);
    }
    
    /**
     * Setter du nom du dépôt (projectName) et du chemin au répertoire contenant 
     * le dépôt (path) à partir du chemin local memant à l'intérieur du dépôt.
     * @param localLinkWithProjectName 
     */
    public void setFromLocalLink(String localLinkWithProjectName) 
    {
        
        String tmpProjectName = localLinkWithProjectName.substring(localLinkWithProjectName.lastIndexOf('\\')+1);
        
        String tmpPath = localLinkWithProjectName.substring(0, localLinkWithProjectName.indexOf(tmpProjectName));
        
        System.out.println(tmpProjectName+"|"+tmpPath);
        
        
        //Problème d'assignation, sûrement lié au fait qu'ici on assigne des trucs bizarres, on switch d'un dépôt qui init pour faire un autre dépôt je sais pas trop
        this.path = tmpPath;
        this.projectName = tmpProjectName;
    }
    
    /**
     * Détermine si le dépôt est egal à l'objet passé en paramètre
     * @param o
     * @return true si l'objet en paramètre est le même dépôt physique que this,
     * false sinon
     */
    @Override
    public boolean equals(Object o)
    {
        if(o == null) return false;
        if(this == o) return true;
        
        if(!(o instanceof Depot))
        {
            return false;
        }
        
        Depot d = (Depot)o;
        return this.getLocalLinkWithProjectName().equals(d.getLocalLinkWithProjectName());
    }
    
    
    /**
     * Retourne une chaine contenant les informations du dépôt
     * @return les informations du dépôt
     */
    @Override
    public String toString()
    {
        return "Nom projet : " + projectName
                + "\nChemin d'accès au projet : " + path
                + "\nNom de domaine du dépôt distant associé : " + domain
                + "\nLogin du propriétaire du projet : " + login
                + "\nCommentaire du dernier commit connu : " + lastComment;
    }
    
    /**
     * chemin menant au répertoire contenant le dépot distant
     * exemple : "C:\\Users\\Yohann\\MesProjetsGit"
     */
    private String path;
    
    /**
     * dernier commentaire associé au dernier commit du dépôt
     */
    private String lastComment;
    
    /**
     * nom du domaine sur lequel le dépôt du distant associé au dépôt est stocké
     * exemple : "forge.univ-lyon1"
     */
    private String domain;
    
    /**
     * login de l'utilisateur ayant crée le dépôt, permet de conserver l'url
     * eu dépôt distant. exemple : "P1702174"
     */
    private String login;
    
    /**
     * nom du projet, donc du dépôt
     */
    private String projectName;

    
    
}
