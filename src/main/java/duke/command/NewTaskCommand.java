package duke.command;

import duke.Utils;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.TaskList;
import duke.task.Todos;
import duke.ui.UI;

import java.io.IOException;

public class NewTaskCommand extends Command{
    String fullCommand;
    public NewTaskCommand(String fullCommand) { this.fullCommand =fullCommand; }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException, IOException {

        if (fullCommand.toLowerCase().contains("todo")){
            Todos todoTask = Utils.newTodoTask(fullCommand);
            taskList.addTask(todoTask);
            storage.save(taskList);
            UI.showNewTask(todoTask, taskList);
        } else if (fullCommand.toLowerCase().contains("deadline")) {
            Deadlines deadlineTask = Utils.newDeadlineTask(fullCommand);
            taskList.addTask(deadlineTask);
            storage.save(taskList);
            UI.showNewTask(deadlineTask, taskList);
        } else {
            Events eventTask = Utils.newEventTask(fullCommand);
            taskList.addTask(eventTask);
            storage.save(taskList);
            UI.showNewTask(eventTask, taskList);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
