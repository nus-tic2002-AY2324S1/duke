package Duke;
// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import Duke.ExceptionClasses.DupeException;
import Duke.ExceptionClasses.EmptyDescriptionException;
import Duke.ExceptionClasses.IncompleteDataException;
import Duke.ExceptionClasses.UnknownCommandException;

import Duke.TaskClasses.*;


import java.time.LocalDateTime;
import java.util.ArrayList;

public class Dupe {

    private static ArrayList<Task> tasks = new ArrayList<>();
    private static Ui ui = new Ui();
    private static final String FILE_PATH = "data/dupe.txt"; // adjust the file path as needed
    private static Storage storage = new Storage(FILE_PATH);

    public static void main(String[] args) throws DupeException {
        try {
            tasks = storage.load();
        } catch (DupeException e) {
            // Handle the exception (e.g., show an error message)
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        // Greet the user
        ui.showWelcome();


        while (true) {
            String userInput = ui.readCommand();
            ui.showLine();
            if (userInput.equalsIgnoreCase("bye")) {
                ui.showBye();
                break;
            }
            else if (userInput.toLowerCase().startsWith("list")) {
                displayTasks();
            } else if (userInput.toLowerCase().startsWith("mark ")) {
                markTaskAsDone(userInput);
            } else if (userInput.toLowerCase().startsWith("unmark ")) {
                unmarkTask(userInput);
            } else if(userInput.toLowerCase().startsWith("delete")){
                deleteTask(userInput);
            } else if (userInput.toLowerCase().startsWith("recur")) {
                handleRecurringEvent(userInput);
            } else {
                addTask(userInput);
            }
        }
    }

    private static void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("The task list is currently empty");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i).toString());
            }
        }
    }

    private static void markTaskAsDone(String userInput) throws DupeException {
        int taskIndex = getTaskIndex(userInput);
        if (taskIndex >= 1 && taskIndex <= tasks.size()) {
            Task task = tasks.get(taskIndex - 1);
            task.markAsDone();
            storage.save(tasks);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + task.getStatusIcon() + " " + task.getDescription());
        } else {
            System.out.println("Invalid task number.");
        }
    }

    private static void unmarkTask(String userInput) throws DupeException {
        int taskIndex = getTaskIndex(userInput);
        if (taskIndex >= 1 && taskIndex <= tasks.size()) {
            Task task = tasks.get(taskIndex - 1);
            task.markAsNotDone();
            storage.save(tasks);
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
                System.out.println("Invalid task number format.");
            }
        }
        return -1; // Invalid input
    }

    private static void addTask(String userInput) {

            String[] inputParts = userInput.split(" ", 2);

            if (inputParts.length < 2) {
                //throw error if the input is not the following
                if (!inputParts[0].equals("todo") && !inputParts[0].equals("deadline") && !inputParts[0].equals("event")) {
                    try {
                        throw new UnknownCommandException();
                    } catch (UnknownCommandException e) {
                        System.out.println(e.getMessage());
                    }
                    return;
                }
                //else throw error if there's no description
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

                tasks.add(task);
                storage.save(tasks); //save to file after a new task is added
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + task.toString());
                System.out.println("Now you have " + (tasks.size()) + " tasks in the list.");

            } catch (DupeException e) {
                System.out.println(e.getMessage());
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
        return description;
    }
    private static void deleteTask(String userInput) {
        if (tasks.isEmpty()) {
            System.out.println("The task list is currently empty. Nothing to delete.");
            return;
        }

        // Parse the user input to extract the task index to delete
        String[] inputParts = userInput.split(" ");
        if (inputParts.length != 2) {
            System.out.println("Invalid delete command. Use 'delete [task index]'.");
            return;
        }

        try {
            int taskIndex = Integer.parseInt(inputParts[1]);
            if (taskIndex < 1 || taskIndex > tasks.size()) {
                System.out.println("Duke.TaskClasses.Task index is out of range.");
            } else {
                // Remove the task from the list
                Task deletedTask = tasks.remove(taskIndex - 1);
                storage.save(tasks);

                System.out.println("Noted. I've removed this task:");
                System.out.println("  " + deletedTask.toString());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid task index. Please enter a valid number.");
        }
        catch (DupeException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    private static void handleRecurringEvent(String userInput) {
        String[] words = userInput.trim().split("\\s+");
        if (words.length == 4) {
            try {
                int taskNumber = Integer.parseInt(words[1]);
                String recurrenceType = words[2].toUpperCase(); // Convert to uppercase
                int recurrenceCount = Integer.parseInt(words[3]);

                // Call the corresponding method to add recurring event based on the parsed information
                addRecurringEvent(taskNumber, Recurrence.valueOf(recurrenceType), recurrenceCount);
            } catch (NumberFormatException e) {
                System.err.println("Invalid number format in the input. Please provide valid numbers.");
            } catch (IllegalArgumentException e) {
                System.err.println("Invalid recurrence type. Please provide a valid recurrence type (e.g., DAILY, WEEKLY, BIWEEKLY, MONTHLY).");
            }
        } else {
            System.err.println("Invalid input format. Please use the format: recur <taskNumber> <recurrenceType> <recurrenceInterval> <recurrenceCount>");
        }
        // Call the corresponding method to add recurring event based on the parsed information
        // For example, if userInput is "recur 2 biweekly 4", you might extract taskNumber=2, recurrenceType=BIWEEKLY, recurrenceInterval=2, recurrenceCount=4
        // Then call addRecurringEvent method with these parameters
    }

    private static void addRecurringEvent(int taskNumber, Recurrence recurrenceType, int recurrenceCount) {
        // Retrieve the task from the tasks list based on taskNumber
        Task eventTask = tasks.get(taskNumber - 1);

        if (eventTask instanceof Event) {
            for (int i = 0; i < recurrenceCount; i++) {
                LocalDateTime originalFrom = ((Event) eventTask).getFromDateTime();
                LocalDateTime originalTo = ((Event) eventTask).getToDateTime();

                LocalDateTime newFrom, newTo;

                switch (recurrenceType) {
                    case DAILY:
                        newFrom = originalFrom.plusDays(1 * (i + 1));
                        newTo = originalTo.plusDays(1 * (i + 1));
                        break;
                    case WEEKLY:
                        newFrom = originalFrom.plusWeeks(7 * (i + 1));
                        newTo = originalTo.plusWeeks(7 * (i + 1));
                        break;
                    case BIWEEKLY:
                        newFrom = originalFrom.plusWeeks(2 * 7 * (i + 1));
                        newTo = originalTo.plusWeeks(2 * 7 * (i + 1));
                        break;
                    case MONTHLY:
                        newFrom = originalFrom.plusMonths(1 * (i + 1));
                        newTo = originalTo.plusMonths(1 * (i + 1));
                        break;
                    default:
                        throw new IllegalArgumentException("Unsupported recurrence type");
                }

                // Create a new task with the same properties as the original task
                Task recurringTask = new Event(eventTask.getDescription(), newFrom, newTo);

                // Add the recurring task to the tasks list
                tasks.add(recurringTask);
            }

            System.out.println("Recurring event added successfully!");
        } else {
            System.err.println("Task " + taskNumber + " is not an event and cannot have a recurrence.");
        }
    }

}

