package commands.add;

import commands.Command;
import constants.ErrorMessages;
import constants.RegExp;
import exceptions.DukeException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.UI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ToDoCommand extends Command {
    String description;

    public ToDoCommand(String line) throws DukeException {
        Pattern pattern = Pattern.compile(RegExp.TODO_COMMAND_FORMAT_REGEX);
        Matcher matcher = pattern.matcher(line);

        if (!matcher.matches()) {
            throw new DukeException(ErrorMessages.INVALID_TODO_FORMAT);
        }

        this.description = line.split(RegExp.SPACE_DELIMITER, 2)[1].trim();
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        int currentSize = tasks.getTotalTasks();
        Task t = tasks.addToDo(description);
        assert tasks.getTotalTasks() == currentSize + 1 : "todo within period should be added successfully";
        ui.showTaskAdded(t);
    }
}
