package nus.duke.commands;

import nus.duke.data.TaskList;
import nus.duke.exceptions.DukeException;
import nus.duke.storage.Storage;

/**
 * Represents a command to search for tasks based on a given criteria.
 */
public class HelpCommand extends AbstractCommand {

    /**
     * Creates a FindCommand with the specified search criteria.
     *
     * @param args The search criteria provided by the user.
     */
    public HelpCommand(String args) {
        super(args);
    }

    @Override
    public String[] execute(TaskList tasks, Storage storage) throws DukeException {
        assert tasks != null;
        assert storage != null;

        return new String[] {
            "deadline /by {datetime} [/after {datetime/task-number}]",
            "    Create a deadline task with the specified due datetime and an optional dependency.",
            "",
            "event /from {datetime} /to {datetime} [/after {datetime/task-number}]",
            "    Create an event task with the specified start and end datetime, and an optional dependency.",
            "",
            "todo [/after {datetime/task-number}]",
            "    Create a todo task with an optional dependency.",
            "",
            "delete {task-number}",
            "    Delete the task with the given task number.",
            "",
            "mark {task-number}",
            "    Mark the task with the given task number as complete.",
            "",
            "unmark {task-number}",
            "    Mark the task with the given task number as incomplete.",
            "",
            "list",
            "    Show all tasks.",
            "",
            "find {criteria}",
            "    Display tasks with descriptions containing the specified criteria.",
            "",
            "date {date}",
            "    Show tasks scheduled on the given date.",
            "",
            "bye",
            "    Display the farewell message.",
            "",
            "help",
            "    Display this help message."
        };
    }
}
