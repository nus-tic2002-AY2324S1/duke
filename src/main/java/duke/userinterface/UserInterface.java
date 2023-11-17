package duke.userinterface;

import java.util.List;
import java.util.Scanner;

import duke.task.Task;

/**
 * Represents the user interface for the Duke application.
 */
public class UserInterface {

    private final UserInterface.UserInput userInput;
    private final UserInterface.MessageDisplay messageDisplay;

    /**
     * The UserInterface class represents the user interface of the Duke application.
     * It provides components for user input and message display.
     */

    public UserInterface() {

        userInput = new UserInterface.UserInput();
        messageDisplay = new UserInterface.MessageDisplay();
    }

    public UserInput getUserInput() {
        return userInput;
    }

    public MessageDisplay getMessageDisplay() {
        return messageDisplay;
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
        public String getLine() {

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

        public static final String LINE_BREAK = "********************************************";
        private static final String LOGO =
                "███████╗ ██████╗ ██████╗ ██╗  ██╗ ██████╗ ███╗   ██╗" + System.lineSeparator()
                + "██╔════╝██╔═══██╗██╔══██╗██║  ██║██╔═══██╗████╗  ██║" + System.lineSeparator()
                + "███████╗██║   ██║██████╔╝███████║██║   ██║██╔██╗ ██║" + System.lineSeparator()
                + "╚════██║██║   ██║██╔═══╝ ██╔══██║██║   ██║██║╚██╗██║" + System.lineSeparator()
                + "███████║╚██████╔╝██║     ██║  ██║╚██████╔╝██║ ╚████║" + System.lineSeparator()
                + "╚══════╝ ╚═════╝ ╚═╝     ╚═╝  ╚═╝ ╚═════╝ ╚═╝  ╚═══╝" + System.lineSeparator();
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
         * @param task The task being snoozed.
         */
        public void rescheduleMessage(Task task) {

            System.out.printf("Noted. I've updated the due date of this task:\n   %s\n",
                task.toString());
            MessageDisplay.printLineBreak();
        }


        /**
         * Displays a welcome message for existing user with the Duke LOGO.
         */
        public void existingUserHello() {
            System.out.println(LOGO + "Sophon here, Your personal Task assistant! \nHow can I assist you today?");
            printLineBreak();
        }
        /**
         * Displays an informative guide for new user to help them understand what are the commands available.
         */
        public void newUserHello() {

            System.out.println(LOGO + "Hello, I'm Sophon. Your personal Task assistant." + System.lineSeparator()
                + LINE_BREAK + System.lineSeparator()
                 + "It seems that you're here for the first time. Here are the list of the "
                 + "command that can help you with your task management:" + System.lineSeparator()
                 + "Todo        -> To create a simple to-do task." + System.lineSeparator()
                + "Deadline    -> To create a deadline task with a deadline." + System.lineSeparator()
                + "Event       -> To create a event task that comes with a start and end date." + System.lineSeparator()
                + "Delete      -> To delete a task from the list." + System.lineSeparator()
                + "Find        -> To find a task from the list using a keyword." + System.lineSeparator()
                + "List        -> To show all recorded tasks." + System.lineSeparator()
                + "Mark        -> To mark a task as completed." + System.lineSeparator()
                + "Unmark      -> To unmark a task as incomplete." + System.lineSeparator()
                + "On          -> To check if any task is due on a certain deadline." + System.lineSeparator()
                + "Reschedule  -> To reschedule a task's deadline." + System.lineSeparator()
                + "You can also use 'help' to learn the command format."
            );
            printLineBreak();
        }
        /**
         * Displays an informative guide for new user to help them understand how to interact with Sophon.
         */
        public static void helpUserOnCommand() {

            System.out.println("Here are the format of the commands:" + System.lineSeparator()
                + "Todo        -> <todo> + <Task Name>" + System.lineSeparator()
                + "Deadline    -> <deadline> + <task name> + </by> + <task due Date (yyyy-MM-dd HH:mm)>"
                + System.lineSeparator()
                + "Event       -> <event> + <task name> + </from> + <task start Date (yyyy-MM-dd HH:mm)> "
                + "+ </to> + <task due Date (yyyy-MM-dd HH:mm)>" + System.lineSeparator()
                + "Delete      -> <delete> + <task index>" + System.lineSeparator()
                + "Find        -> <find> + <keyword in task name>" + System.lineSeparator()
                + "List        -> <list>" + System.lineSeparator()
                + "Mark        -> <mark> + <task index>" + System.lineSeparator()
                + "Unmark      -> <unmark> + <task index>" + System.lineSeparator()
                + "On          -> <on> + <task due Date (yyyy-MM-dd HH:mm)>" + System.lineSeparator()
                + "Reschedule  -> <reschedule> + <task index> + <task due Date (yyyy-MM-dd HH:mm)>"
            );
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
