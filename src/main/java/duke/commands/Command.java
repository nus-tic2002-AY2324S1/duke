package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UI;

/**
 * The base class for command execution.
 *
 * Stores user input and provides a mechanism to check if the user
 * wishes to exit the program. Subclasses are expected to provide their 
 * own implementation of the {@code execute} method to define specific 
 * command behavior.</p>
 */

public class Command {
    protected boolean isExit = false;
    protected String input;


    /**
     * {@code execute} method defines the behaviour of respective user command.
     * <p>
     * @param tasks user's current TaskList.
     * @param ui ui object that handles printing of statements.
     * @param storage storage object to save/store user input.
     */
    public void execute(TaskList tasks, UI ui, Storage storage){
    }

    public Boolean isExit(){
        return this.isExit;
    }
}
