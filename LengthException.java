/**
 * Exception thrown when a password is shorter than the required length.
 * @author Angel Abrigo
 */
public class LengthException extends Exception {
    
    /**
     * Default constructor that sets the default error message.
     */
    public LengthException() {
        super("The password must be at least 6 characters long");
    }

    /**
     * Constructor that allows a custom error message.
     * @param message Custom error message.
     */
    public LengthException(String message) {
        super(message);
    }
}
