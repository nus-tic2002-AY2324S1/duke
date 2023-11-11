package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

public class UI {
    private static Scanner scanner;

    public UI() {
        scanner = new Scanner(System.in);
    }

    public static void showLine() {
        System.out.println("----".repeat(10));
    }

    public static void showMessage(String message) {
        System.out.println(message);
    }

    public static String readCommand() {
        return scanner.nextLine().trim();
    }

    public static void showWelcome() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        showLine();
        showMessage(logo);
        showMessage("Hello! I am  BotGenius, your chatBot.");
        showMessage("What can I do for you?");
        showLine();
    }

    public static void showLoadingError() {
        showMessage("Error: Unable to load task data. Please check the data file.");
        showLine();
    }

    public static void showError(String errorMessage) {
        showMessage("Error: " + errorMessage);
    }

    public static void showBye() {
        showMessage("Bye. Hope to see you again soon!");
    }

    public static void showUndoSuccess() {
        showMessage("Undo the last action successful");
    }

    public static void showUndoFail() {
        showMessage("No more actions to undo");
    }

    public static void showOOPS() {
        showMessage("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public static void showTaskDeleted(Task deletedTask, TaskList taskList) {
        UI.showMessage("Noted. I've removed this task:");
        UI.showMessage(deletedTask.getStatusIcon() + deletedTask.getDescription());
        System.out.printf("Now you have %d tasks in the list.\n", taskList.getTaskList().size());
    }

    public static void showTaskMarkedAsDone(Task task) {
        UI.showMessage("Nice! I've marked this task as done:");
        UI.showMessage(task.getStatusIcon() + task.getDescription());
    }

    public static void showTaskUnmarkedAsNotDone(Task task) {
        UI.showMessage("OK, I've marked this task as not done yet:");
        UI.showMessage(task.getStatusIcon() + task.getDescription());
    }

    public static void showNewTask(Task task, TaskList taskList) {
        UI.showMessage("Got it. I've added this task:");
        UI.showMessage(task.getStatusIcon() + " " + task.getDescription());
        UI.showMessage(String.format("Now you have %d tasks in the list.", taskList.getTaskList().size()));
    }

    public static void showSearchResults(TaskList foundTasks) {
        if (!foundTasks.getTaskList().isEmpty()) {
            int idx = 1;
            UI.showMessage("Here are the matching tasks in your list:");
            for (Task task : foundTasks.getTaskList()) {
                UI.showMessage(idx + ". " + task.getStatusIcon() + " " + task.getDescription());
                idx++;
            }
        } else {
            UI.showMessage("No matching result found");
        }
    }
}
