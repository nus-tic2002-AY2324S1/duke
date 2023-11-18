package amebot.commands;

import amebot.common.Messages;

import java.util.ArrayList;

/**
 * Represents an exit command.
 */
public class ExitCommand extends Command {
    protected ArrayList<String> logs = new ArrayList<>();

    /**
     * Returns the logs for output.
     *
     * @return Logs for output.
     */
    @Override
    public ArrayList<String> executeCommand() {
        logs.add(Messages.EXIT_MESSAGE);
        return logs;
    }

    /**
     * Returns true if command is an exit command.
     *
     * @param command User input command.
     * @return True if command is an exit command, false otherwise.
     */
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
