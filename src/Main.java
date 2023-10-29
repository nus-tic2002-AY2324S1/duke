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
            }
            else if (userInput.equalsIgnoreCase("list")) {
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
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskCounter; i++) {
                System.out.println((i + 1) + ". " + tasks[i].toString());
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

    private static void addTask(String userInput) {
        if (taskCounter < MAX_TASKS) {
            String[] inputParts = userInput.split(" ", 2);

            if (inputParts.length < 2) {
                if (!inputParts[0].equals("todo") && !inputParts[0].equals("deadline") && !inputParts[0].equals("event")) {
                    try {
                        throw new UnknownCommandException();
                    } catch (UnknownCommandException e) {
                        System.out.println(e.getMessage());
                    }
                    return;
                }

                try {
                    throw new EmptyDescriptionException();
                } catch (EmptyDescriptionException e) {
                    System.out.println(e.getMessage());
                    return;
                }
            }

            String taskType = inputParts[0].toLowerCase();
            String description = inputParts[1].trim();

            try {
                Task task = null;

                switch (taskType) {
                    case "todo":
                        if (description.isEmpty()) {
                            throw new EmptyDescriptionException();
                        }
                        task = new ToDo(description);
                        break;
                    case "deadline":
                        String by = extractBy(description);
                        String actualDescriptionDeadline = extractDescription(description);
                        if (by.isEmpty() || actualDescriptionDeadline.isEmpty()) {
                            throw new IncompleteDataException(); // Handle incomplete data
                        }
                        task = new Deadline(actualDescriptionDeadline, by);
                        break;
                    case "event":
                        String from = extractFrom(description);
                        String to = extractTo(description);
                        String actualDescriptionEvent = extractDescription(description);
                        if (from.isEmpty() || to.isEmpty() || actualDescriptionEvent.isEmpty()) {
                            throw new IncompleteDataException(); // Handle incomplete data
                        }
                        task = new Event(actualDescriptionEvent, from, to);
                        break;
                    default:
                        throw new UnknownCommandException();
                }

                tasks[taskCounter] = task;
                taskCounter++;
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + task.toString());
                System.out.println("Now you have " + taskCounter + " tasks in the list.");
            } catch (DupeException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Sorry, your task list is full.");
        }
    }

    private static String extractBy(String description) {
        //extract string after /by
        int byIndex = description.indexOf("/by");
        if (byIndex != -1) {
            return description.substring(byIndex + 3).trim();
        }
        return ""; // Return an empty string if no "/by" information is found
    }

    private static String extractFrom(String description) {
        //extract string after /from
        int fromIndex = description.indexOf("/from");
        if (fromIndex != -1) {
            return description.substring(fromIndex + 5, description.indexOf("/to")).trim();
        }
        return ""; // Return an empty string if no "/from" information is found
    }

    private static String extractTo(String description) {
        //extract string after /to
        int toIndex = description.indexOf("/to");
        if (toIndex != -1) {
            return description.substring(toIndex + 3).trim();
        }
        return ""; // Return an empty string if no "/to" information is found
    }

    private static String extractDescription(String description) {
        //extract description for userinputs that have more fields
        int toIndex = description.indexOf("/");
        if (toIndex != -1) {
            String[] splitDescription = description.split("/", 2);
            return splitDescription[0];
        }
        return description; // Return an empty string if no "/to" information is found
    }

}

