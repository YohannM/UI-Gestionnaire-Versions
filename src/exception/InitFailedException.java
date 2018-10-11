
package exception;

public class InitFailedException extends Exception
{
    public InitFailedException()
    {
        super("Echec de la création du dépôt git.");
    }
}
