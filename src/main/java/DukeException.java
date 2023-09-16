class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}

class EmptyCommandException extends DukeException {
    public EmptyCommandException() {
        super("You did not provide any command. Please try again.");
    }
}

class EmptyArgumentException extends DukeException {
    public EmptyArgumentException() {
        super("You did not provide any details on your todo. Please provide in the format of <event + Task Name>.");
    }
}

class TaskCreationException extends DukeException {
    public TaskCreationException(String message) {
        super("Error creating task: " + message);
    }
}

class InvalidNumberFormatException extends DukeException {
    public InvalidNumberFormatException(String message) {
        super(message);
    }
}

class InvalidTaskFormatException extends DukeException {
    public InvalidTaskFormatException(String taskType) throws DukeException {
        super(getErrorMessage(taskType));
    }

    private static String getErrorMessage(String taskType) throws DukeException {
        switch (taskType) {
            case "deadline":
                return "Invalid format for Deadline task. Please provide in the format of <deadline + Task Name + /by + by time>";
            case "event":
                return "Invalid format for Event task. Please provide in the format of <event + Task Name + /from + From time + /to + To time>";
            default:
                throw new DukeException("Unknown task type: " + taskType);
        }
    }
}
