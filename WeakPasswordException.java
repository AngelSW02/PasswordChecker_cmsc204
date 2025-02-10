/**
 * Exception thrown when a password is valid but considered weak.
 * @author Angel Abrigo
 */
public class WeakPasswordException extends Exception {

    /**
     * Default constructor that sets the default error message.
     */
    public WeakPasswordException() {
        super("The password is OK but weak - it contains fewer than 10 characters");
    }

    /**
     * Constructor that allows a custom error message.
     * @param message Custom error message.
     */
    public WeakPasswordException(String message) {
        super(message);
    }
}
