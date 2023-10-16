package command;

import io.CrabyMessage;
import task.Task;

import java.util.Arrays;
import java.util.List;

import static task.Craby.stackInput;
import static task.Craby.stackTaskList;

public class UndoCommand extends CrabyMessage implements CommandInterface {
    @Override
    public short handleCommand(String input, List<Task> tasks) {
        if (stackTaskList.isEmpty() || stackInput.isEmpty()) {
            printUndoError();
            return 0;
        }
        String command = stackInput.pop().toLowerCase();
        String customizeMessage = "add - ";
        if(command.contains("delete") || command.contains("mark") || command.contains("sort")) {
            customizeMessage = " ";
        }
        List<Task> prevTask = stackTaskList.peek();
        stackTaskList.pop();
        tasks.clear();
        tasks.addAll(prevTask);
        printUndoMessage(command, customizeMessage);
        return 0;
    }
}

