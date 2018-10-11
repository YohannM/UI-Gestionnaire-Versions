
package exception;

public class PullFailedException extends Exception{
    
    public PullFailedException()
    {
        super("Echec du pull du dépôt.");
    }
    
}
