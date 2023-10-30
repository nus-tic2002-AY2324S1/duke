package duke;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import duke.command.TaskType; 
import duke.error.ErrorType;
import duke.ui.UI;
import duke.storage.Storage;
import duke.tasklist.TaskList;

import duke.task.Events;
import duke.task.Deadlines;
import duke.task.ToDos;
import duke.task.Task;

public class Duke {
    private UI ui;
    private Storage storage;
    private TaskList taskList;

    private static final Map<TaskType, String> TASK_KEYWORDS = new HashMap<>();

    static {
        TASK_KEYWORDS.put(TaskType.TODO, "todo");
        TASK_KEYWORDS.put(TaskType.DEADLINE, "deadline");
        TASK_KEYWORDS.put(TaskType.EVENT, "event");
        TASK_KEYWORDS.put(TaskType.DELETE, "delete");
    }

    public Duke() {
        this.ui = new UI();
        this.storage = new Storage();
        this.taskList = new TaskList(storage.loadTasks());
    }

    public void run() {
        ui.printWelcomeMessage();

        Scanner scanner = new Scanner(System.in);
        String input;

        do {
            input = scanner.nextLine().trim();
            processInput(input);
        } while (!input.equalsIgnoreCase("bye"));

        ui.printGoodbyeMessage();
        scanner.close();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    private void processInput(String input) {
        if (input.equalsIgnoreCase("list")) {
            ui.printTaskList(taskList.getList());
            return;
        }
        TaskType taskType = getTaskType(input);
        if (taskType == null) {
            ui.printErrorMessage(ErrorType.ERR_SYSTEM_READ_FAIL, taskType, taskList.getListSize());
            return;
        }

        ErrorType err = hasError(input, taskType);
        if (err != null) {
            ui.printErrorMessage(err, taskType, taskList.getListSize());
            return;
        }

        String taskDescription = input.substring(TASK_KEYWORDS.get(taskType).length() + 1).trim();
        if (taskDescription.isEmpty()) {
            ui.printErrorMessage(ErrorType.ERR_EMPTY_DESCRIPTION, taskType, taskList.getListSize());
            return;
        }
        try {
            if (taskType == TaskType.DELETE) {
                try {
                    int intValue = Integer.parseInt(taskDescription);
                    if (intValue > 0 && intValue <= taskList.getListSize()) {
                        taskList.deleteTask(intValue - 1);
                        storage.saveTasks(taskList.getList()); // Save tasks to file after each change
                    } else {
                        ui.printErrorMessage(ErrorType.ERR_EXCEED_LIMIT, taskType, taskList.getListSize());
                    }
                } catch (NumberFormatException e) {
                    ui.printErrorMessage(ErrorType.ERR_EXPECT_NUMBER, taskType, taskList.getListSize());
                }
            } else {
                taskList.addTask(createTask(taskType, taskDescription));
                storage.saveTasks(taskList.getList()); // Save tasks to file after each change
            }
        } catch (IllegalArgumentException e) {
            ui.printErrorMessage(ErrorType.ERR_INVALID_FORMAT, taskType, taskList.getListSize());
            return;
        }
    }

    private static Task createTask(TaskType taskType, String taskDescription) {
        switch (taskType) {
            case EVENT:
                return new Events(taskDescription);
            case DEADLINE:
                return new Deadlines(taskDescription);
            default:
                return new ToDos(taskDescription);
        }
    }

    private static ErrorType hasError(String input, TaskType t) {
        // for input begins with "[command]"
        String command = TASK_KEYWORDS.get(t);
        if (input.trim().equalsIgnoreCase(command)) {
            return ErrorType.ERR_EMPTY_DESCRIPTION;
        } else if (input.trim().length() > command.length() &&
                !input.substring(command.length(), command.length() + 1).equals(" ")) {
            return ErrorType.ERR_POSSIBLE_TYPO;
        } else {
            return null;
        }
    }

    private static TaskType getTaskType(String input) {
        for (Map.Entry<TaskType, String> entry : TASK_KEYWORDS.entrySet()) {
            if (input.toLowerCase().startsWith(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
}
