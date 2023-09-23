import java.util.List;

public class MessageDisplay {
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

    public void print(String line) {
        System.out.printf("%s\n%s\n", line, LINE_BREAK);
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
    public void addedMessage(List<Task> userInputList, int itemNumber) {
        int totalTasks = Task.getTotalTasks();
        System.out.printf("Got it. I've added this task:\n   %s\nNow you have %d tasks in the list.\n%s%n", userInputList.get(itemNumber).toString(), totalTasks, LINE_BREAK);
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

    /**
     * Displays a message when a task is marked as completed.
     *
     * @param userInputList The array of user tasks.
     * @param itemNumber     The index of the completed task.
     */
    public void completeMessage(List<Task> userInputList, int itemNumber) {
        System.out.printf("That's some progress! I've marked this task as done:\n   %s\n%s\n", userInputList.get(itemNumber).toString(), LINE_BREAK);
    }

    /**
     * Displays a message when a task is unmarked.
     *
     * @param userInputList The array of user tasks.
     * @param itemNumber     The index of the unmarked task.
     */
    public void unCompleteMessage(List<Task> userInputList, int itemNumber) {
        System.out.printf("Okay, you can do it at a later time:\n   %s\n%s\n", userInputList.get(itemNumber).toString(), LINE_BREAK);
    }

    /**
     * Displays the list of user tasks.
     *
     * @param userInputList The array of user tasks.
     */
    public void UserInputList(List<Task> userInputList) {
        if (Task.getTotalTasks() == 0) {
            print("There's nothing in your list");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < Task.getTotalTasks(); i++) {
            System.out.println((i + 1) + "." + userInputList.get(i).toString());
        }
        System.out.println(LINE_BREAK);
    }

}
