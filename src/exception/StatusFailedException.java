
package exception;

public class StatusFailedException extends Exception
{
    public StatusFailedException()
    {
        super("Echec de la consultation de l'état du dépôt");
    }
}
