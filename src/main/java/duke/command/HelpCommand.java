package duke.command;


import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.UI;

public class HelpCommand extends Command {

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        StringBuilder helpMessage = new StringBuilder();
        helpMessage.append("Here are some commands you can use:\n");
        helpMessage.append("list - Displays all tasks in your list. Usage: list\n");
        helpMessage.append("todo <description> - Adds a new todo task. Usage: todo read book\n");
        helpMessage.append("deadline <description> /by <datetime> - Adds a new deadline task. Usage: return book /by 2/12/2019 1800\n");
        helpMessage.append("event <description> /from <date> /to <date> - Adds a new event task. Usage: event orientation week /from 4/10/2019 /to 11/10/2019\n");
        helpMessage.append("delete <task number> - Deletes a task from the list. Usage: delete 3\n");
        helpMessage.append("mark <task number> - Marks a task as done. Usage: mark 1\n");
        helpMessage.append("unmark <task number> - Unmarks a task as not done. Usage: unmark 1\n");
        helpMessage.append("find <keyword> - Finds tasks containing the keyword. Usage: find book\n");
        helpMessage.append("bye - Exits the application. Usage: bye\n");
        helpMessage.append("help - Displays this help message. Usage: help\n");

        UI.showMessage(helpMessage.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean isChangingState() {
        return false;
    }
}
