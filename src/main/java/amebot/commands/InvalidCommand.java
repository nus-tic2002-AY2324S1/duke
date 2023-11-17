package amebot.commands;

import java.util.ArrayList;

/**
 * Represents an invalid command.
 */
public class InvalidCommand extends Command {
    protected String errorMessage;
    protected ArrayList<String> logs = new ArrayList<>();

    public InvalidCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Returns the error message.
     *
     * @return the error message.
     */
    public ArrayList<String> executeCommand() {
        logs.add(this.errorMessage);
        return logs;
    }
}
