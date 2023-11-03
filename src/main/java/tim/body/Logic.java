package tim.body;
import tim.tasks.Task;
import java.util.ArrayList;

/**
 * Represents as a Logic object.
 * This class is the logic of the program.
 * It executes the actions based on the parsed user input from the parser.
 */
public class Logic {

    /**
     * Executes the action based on the parsed user input from the parser.
     *
     * @param mode  Action to be executed.
     * @param list  List of tasks.
     * @param token User input split into an array of strings.
     */
    static void executeAction (String mode, ArrayList<Task> list, String[] token){
        int index;
        String taskName;
        switch (mode) {
        case "bye":
            Messenger.goodbyeGreet();
            break;
        case "list":
            Messenger.printList(list);
            break;
        case "date":
            Messenger.printDate();
            break;
        case "mark":
            try {
                index = Integer.parseInt(token[1]);
                ListManager.markUnmarkTask(index, true, list);
            } catch (ArrayIndexOutOfBoundsException AIO) {
                System.err.println("incorrect input for index!");
            }
            break;
        case "unmark":
            try {
                index = Integer.parseInt(token[1]);
                ListManager.markUnmarkTask(index, false, list);
            } catch (ArrayIndexOutOfBoundsException AIO) {
                System.err.println("incorrect input for index!");
            }
            break;
        case "todo":
            try {
                taskName = token[1];
                ListManager.addToDo(taskName, list);
            } catch (ArrayIndexOutOfBoundsException AIO) {
                System.err.println("what is the name of the task to complete?");
            }
            break;
        case "deadline":
            try {

                taskName = token[1];
                ListManager.addDeadline(taskName, list);
            } catch (ArrayIndexOutOfBoundsException AIO) {
                System.err.println("what is the name of the deadline agenda?");
            }
            break;
        case "event":
            try {
                taskName = token[1];
                ListManager.addEvent(taskName, list);
            } catch (ArrayIndexOutOfBoundsException AIO) {
                System.err.println("what is the name of the event?");
            }
            break;
        case "delete":
            try {
                int deleteIndex = Integer.parseInt(token[1]);
                ListManager.deleteFromList(deleteIndex, list);
            } catch (Exception e) {
                System.err.println("please include valid index of task to delete");
            }
            break;
        default:
            System.err.println("I've not learn to do this yet!!");
        }
        if (!(mode.equals("list") || mode.equals("date") || mode.equals("bye"))) {
            FileManager.saveList(list);
        }
    }
}
