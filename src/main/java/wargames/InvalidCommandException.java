package wargames;

public class InvalidCommandException extends Exception{
    public InvalidCommandException() {
        super("This command is invalid.");
    }

    public InvalidCommandException(String message) {
        super(message);
    }
}
