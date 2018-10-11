
package exception;

public class PushFailedException extends Exception
{
    public PushFailedException()
    {
        super("Echec du push du dépôt.");
    }
}
