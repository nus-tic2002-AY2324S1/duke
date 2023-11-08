package commands;

import storage.Storage;
import joshua.JoshuaUi;
import joshua.TaskList;

public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " - Shows program usage instructions.\n"
            + "\tExample: " + COMMAND_WORD;

    public HelpCommand() {}

    @Override
    public void execute(TaskList taskList, JoshuaUi ui, Storage storage) {
        ui.joshuaSays(
                "List of available commands:\n"
                + "\tLIST\n"
                + "\tMARK #\n"
                + "\tUNMARK #\n"
                + "\tTODO\n"
                + "\tDEADLINE /BY\n"
                + "\tEVENT /FROM /TO\n"
                + "\tDELETE\n"
                + "\tBYE\n"
                        // + "\n" + HelpCommand.MESSAGE_USAGE
                + "\tGLOBAL THERMONUCLEAR WAR");
    }
}
