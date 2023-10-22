package nus.duke.exceptions;

/**
 * The `UnknownCommandDukeException` class represents an exception specific to Duke
 * that is thrown when an unrecognized or unknown command is encountered.
 * It extends the `DukeException` class and is used for error handling when Duke receives an unknown command.
 */
public class UnknownCommandDukeException extends DukeException {
    /**
     * Instantiates a new `UnknownCommandDukeException` with the specified error message.
     *
     * @param errorMessage The error message describing the exception, typically indicating the issue with an unknown
     *                     command.
     */
    public UnknownCommandDukeException(String errorMessage) {
        super(errorMessage);
    }
}
