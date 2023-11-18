package nus.duke.exceptions;

/**
 * The `InvalidCommandArgsDukeException` class represents an exception specific to Duke
 * that is thrown when an invalid or improperly formatted command argument is encountered.
 * It extends the `DukeException` class and is used for error handling related to command arguments.
 */
public class InvalidCommandArgsDukeException extends DukeException {
    /**
     * Instantiates a new `InvalidCommandArgsDukeException` with the specified error message.
     *
     * @param errorMessage The error message describing the exception, typically indicating the issue with command
     *                     arguments.
     */
    public InvalidCommandArgsDukeException(String errorMessage) {
        super(errorMessage);
    }
}
