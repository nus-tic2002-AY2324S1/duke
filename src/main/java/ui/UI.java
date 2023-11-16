package ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import tasks.Task;

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
        System.out.println(
                "Greetings from the whimisical realm of Duke! ✨ \n\nI'm TaskMaster, your efficient sidekick in the quest for productivity. \nImagine me with a superhero cape – I accept Deadlines, Events, and even the quirkiest To-Dos! \nReady for a mini manual adventure? Brace yourself, here it comes:");
        System.out.println();
        help();
        System.out.println(
                "And now, let the epic task conquering commence! What grand adventure shall we embark on today?\n");
    }

    public void showLine() {
        System.out.println("-------------------------------");
    }

    public String readCommand() {
        return in.nextLine().trim();
    }

    public void help() {
        System.out.println("1. To Summon a Deadline: 'deadline conquer the world /by 2023-12-31'");
        System.out.println(
                "2. To Kickstart an Event: 'event epic brainstorming /from 2023-11-01 /to 2023-11-02'");
        System.out.println("3. To Spur a To-Do: 'todo master the art of juggling'");
        System.out.println(
                "4. To Manifest a To-Do Within a Period: 'todo write a novel /between 2024-01-01 /and 2024-12-31'");
        System.out.println("5. To Unveil the Master List: 'list'");
        System.out.println("6. To Flaunt Your Triumphs: (a) 'mark 3' or (b) 'unmark 3'");
        System.out.println("7. To Banish a Task: 'delete 2'");
        System.out.println("8. To Glimpse Into the Future: 'due 2023-11-30'");
        System.out.println("9. To Unearth Tasks with a Secret: 'find secret'");
        System.out.println("10. To Ascend Task Priorities: 'set 1 to HIGH'. Default is low.");
        System.out.println("11. Feeling lost on your quest? Fear not! To summon guidance, just type 'help'.");
        System.out.println(
                "And there you have it! Now, off to the tasking adventures, TaskMaster awaits your command!");
        System.out.println();


    }

    public void showLoadingError() {
        System.out.println(
                "Oopsie-daisy! It looks like our task teleporter hiccuped while loading your previous quests. No worries, we'll kickstart a fresh adventure!");
    }

    public void killUI() {
        System.out.println(
                "Taskmaster signing off! Your master list is safely stored, so take a break and return when the tasks start missing you. Until then, stay bossing those to-dos! ✨📋 #TaskConquerorOut");
        in.close();

    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void showTaskAdded(Task task) {
        System.out.println("Splendid! I've added this task to the quest board:\n" + task);
    }

    public void showMarkedTask(Task task) {
        System.out.println("Marvelous! I've marked this task as conquered:\n" + task);
    }

    public void showTaskPrioritySet(Task task) {
        System.out.println("Successfully awarded the mighty title of '" + task.getDescription() + "' with "
                + task.getPriority() + " Priority.");
    }

    public void showTasksDue(ArrayList<Task> tasks, LocalDate dueDate) {

        if (tasks.size() > 0) {
            System.out.println("Ahoy! Here are the events and deadlines scheduled for  "
                    + dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
            for (Task t : tasks) {
                System.out.println(t);
            }
        } else {
            System.out.println(
                    "No quests scheduled on " + dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        }
    }
}
