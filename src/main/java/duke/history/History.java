package duke.history;

import duke.task.TaskList;

import java.util.Stack;

public class History {
    private static final Stack<TaskList> historyStack = new Stack<>();


    /**
     * Saves the current state of the task list to history.
     * This method is called after executing a command that changes the task list.
     * It ensures that a maximum of 10 previous states are retained to limit memory usage.
     *
     * @param currentList The current state of the task list to be saved.
     */
    public static void saveHistory(TaskList currentList) {
        TaskList copiedTaskList = currentList.copyTaskList();
        historyStack.push(copiedTaskList);
        if (historyStack.size() > 10) {
            historyStack.remove(0);
        }
    }

    /**
     * Undoes the most recent change to the task list by popping the last state from the history stack.
     * If there are no previous states, it returns null, indicating there is nothing to undo.
     *
     * @return The previous state of the task list, or null if there is none.
     */
    public static TaskList undo() {
        if (!historyStack.isEmpty()) {
            return historyStack.pop();
        }
        return null;
    }

}
