package command;

import io.CrabyMessage;
import task.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;


public class UndoCommand extends CrabyMessage implements CommandInterface {
    public static Stack<List<Task>> stackTaskList = new Stack<>();
    public static Stack<String> stackInput = new Stack<>();
    private static final List<String> NOT_ALLOWED_UNDO = Arrays.asList("list", "find", "help", "undo", "blah");

    private static boolean isPutInStack(String input) {
        String[] inputArr = input.split(" ");
        input = inputArr[0].trim().toLowerCase();
        return NOT_ALLOWED_UNDO.contains(input);
    }

    public static void putInToStack(String input, List<Task> tasks) {
        if (!isPutInStack(input)) {
            List<Task> cloneTasks = new ArrayList<>();
            for (Task task : tasks) {
                cloneTasks.add(task.clone());
            }
            stackTaskList.push(cloneTasks);
            stackInput.push(input);
        }
    }

    @Override
    public void handleCommand(String input, List<Task> tasks) {
        assert input != null;
        assert tasks != null;

        if (stackTaskList.isEmpty() || stackInput.isEmpty()) {
            printUndoError();
            return;
        }
        String command = stackInput.pop().toLowerCase();
        String customizeMessage = "add - ";
        if (command.contains("delete") || command.contains("mark") || command.contains("sort")) {
            customizeMessage = " ";
        }
        List<Task> prevTask = stackTaskList.peek();
        stackTaskList.pop();
        tasks.clear();
        tasks.addAll(prevTask);
        printUndoMessage(command, customizeMessage);
    }
}

