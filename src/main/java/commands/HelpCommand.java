package commands;

import storage.Storage;
import joshua.JoshuaUi;
import joshua.TaskList;

/**
 * Represents a help command.
 */
public class HelpCommand extends Command {
    public HelpCommand() {}

    /**
     * {@inheritDoc} Prints a list of available commands the user can input.
     */
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
                + "\tFIND\n"
                + "\tSCHEDULE\n"
                + "\tDELETE #\n"
                + "\tHELP\n"
                + "\tBYE\n"
                + "\tGLOBAL THERMONUCLEAR WAR");
    }
}
