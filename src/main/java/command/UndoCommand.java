package command;

import io.CrabyMessage;
import task.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * UndoCommand class is a class that handle the undo command.
 * It implements the CommandInterface.
 * It has a method to handle the undo command.
 */
public class UndoCommand extends CrabyMessage implements CommandInterface {
    private static Stack<List<Task>> stackTaskList = new Stack<>();
    private static Stack<String> stackInput = new Stack<>();
    private static final List<String> NOT_ALLOWED_UNDO = Arrays.asList("list", "find", "help", "undo", "blah");

    /**
     * @inheritDoc
     * Sends the undo message to the user and undo the previous command.
     */
    @Override
    public void handleCommand(String input, List<Task> tasks) {
        assert input != null;
        assert tasks != null;

        if (stackTaskList.isEmpty() || stackInput.isEmpty()) {
            printUndoError();
            return;
        }
        String command = stackInput.pop().toLowerCase();
        List<Task> prevTask = stackTaskList.peek();
        stackTaskList.pop();
        tasks.clear();
        tasks.addAll(prevTask);
        printUndoMessage(command);
    }

    /**
     * This method will check if the input is allowed to undo or not.
     * If it is allowed, it will return true.
     * Otherwise, it will return false.
     *
     */
     private static boolean isPutInStack(String input) {
        String[] inputArr = input.split(" ");
        input = inputArr[0].trim().toLowerCase();
        return NOT_ALLOWED_UNDO.contains(input);
    }

    /**
     * Adds the input and the list of tasks into the stack.
     * This method will check if the input is allowed to undo.
     * If it is allowed, it will push the input and the list of tasks into the stack.
     * Otherwise, it will not push the input and the list of tasks into the stack.
     *
     */
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

}

