/**
 * Exception thrown when a password does not contain at least one lowercase letter.
 * @author Angel Abrigo
 */
public class NoLowerAlphaException extends Exception {
    
    /**
     * Default constructor that sets the default error message.
     */
    public NoLowerAlphaException() {
        super("The password must contain at least one lowercase alphabetic character");
    }

    /**
     * Constructor that allows a custom error message.
     * @param message Custom error message.
     */
    public NoLowerAlphaException(String message) {
        super(message);
    }
}
