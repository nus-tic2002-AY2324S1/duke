import java.util.ArrayList;

public class UI {
    private static final String BORDER = "\t─────────────────────────────────────────────────────────────────";

    public static void welcome() {
        System.out.println(BORDER);
        System.out.println("\tHello! I'm Bott!\n\tWhat can I do for you?");
        System.out.println(BORDER);
    }

    public static void bye() {
        System.out.println(BORDER);
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(BORDER);
    }

    public static void hello() {
        System.out.println(BORDER);
        System.out.println("\tHello! Nice to meet you.");
        System.out.println(BORDER);
    }

    public static void printGenericMessage(String message) {
        System.out.println(BORDER);
        System.out.println("\t" + message);
        System.out.println(BORDER);
    }

    public static void printTaskList(ArrayList<Task> taskList) {
        System.out.println(BORDER);
        System.out.println("\tHere is/are the task(s) in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("\t" + (i+1) + ". " + taskList.get(i).toDisplay());
        }
        System.out.println(BORDER);
    }

    public static void setDone(ArrayList<Task> taskList, int taskIndex) {
        System.out.println(BORDER);
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t" + taskList.get(taskIndex).toDisplay());
        System.out.println(BORDER);
    }

    public static void setNotDone(ArrayList<Task> taskList, int taskIndex) {
        System.out.println(BORDER);
        System.out.println("\tOK! I've marked this task as not done yet:");
        System.out.println("\t" + taskList.get(taskIndex).toDisplay());
        System.out.println(BORDER);
    }

    public static void printTaskAdded(ArrayList<Task> taskList, int index) {
        System.out.println(BORDER);
        System.out.println("\tGot it! I've added this task:");
        System.out.println("\t" + taskList.get(index).toDisplay());
        System.out.printf("\tNow you have %d task in the list.\n", index+1);
        System.out.println(BORDER);
    }

    public static void printTaskDeleted(ArrayList<Task> taskList, int taskIndex, int index) {
        System.out.println(BORDER);
        System.out.println("\tNoted! I will remove this task:");
        System.out.println("\t" + taskList.get(taskIndex).toDisplay());
        System.out.printf("\tNow you have %d task in the list\n", index-1);
        System.out.println(BORDER);
    }

    public static void printDefaultMessage() {
        System.out.println(BORDER);
        System.out.println("\tI don't quite understand that. Please try again.");
        System.out.println(BORDER);
    }

    public static void errorWrongTodoFormat() {
        System.out.println(BORDER);
        System.out.println("\tPlease follow this format:\n" + "\t\"todo\" \"your description\"");
        System.out.println(BORDER);
    }

    public static void errorWrongDeadlineFormat() {
        System.out.println(BORDER);
        System.out.println("\tPlease follow this format:\n" + "\t\"deadline\" \"your description\" \"/by\" \"date/time\"");
        System.out.println(BORDER);
    }

    public static void errorWrongEventFormat() {
        System.out.println(BORDER);
        System.out.println("\tPlease follow this format:\n" + "\t\"event\" \"your description\" \"/from\" \"date/time\" \"/to\" \"date/time\"");
        System.out.println(BORDER);
    }

    public static void errorIndexOutOfBounds() {
        System.out.println(BORDER);
        System.out.println("\tPlease enter a valid number, i.e. within the list.");
        System.out.println(BORDER);
    }

    public static void errorArrayIndexOutOfBounds() {
        System.out.println(BORDER);
        System.out.println("\tPlease include the task number.");
        System.out.println(BORDER);
    }

    public static void errorNumberFormatException() {
        System.out.println(BORDER);
        System.out.println("\tPlease only input a number.");
        System.out.println(BORDER);
    }
}
