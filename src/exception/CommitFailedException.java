
package exception;

public class CommitFailedException extends Exception
{
    public CommitFailedException()
    {
        super("Echec du commit.");
    }
}
