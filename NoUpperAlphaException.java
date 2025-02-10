/**
 * Exception thrown when a password does not contain at least one uppercase letter.
 * @author Angel Abrigo
 */
public class NoUpperAlphaException extends Exception {
    
    /**
     * Default constructor that sets the default error message.
     */
    public NoUpperAlphaException() {
        super("The password must contain at least one uppercase alphabetic character");
    }

    /**
     * Constructor that allows a custom error message.
     * @param message Custom error message.
     */
    public NoUpperAlphaException(String message) {
        super(message);
    }
}
