package duke.userinterface;

import duke.task.Task;

import java.util.List;
import java.util.Scanner;

/**
 * Represents the user interface for the Duke application.
 */
public class UserInterface {

  public UserInterface.UserInput userInput;
  public UserInterface.MessageDisplay messageDisplay;

  /**
   * The UserInterface class represents the user interface of the Duke application.
   * It provides components for user input and message display.
   */
  public UserInterface() {

    userInput = new UserInterface.UserInput();
    messageDisplay = new UserInterface.MessageDisplay();
  }

  /**
   * Represents the user input part of the user interface.
   */
  public static class UserInput {

    private final Scanner scanner;

    public UserInput() {

      scanner = new Scanner(System.in);
    }

    /**
     * Get user input from the console.
     *
     * @return The user's input.
     */
    public String getUserInput() {

      return scanner.nextLine().trim().toLowerCase();
    }

    /**
     * Close the scanner when done.
     */
    public void closeScanner() {

      scanner.close();
    }

  }

  /**
   * Represents the message display part of the user interface.
   */
  public static class MessageDisplay {

    public static final String LINE_BREAK = "****************************************";
    private static final String LOGO =
        "╭━━━╮╱╱╱╱╱╭╮\n" +
            "┃╭━╮┃╱╱╱╱╱┃┃\n" +
            "┃╰━━┳━━┳━━┫╰━┳━━┳━╮\n" +
            "╰━━╮┃╭╮┃╭╮┃╭╮┃╭╮┃╭╮╮\n" +
            "┃╰━╯┃╰╯┃╰╯┃┃┃┃╰╯┃┃┃┃\n" +
            "╰━━━┻━━┫╭━┻╯╰┻━━┻╯╰╯\n" +
            "╱╱╱╱╱╱╱┃┃\n" +
            "╱╱╱╱╱╱╱╰╯\n";

    /**
     * Print a message with a line break.
     *
     * @param line The message to print.
     */
    public static void print(String line) {

      System.out.printf("%s\n%s\n", line, LINE_BREAK);
    }

    /**
     * Displays a message when a task is added.
     *
     * @param userInputList The list of user tasks.
     * @param itemNumber    The index of the added task.
     */
    public static void addedMessage(List<Task> userInputList, int itemNumber) {

      int totalTasks = Task.getTotalTasks();
      System.out.printf("Got it. I've added this task:\n"
              + "   %s\nNow you have %d tasks in the list.\n%s%n",
          userInputList.get(itemNumber).toString(), totalTasks, LINE_BREAK);
    }

    /**
     * Displays a message when attempting to mark a task that is already marked as completed.
     *
     * @param taskName The name of the task.
     */
    public static void alreadyMark(String taskName) {

      print(taskName + " is already marked!");
    }

    /**
     * Displays a message when attempting to unmark a task that is not marked as completed.
     *
     * @param taskName The name of the task.
     */
    public static void notMark(String taskName) {

      print("You did not complete " + taskName + " before!");
    }

    /**
     * Displays a message when a task is marked as completed.
     *
     * @param userInputList The list of user tasks.
     * @param itemNumber    The index of the completed task.
     */
    public static void completeMessage(List<Task> userInputList, int itemNumber) {

      System.out.printf("That's some progress! I've marked this task as done:\n   %s\n%s\n",
          userInputList.get(itemNumber).toString(), LINE_BREAK);
    }

    /**
     * Displays a message when a task is unmarked.
     *
     * @param userInputList The list of user tasks.
     * @param itemNumber    The index of the unmarked task.
     */
    public static void unCompleteMessage(List<Task> userInputList, int itemNumber) {

      System.out.printf("Okay, you can do it at a later time:\n   %s\n%s\n",
          userInputList.get(itemNumber).toString(), LINE_BREAK);
    }

    /**
     * Displays a message when a task is deleted.
     *
     * @param task The deleted task.
     */
    public static void deleteMessage(Task task) {

      System.out.printf("Noted. I've removed this task:\n   %s\n"
              + "Now you have %s tasks in the list.\n%s\n",
          task.toString(), Task.getTotalTasks(), LINE_BREAK);
    }

    /**
     * Displays a welcome message with the Duke LOGO.
     */
    public void hello() {

      print(LOGO + "hello, I'm Sophon:). \nHow can I assist you today?");
    }

    /**
     * Displays a goodbye message.
     */
    public void goodbye() {

      print("Bye! Hope to see you again soon!");
    }

  }

}
