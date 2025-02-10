/**
 * Exception thrown when a password does not contain at least one special character.
 * @author Angel Abrigo
 */
public class NoSpecialCharacterException extends Exception {
    
    /**
     * Default constructor that sets the default error message.
     */
    public NoSpecialCharacterException() {
        super("The password must contain at least one special character");
    }

    /**
     * Constructor that allows a custom error message.
     * @param message Custom error message.
     */
    public NoSpecialCharacterException(String message) {
        super(message);
    }
}
