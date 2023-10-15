package Duke.DukeExceptions;

public class InvalidNumberFormatException extends DukeException {
    public InvalidNumberFormatException(String message) {
        super(message + " is not a valid number! Please Try again.");
    }

    public InvalidNumberFormatException() {
        super("Invalid Number! Please Try again.");
    }
}
