package nus.duke.commands;

import java.time.LocalDateTime;
import java.util.Optional;
import nus.duke.data.TaskAfterOption;
import nus.duke.data.TaskList;
import nus.duke.data.TaskOptionKey;
import nus.duke.data.TaskSource;
import nus.duke.data.tasks.Deadline;
import nus.duke.exceptions.DukeException;
import nus.duke.exceptions.InvalidCommandArgsDukeException;
import nus.duke.parser.Parser;
import nus.duke.storage.Storage;

/**
 * The `DeadlineCommand` class represents a command to add a new deadline task.
 * When executed, it parses user input to create a deadline task with a description
 * and a specific due datetime, then adds the task to the task list.
 */
public class DeadlineCommand extends AbstractTaskCommand {
    /**
     * Instantiates a new `DeadlineCommand` with the provided arguments.
     *
     * @param args The user input containing the description and the due datetime of the deadline task.
     */
    public DeadlineCommand(String args) {
        super(args);
    }

    @Override
    public String[] execute(TaskList tasks, Storage storage) throws DukeException {
        assert tasks != null;
        assert storage != null;

        if (args.isEmpty()) {
            throw new InvalidCommandArgsDukeException("The description of a deadline cannot be empty.");
        }

        TaskSource taskSource = Parser.parseTaskSource(args);
        Optional<String> byOption = taskSource.getOptionValue(TaskOptionKey.BY);
        if (byOption.isEmpty()) {
            throw new InvalidCommandArgsDukeException(
                String.format("The \"/%s {datetime}\" of a deadline is required.", TaskOptionKey.BY));
        }

        Optional<TaskAfterOption> optionalAfterOption = getAfterOption(tasks, taskSource);
        LocalDateTime by = Parser.parseUserDateTime(byOption.get());
        Deadline deadline = new Deadline(taskSource.getDescription(), by);
        optionalAfterOption.ifPresent(deadline::setAfterOption);
        tasks.addTask(deadline);
        storage.save(tasks);
        return getTaskAddedMessages(tasks);
    }
}
