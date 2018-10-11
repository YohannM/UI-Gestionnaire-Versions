
package exception;

public class AddRepositoryInitializedFailedException extends Exception{

    public AddRepositoryInitializedFailedException() {
        super("Aucun repertoire .git trouve...");
    }
    
}
