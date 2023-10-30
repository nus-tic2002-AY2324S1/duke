import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UI {
    public final Map<TaskType, String> TASK_KEYWORDS = new HashMap<>();
    {
        TASK_KEYWORDS.put(TaskType.TODO, "todo");
        TASK_KEYWORDS.put(TaskType.DEADLINE, "deadline");
        TASK_KEYWORDS.put(TaskType.EVENT, "event");
        TASK_KEYWORDS.put(TaskType.DELETE, "delete");
    }

    public void printTaskList(List<Task> list) {
        printSeparator();
        for (int i = 0; i < list.size(); i++) {
            System.out.print(i + 1 + ". ");
            list.get(i).print();
        }
        printSeparator();
    }

    public void printWelcomeMessage() {
        printSeparator();
        System.out.println("Hello! I'm AngelBot!");
        System.out.println("What can I do for you?");
        printSeparator();
    }

    public void printGoodbyeMessage() {
        printSeparator();
        System.out.println("Bye. Hope to see you again soon!");
        printSeparator();
    }

    public void printErrorMessage(ErrorType e, TaskType t, int size) {
        printSeparator();
        switch (e) {
            case ERR_EMPTY_DESCRIPTION:
                System.out.println("OOPS!!! The description of a " + TASK_KEYWORDS.get(t) + " cannot be empty.");
                break;
            case ERR_INVALID_FORMAT:
                System.out.printf(
                        "OOPS! It seems you didn't provide a valid %s format. To create a %s task, use the following format:",
                        TASK_KEYWORDS.get(t), TASK_KEYWORDS.get(t));
                System.out.println("");
                switch (t) {
                    case DEADLINE:
                        System.out.println("deadline [description] /by [date]");
                        break;
                    case EVENT:
                        System.out.println("event [description] /from [start time] /to [end time]");
                        break;
                    default:
                        System.out.println("todo [description]"); // Not expecting any formatting error here
                        break;
                }
                break;
            case ERR_POSSIBLE_TYPO:
                System.out.printf("OOPS! It appears there might be a typo. Did you mean to write '%s'?",
                        TASK_KEYWORDS.get(t));
                System.out.println("");
                break;
            case ERR_EXPECT_NUMBER:
                System.out.printf("Expect a number (1-%d) after %s", size, TASK_KEYWORDS.get(t));
                System.out.println("");
                break;
            case ERR_EXCEED_LIMIT:
                System.out.printf("The specified number exceeds the limit (1-%d)", size);
                System.out.println("");
                break;
            default:
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        printSeparator();
    }

    public static void printSeparator() {
        System.out.println("____________________________________________________________");
    }

}