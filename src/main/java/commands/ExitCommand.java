package commands;

public class ExitCommand extends Command {
    public static boolean isExit(String command) {
        return command.equalsIgnoreCase("bye");
    }
}
