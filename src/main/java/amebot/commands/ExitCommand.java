package amebot.commands;

/**
 * Represents an exit command.
 */
public class ExitCommand extends Command {
    /**
     * Returns true if the command is exit.
     *
     * @param command Command from the user input
     * @return True if the command is exit, false otherwise
     */
    public static boolean isExit(String command) {
        return command.equalsIgnoreCase("bye");
    }
}
