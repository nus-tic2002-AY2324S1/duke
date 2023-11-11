package duke.history;

import duke.task.TaskList;

import java.util.Stack;

public class History {
    private static final Stack<TaskList> historyStack = new Stack<>();


    public static void saveHistory(TaskList currentList) {

        TaskList copiedTaskList = currentList.copyTaskList();
        historyStack.push(copiedTaskList);
        if (historyStack.size() > 10) {
            historyStack.remove(0);
        }
    }

    public static TaskList undo() {
        if (!historyStack.isEmpty()) {
            return historyStack.pop();
        }
        return null;
    }

}
