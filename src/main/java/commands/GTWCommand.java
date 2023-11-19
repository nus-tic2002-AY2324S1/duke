package commands;

import joshua.JoshuaUi;
import joshua.TaskList;
import storage.Storage;

/**
 * Represents a "global thermonuclear war" command.
 */
public class GTWCommand extends Command {
    /**
     * {@inheritDoc} Prints a short response from Joshua in homage to the 1983 movie, WarGames.
     */
    @Override
    public void execute(TaskList taskList, JoshuaUi ui, Storage storage) {
        ui.joshuaSays("A strange game." +
                "\nThe only winning move is" +
                "\nnot to play." +
                "\n\nHow about a nice game of chess?");
    }
}
