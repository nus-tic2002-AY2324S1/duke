public class MessageDisplay {
    private final String lineBreak = "****************************************";

    private final String logo =
            "╭━━━╮╱╱╱╱╱╭╮\n" +
            "┃╭━╮┃╱╱╱╱╱┃┃\n" +
            "┃╰━━┳━━┳━━┫╰━┳━━┳━╮\n" +
            "╰━━╮┃╭╮┃╭╮┃╭╮┃╭╮┃╭╮╮\n" +
            "┃╰━╯┃╰╯┃╰╯┃┃┃┃╰╯┃┃┃┃\n" +
            "╰━━━┻━━┫╭━┻╯╰┻━━┻╯╰╯\n" +
            "╱╱╱╱╱╱╱┃┃\n" +
            "╱╱╱╱╱╱╱╰╯\n";



    public void print(String line) {
        System.out.println(lineBreak);
        System.out.println(line);
        System.out.println(lineBreak);
    }

    /**
     * Displays a welcome message with the Duke logo.
     */
    public void Hello() {
        print(logo + "Hello, I'm Sophon:). \nHow can I assist you today?");
    }

    /**
     * Displays a goodbye message.
     */
    public void Goodbye() {
        print("Bye! Hope to see you again soon!");
    }

    /**
     * Displays a message when user input is missing or empty.
     */
    public void MissingInput() {
        print("OOPS!!! The description of a todo cannot be empty.");
    }

    /**
     * Displays a message when a task is added.
     * @param message The message to be displayed.
     */
    public void AddedMessage(String message) {
        print("Added: " + message);
    }

    /**
     * Displays a message when attempting to mark a task that is already marked as completed.
     * @param taskName The name of the task.
     */
    public void alreadyMark(String taskName) {
        print(taskName + " is already marked!");
    }

    /**
     * Displays a message when attempting to unmark a task that is not marked as completed.
     * @param taskName The name of the task.
     */
    public void notMark(String taskName) {
        print("You did not complete " + taskName + " before!");
    }

    //Displays a message when the requested task is not found.
    public void notDeclared() {
        print("I can't find this task!");
    }

    /**
     * Displays a message when the requested task is not found.
     */
    public void invalidCommand() {
        print("Sorry, I don't understand that command. Please try again.");
    }

    /**
     * Displays a message when a task is marked as completed.
     * @param userInputArray The array of user tasks.
     * @param itemNumber The index of the completed task.
     */
    public void completeMessage(Task[] userInputArray, int itemNumber) {
        System.out.println(lineBreak);
        System.out.println("That's some progress! I've mark this task as done:");
        System.out.println(" ".repeat(3) + "[X] " + userInputArray[itemNumber].getTaskName());
        System.out.println(lineBreak);
    }

    /**
     * Displays a message when a task is unmarked.
     * @param userInputArray The array of user tasks.
     * @param itemNumber The index of the unmarked task.
     */
    public void unCompleteMessage(Task[] userInputArray, int itemNumber) {
        System.out.println(lineBreak);
        System.out.println("Okay, you can do it at a later time:");
        System.out.println(" ".repeat(3) + "[ ] " + userInputArray[itemNumber].getTaskName());
        System.out.println(lineBreak);
    }

    /**
     * Displays an error message for invalid number format.
     */
    public void invalidNumberFormat() {
        print("That doesn't seems like a item number.");
    }

    /**
     * Displays the list of user tasks.
     * @param userInputList The array of user tasks.
     */
    public void UserInputList(Task[] userInputList) {
        if (Task.getTotalTasks() == 0) {
            print("There's nothing in your list");
            return;
        }
        System.out.println(lineBreak);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < Task.getTotalTasks(); i++) {
            String Completed = "";
            if (userInputList[i].isCompleted()) {
                Completed = "[X]";
            } else {
                Completed = "[ ]";
            }
            System.out.println((i + 1) + "." + Completed + " " + userInputList[i].getTaskName());
        }
        System.out.println(lineBreak);
    }
}
