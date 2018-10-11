
package exception;

public class AddFailedException extends Exception 
{
    public AddFailedException()
    {
        super("La commande add a échoué...");
    }
}
