
package exception;

public class CloneFailedException extends Exception
{
    public CloneFailedException()
    {
        super("Echec du clone.");
    }
}
