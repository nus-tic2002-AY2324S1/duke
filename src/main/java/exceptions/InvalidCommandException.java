package exceptions;

/**
 * Thrown when an error is found in the formatting of a given command.
 */
public class InvalidCommandException extends Exception{

    public InvalidCommandException() {
        super("This command is invalid.");
    }

    public InvalidCommandException(String message) {
        super(message);
    }
}
