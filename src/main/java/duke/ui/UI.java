package duke.ui;

import java.util.ArrayList;
import java.util.Scanner;

import duke.tasks.Task;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * Object of class UI gives access to methods which offers interactions to the user via the command
 * line. This class offers methods for various interactions, including:
 * <ul>
 * <li>{@code greet()}: Displays a greeting message.</li>
 * <li>{@code readCommand()}: Reads and retrieves a command from the input.</li>
 * <li>{@code help()}: Displays informative content or instructions.</li>
 * <li>{@code killUI()}: Terminates the UI object, concluding the interaction.</li>
 * </ul>
 */

public class UI {

    Scanner in;
    String userInput;

    public UI() {
        in = new Scanner(System.in);
    }

    public void greet() {
        String botName = "Angel";
        System.out.println(
                "Hello! I'm " + botName + " your personal friendly to-do list tracker bot.");
        System.out.println("I accept Deadlines, Events and simple To-Dos. Here's a mini manual!");
        System.out.println();
        help();
        System.out.println("What can I do for you?" + "\n");
    }

    public void showLine() {
        System.out.println("-------------------------------");
    }

    public String readCommand() {
        return in.nextLine().trim();
    }

    public void help() {
        System.out.println("To Add:");
        System.out.println("(a) A Deadline: deadline return book /by 2023-10-06");
        System.out.println("(b) An Event: event project meeting /from 2023-10-05 /to 2023-10-06");
        System.out.println("(c) A To-Do: todo borrow book");
        System.out.println("To List all items in your list: list");
        System.out.println(
                "To Mark or Unmark your tasks, type: (a) mark 'task no.' or (b) unmark 'task no' ");
        System.out.println("To Delete your tasks, type: delete 'task no.' ");
        System.out.println();
    }

    public void showLoadingError() {
        System.out.println(
                "Uh Oh! Something went wrong with loading your previous tasks. Let's start fresh!");
    }

    public void killUI() {
        in.close();
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void showTaskAdded(Task task) {
        System.out.println("Got it. I've added this task: \n" + task);
    }

    public void showMarkedTask(Task task) {
        System.out.println("Nice! I've marked this task: \n" + task);
    }

    public void showTaskPrioritySet(Task task) {
        System.out.println("Successfully set " + task.getDescription() + " to " + task.getPriority()
                + " Priority.");
    }

    public void showTasksDue(ArrayList<Task> tasks, LocalDate dueDate) {
        Period days = LocalDate.now().until(dueDate);

        if (tasks.size() > 0) {
            System.out.println("There are " + days.getDays() + " day(s) left until "
                    + dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
            for (Task t : tasks) {
                System.out.println(t);
            }
        } else {
            System.out.println("You have nothing due on "
                    + dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        }
    }
}
