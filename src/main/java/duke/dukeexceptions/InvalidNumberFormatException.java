package duke.dukeexceptions;

/**
 * The InvalidNumberFormatException class is used to indicate that the user
 * provided an invalid number or numeric input.
 */
public class InvalidNumberFormatException extends DukeException {

    /**
     * Constructs a new InvalidNumberFormatException with a specified error message.
     *
     * @param message The error message indicating the provided value is not a valid number.
     */
    public InvalidNumberFormatException(String message) {

        super(message + " is not a valid number! Please try again.");
    }

    /**
     * Constructs a new InvalidNumberFormatException with
     * a generic error message for an invalid number.
     */
    public InvalidNumberFormatException() {

        super("Invalid Number! Please try again.");
    }

}
