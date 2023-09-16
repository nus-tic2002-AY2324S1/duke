public class MessageDisplay {
    private static final String LINE_BREAK = "****************************************";

    private static final String LOGO =
            "╭━━━╮╱╱╱╱╱╭╮\n" +
            "┃╭━╮┃╱╱╱╱╱┃┃\n" +
            "┃╰━━┳━━┳━━┫╰━┳━━┳━╮\n" +
            "╰━━╮┃╭╮┃╭╮┃╭╮┃╭╮┃╭╮╮\n" +
            "┃╰━╯┃╰╯┃╰╯┃┃┃┃╰╯┃┃┃┃\n" +
            "╰━━━┻━━┫╭━┻╯╰┻━━┻╯╰╯\n" +
            "╱╱╱╱╱╱╱┃┃\n" +
            "╱╱╱╱╱╱╱╰╯\n";

    public void print(String line) {
        System.out.printf("%s\n%s\n%s\n",LINE_BREAK,line,LINE_BREAK);
    }

    /**
     * Displays a welcome message with the Duke LOGO.
     */
    public void Hello() {
        print(LOGO + "Hello, I'm Sophon:). \nHow can I assist you today?");
    }

    /**
     * Displays a goodbye message.
     */
    public void Goodbye() {
        print("Bye! Hope to see you again soon!");
    }



    /**
     * Displays a message when a task is added.
     */
    public void addedMessage(Task[] userInputArray, int itemNumber) {
        int totalTasks = Task.getTotalTasks();
        System.out.printf("%s\nGot it. I've added this task:\n   %s\nNow you have %d tasks in the list.\n%s%n", LINE_BREAK, userInputArray[itemNumber].toString(), totalTasks, LINE_BREAK);
    }

    /**
     * Displays a message when attempting to mark a task that is already marked as completed.
     *
     * @param taskName The name of the task.
     */
    public void alreadyMark(String taskName) {
        print(taskName + " is already marked!");
    }

    /**
     * Displays a message when attempting to unmark a task that is not marked as completed.
     *
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

    public void invalidItemNumber() {
        print("Please provide me with Task item number only!");
    }

    /**
     * Displays a message when a task is marked as completed.
     *
     * @param userInputArray The array of user tasks.
     * @param itemNumber     The index of the completed task.
     */
    public void completeMessage(Task[] userInputArray, int itemNumber) {
        System.out.printf("%s\nThat's some progress! I've marked this task as done:\n   %s\n%s", LINE_BREAK, userInputArray[itemNumber].toString(), LINE_BREAK);
    }

    /**
     * Displays a message when a task is unmarked.
     *
     * @param userInputArray The array of user tasks.
     * @param itemNumber     The index of the unmarked task.
     */
    public void unCompleteMessage(Task[] userInputArray, int itemNumber) {
        System.out.printf("%s\nOkay, you can do it at a later time:\n   %s\n%s", LINE_BREAK, userInputArray[itemNumber].toString(), LINE_BREAK);
    }

    /**
     * Displays an error message for invalid number format.
     */
    public void invalidNumberFormat() {
        print("Invalid Number format for task item number!");
    }

    /**
     * Displays the list of user tasks.
     *
     * @param userInputArray The array of user tasks.
     */
    public void UserInputList(Task[] userInputArray) {
        if (Task.getTotalTasks() == 0) {
            print("There's nothing in your list");
            return;
        }
        System.out.println(LINE_BREAK);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < Task.getTotalTasks(); i++) {
            System.out.println((i + 1) + "." + userInputArray[i].toString());
        }
        System.out.println(LINE_BREAK);
    }

}
