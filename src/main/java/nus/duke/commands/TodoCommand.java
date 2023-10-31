package nus.duke.commands;

import java.util.Optional;
import nus.duke.data.TaskAfterOption;
import nus.duke.data.TaskList;
import nus.duke.data.TaskSource;
import nus.duke.data.tasks.Todo;
import nus.duke.exceptions.DukeException;
import nus.duke.exceptions.InvalidCommandArgsDukeException;
import nus.duke.parser.Parser;
import nus.duke.storage.Storage;
import nus.duke.ui.Ui;

/**
 * The `TodoCommand` class represents a command to add a new "todo" task.
 * When executed, this command validates the user input, creates a todo task with a description,
 * and adds it to the task list, providing confirmation messages.
 */
public class TodoCommand extends AbstractTaskCommand {
    /**
     * Instantiates a new `TodoCommand` with the provided arguments.
     *
     * @param args The user input containing the description of the todo task.
     */
    public TodoCommand(String args) {
        super(args);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert tasks != null;
        assert ui != null;
        assert storage != null;

        if (args.isEmpty()) {
            throw new InvalidCommandArgsDukeException("The description of a todo cannot be empty.");
        }

        TaskSource taskSource = Parser.parseTaskSource(args);
        Optional<TaskAfterOption> optionalAfterOption = getAfterOption(tasks, taskSource);
        Todo todo = new Todo(taskSource.getDescription());
        optionalAfterOption.ifPresent(todo::setAfterOption);
        tasks.addTask(todo);
        storage.save(tasks);
        ui.showMessages(getTaskAddedMessages(tasks));
    }
}
