package wargames;

public class InvalidCommandException extends Exception{
    // TODO: rename class to JoshuaException or DukeException
    public InvalidCommandException() {
        super("This command is invalid.");
    }

    public InvalidCommandException(String message) {
        super(message);
    }
}
