// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    private static final int MAX_TASKS = 100;
    private static Task[] tasks = new Task[MAX_TASKS];
    private static int taskCounter = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Greet the user
        String logo = "    ____                       __\n" +
                "   / __ \\__  ______  ___  ____/ /\n" +
                "  / / / / / / / __ \\/ _ \\/ __  /\n" +
                " / /_/ / /_/ / /_/ /  __/ /_/ /\n" +
                "/_____/\\__,_/ .___/\\___/\\__,_/\n" +
                "           /_/";
        System.out.println("Hello! I'm DupeBot.\n" + logo);
        System.out.println("What can I do for you?");


        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                displayTasks();
            } else if (userInput.startsWith("mark ")) {
                markTaskAsDone(userInput);
            } else if (userInput.startsWith("unmark ")) {
                unmarkTask(userInput);
            } else {
                addTask(userInput);
            }
        }

        scanner.close();
    }

    private static void displayTasks() {
        if (taskCounter == 0) {
            System.out.println("The task list is currently empty");
        } else {
            for (int i = 0; i < taskCounter; i++) {
                Task task = tasks[i];
                System.out.println((i + 1) + " " + task.getStatusIcon() + " " + task.getDescription());
            }
        }
    }

    private static void markTaskAsDone(String userInput) {
        int taskIndex = getTaskIndex(userInput);
        if (taskIndex >= 1 && taskIndex <= taskCounter) {
            Task task = tasks[taskIndex - 1];
            task.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + task.getStatusIcon() + " " + task.getDescription());
        } else {
            System.out.println("Invalid task number.");
        }
    }

    private static void unmarkTask(String userInput) {
        int taskIndex = getTaskIndex(userInput);
        if (taskIndex >= 1 && taskIndex <= taskCounter) {
            Task task = tasks[taskIndex - 1];
            task.markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + task.getStatusIcon() + " " + task.getDescription());
        } else {
            System.out.println("Invalid task number.");
        }
    }

    private static int getTaskIndex(String userInput) {
        String[] parts = userInput.split(" ");
        if (parts.length == 2) {
            try {
                return Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                // Handle the case where the user didn't provide a valid number.
            }
        }
        return -1; // Invalid input
    }

    private static void addTask(String description) {
        if (taskCounter < MAX_TASKS) {
            Task task = new Task(description);
            tasks[taskCounter] = task;
            taskCounter++;
            System.out.println("Added: " + description);
        } else {
            System.out.println("Sorry, your task list is full.");
        }
    }
}

