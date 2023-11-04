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

    private UserInput() {

      scanner = new Scanner(System.in);
    }

    /**
     * Get user input from the console.
     *
     * @return The user's input as a String.
     */
    public String getUserInput() {

      return scanner.nextLine().trim().toLowerCase();
    }

    /**
     * Close the underlying scanner when done.
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
        "╭━━━╮╱╱╱╱╱╭╮\n"
            + "┃╭━╮┃╱╱╱╱╱┃┃\n"
            + "┃╰━━┳━━┳━━┫╰━┳━━┳━╮\n"
            + "╰━━╮┃╭╮┃╭╮┃╭╮┃╭╮┃╭╮╮\n"
            + "┃╰━╯┃╰╯┃╰╯┃┃┃┃╰╯┃┃┃┃\n"
            + "╰━━━┻━━┫╭━┻╯╰┻━━┻╯╰╯\n"
            + "╱╱╱╱╱╱╱┃┃\n"
            + "╱╱╱╱╱╱╱╰╯\n";

    /**
     * Prints a line break using a predefined LINE_BREAK constant.
     */
    public static void printLineBreak() {

      System.out.println(LINE_BREAK);
    }

    /**
     * Displays a message when a task is added.
     *
     * @param taskList The list of user tasks.
     */
    public void addedMessage(List<Task> taskList) {

      System.out.printf("Got it. I've added this task:\n"
              + "   %s\nNow you have %d tasks in the list.\n%s%n",
          taskList.get(taskList.size() - 1).toString(), taskList.size(), LINE_BREAK);
    }

    /**
     * Displays a message when attempting to mark a task that is already marked as completed.
     *
     * @param taskName The name of the task.
     */
    public void alreadyMark(String taskName) {
      System.out.println(taskName + " is already marked!");
      printLineBreak();
    }

    /**
     * Displays a message when attempting to unmark a task that is not marked as completed.
     *
     * @param taskName The name of the task.
     */
    public void notMark(String taskName) {
      System.out.println("You did not complete " + taskName + " before!");
      printLineBreak();
    }

    /**
     * Displays a message when a task is marked as completed.
     *
     * @param taskList   The list of user tasks.
     * @param itemNumber The index of the completed task.
     */
    public void completeMessage(List<Task> taskList, int itemNumber) {

      System.out.printf("That's some progress! I've marked this task as done:\n   %s\n",
          taskList.get(itemNumber).toString());
      printLineBreak();
    }

    /**
     * Displays a message when a task is unmarked.
     *
     * @param taskList   The list of user tasks.
     * @param itemNumber The index of the unmarked task.
     */
    public void unCompleteMessage(List<Task> taskList, int itemNumber) {

      System.out.printf("Okay, you can do it at a later time:\n   %s\n%s\n",
          taskList.get(itemNumber).toString(), LINE_BREAK);
    }

    /**
     * Displays a message when a task is deleted.
     *
     * @param taskList The list of user tasks.
     * @param task     The deleted task.
     */
    public void deleteMessage(List<Task> taskList, Task task) {

      System.out.printf("Noted. I've removed this task:\n   %s\n"
              + "Now you have %s tasks in the list.\n",
          task.toString(), taskList.size());
      MessageDisplay.printLineBreak();
    }

    /**
     * Displays a message when a task is snoozed.
     *
     * @param task     The task being snoozed.
     */
    public void snoozeMessage(Task task) {

      System.out.printf("Noted. I've pushed this task to 12 hours later:\n   %s\n",
          task.toString());
      MessageDisplay.printLineBreak();
    }


    /**
     * Displays a welcome message with the Duke LOGO.
     */
    public void hello() {

      System.out.println(LOGO + "hello, I'm Sophon:). \nHow can I assist you today?");
      printLineBreak();
    }

    /**
     * Displays a goodbye message.
     */
    public void goodbye() {

      System.out.println("Bye! Hope to see you again soon!");
      printLineBreak();
    }

  }

}
