package Duke.DukeExceptions;

public class InvalidTaskFormatException extends DukeException {
    public InvalidTaskFormatException(String taskType) throws DukeException {
        super(getErrorMessage(taskType));
    }

    private static String getErrorMessage(String taskType) throws DukeException {
        switch (taskType) {
            case "deadline":
                return "Invalid format for Deadline task. Please provide in the format of <deadline + Task Name + /by + yyyy-MM-dd HH:mm>";
            case "event":
                return "Invalid format for Event task. Please provide in the format of <event + Duke.Task.Task Name + /from + yyyy-MM-dd HH:mm:ss + /to + yyyy-MM-dd HH:mm>";
            default:
                throw new DukeException("Unknown task type: " + taskType);
        }
    }
}
