package nus.duke.commands;

import nus.duke.data.TaskAfterOption;
import nus.duke.data.TaskList;
import nus.duke.data.TaskOptionKey;
import nus.duke.data.TaskSource;
import nus.duke.exceptions.InvalidCommandArgsDukeException;
import nus.duke.parser.Parser;
import nus.duke.util.IntegerUtils;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * The `AbstractTaskCommand` class serves as the base class for commands that create tasks.
 */
public abstract class AbstractTaskCommand extends AbstractCommand {
    /**
     * Instantiates a new `AbstractTaskCommand` with the provided arguments.
     *
     * @param args The user input arguments.
     */
    public AbstractTaskCommand(String args) {
        super(args);
    }

    /**
     * Generates messages to confirm the addition of a task and provide task count information.
     *
     * @param tasks The task list containing the recently added task.
     * @return An array of strings representing messages confirming the added task and task count.
     */
    protected static String[] getTaskAddedMessages(TaskList tasks) {
        assert tasks != null;

        return new String[]{
                "Got it. I've added this task:",
                "  " + tasks.getLastTask(),
                String.format("Now you have %d tasks in the list.", tasks.size())
        };
    }

    /**
     * Retrieves the task after option, if provided in the user input.
     *
     * @param tasks      The task list containing the tasks.
     * @param taskSource The source of the task input.
     * @return An optional `TaskAfterOption` representing the task after option, or empty if not provided.
     * @throws InvalidCommandArgsDukeException If the task after option is invalid.
     */
    protected static Optional<TaskAfterOption> getAfterOption(TaskList tasks, TaskSource taskSource) throws InvalidCommandArgsDukeException {
        assert tasks != null;
        assert taskSource != null;

        Optional<String> afterOption = taskSource.getOptionValue(TaskOptionKey.AFTER);
        if (afterOption.isEmpty()) {
            return Optional.empty();
        }

        String afterOptionValue = afterOption.get();
        Integer afterTaskNumber = IntegerUtils.tryParseInt(afterOptionValue);
        if (afterTaskNumber != null) {
            if (afterTaskNumber < 1 || afterTaskNumber > tasks.size()) {
                throw new InvalidCommandArgsDukeException(
                        String.format("The task number of \"/%s\" is invalid.", TaskOptionKey.AFTER));
            }
            return Optional.of(new TaskAfterOption(afterTaskNumber));
        }

        LocalDateTime afterDateTime = Parser.parseUserDateTime(afterOptionValue);
        return Optional.of(new TaskAfterOption(afterDateTime));
    }
}
