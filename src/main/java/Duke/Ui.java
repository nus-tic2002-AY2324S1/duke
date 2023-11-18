package Duke;

import Duke.TaskClasses.Task;

import java.util.Scanner;

public class Ui {

    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        String logo = "    ____                       __\n" +
                "   / __ \\__  ______  ___  ____/ /\n" +
                "  / / / / / / / __ \\/ _ \\/ __  /\n" +
                " / /_/ / /_/ / /_/ /  __/ /_/ /\n" +
                "/_____/\\__,_/ .___/\\___/\\__,_/\n" +
                "           /_/";
        System.out.println("Hello! I'm DupeBot.\n" + logo);
        System.out.println("What can I do for you?");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showError(String message) {
        System.out.println("OOPS!!! " + message);
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void displayTasks(Task[] tasks, int taskCounter) {
        showLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCounter; i++) {
            System.out.println((i + 1) + ". " + tasks[i].toString());
        }
        showLine();
    }
}
