/**
 * Exception thrown when a password contains more than two of the same character in sequence.
 * @author Angel Abrigo
 */
public class InvalidSequenceException extends Exception {
    
    /**
     * Default constructor that sets the default error message.
     */
    public InvalidSequenceException() {
        super("The password cannot contain more than two of the same character in sequence");
    }

    /**
     * Constructor that allows a custom error message.
     * @param message Custom error message.
     */
    public InvalidSequenceException(String message) {
        super(message);
    }
}
