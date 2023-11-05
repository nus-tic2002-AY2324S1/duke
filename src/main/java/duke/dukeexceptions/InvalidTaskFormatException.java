package duke.dukeexceptions;

/**
 * The InvalidTaskFormatException class is used to indicate that the user
 * provided a task in an invalid format.
 */
public class InvalidTaskFormatException extends DukeException {

    /**
     * Constructs a new InvalidTaskFormatException with a specified task type.
     *
     * @param taskType The type of task (e.g., "deadline" or "event") for which the format is invalid.
     * @throws DukeException if the task type is unknown or unsupported.
     */
    public InvalidTaskFormatException(String taskType) throws DukeException {

        super(getErrorMessage(taskType));
    }

    /**
     * Generates an error message based on the provided task type.
     *
     * @param taskType The type of task (e.g., "deadline" or "event") for which the format is invalid.
     * @return The error message indicating the invalid task format.
     * @throws DukeException if the task type is unknown or unsupported.
     */
    private static String getErrorMessage(String taskType) throws DukeException {

        switch (taskType) {
        case "deadline":
            return "Invalid format for Deadline task. Please provide in the format of "
                + "<deadline + Task Name + /by + yyyy-MM-dd HH:mm>";
        case "event":
            return "Invalid format for Event task. Please provide in the format of "
                + "<event + Duke.Task.Task Name + /from + "
                + "yyyy-MM-dd HH:mm:ss + /to + yyyy-MM-dd HH:mm>";
        default:
            throw new DukeException("Unknown task type: " + taskType);
        }
    }

}
