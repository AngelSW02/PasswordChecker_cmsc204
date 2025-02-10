/**
 * Exception thrown when two passwords do not match.
 * @author Angel Abrigo
 */
public class UnmatchedException extends Exception {

    /**
     * Default constructor that sets the default error message.
     */
    public UnmatchedException() {
        super("The passwords do not match");
    }

    /**
     * Constructor that allows a custom error message.
     * @param message Custom error message.
     */
    public UnmatchedException(String message) {
        super(message);
    }
}
