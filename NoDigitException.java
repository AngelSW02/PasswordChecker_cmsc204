/**
 * Exception thrown when a password does not contain at least one digit.
 * @author Angel Abrigo
 */
public class NoDigitException extends Exception {
    
    /**
     * Default constructor that sets the default error message.
     */
    public NoDigitException() {
        super("The password must contain at least one digit");
    }

    /**
     * Constructor that allows a custom error message.
     * @param message Custom error message.
     */
    public NoDigitException(String message) {
        super(message);
    }
}
