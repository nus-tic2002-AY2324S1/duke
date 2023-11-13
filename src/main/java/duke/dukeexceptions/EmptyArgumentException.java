package duke.dukeexceptions;

import duke.parser.CommandValidator;

/**
 * Exception thrown when an empty argument is encountered for a specific command type.
 * This exception is thrown to indicate that the description or specific information
 * required for a particular command type is missing or empty.
 */
public class EmptyArgumentException extends DukeException {

    /**
     * Constructs an EmptyArgumentException with a specific command type.
     *
     * @param commandType The type of the command for which the empty argument occurred.
     * @throws DukeException If an unknown command type is provided.
     */
    public EmptyArgumentException(String commandType) throws DukeException {
        super(getErrorMessage(commandType));
    }

    /**
     * Gets the error message corresponding to the provided command type.
     *
     * @param commandType The type of the command for which the empty argument occurred.
     * @return The error message for the specific command type.
     * @throws DukeException If an unknown command type is provided.
     */
    private static String getErrorMessage(String commandType) throws DukeException {

        switch (commandType) {
        case CommandValidator.TODO_COMMAND:
            return "The description of a todo task cannot be empty!";
        case CommandValidator.DEADLINE_COMMAND:
            return "The description of a deadline task cannot be empty!";
        case CommandValidator.EVENT_COMMAND:
            return "The description of a event task cannot be empty!";
        case CommandValidator.ON_COMMAND:
            return "Please provide the specific date that you want to check if a task exists!";
        case CommandValidator.FIND_COMMAND:
            return "Please provide the specific keyword that you want to check if a task exists!";
        case CommandValidator.RESCHEDULE_COMMAND:
            return "Please provide the specific task and date that you want to postpone!";
        case CommandValidator.DELETE_COMMAND:
            return "Please provide the specific task that you want to delete!";
        case CommandValidator.MARK_COMMAND:
            return "Please provide the specific task that you want to mark as completed!";
        case CommandValidator.UNMARK_COMMAND:
            return "Please provide the specific task that you want to mark as incomplete!";
        default:
            throw new DukeException("Unknown Command type: " + commandType);
        }
    }

}
