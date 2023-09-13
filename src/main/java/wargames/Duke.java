package wargames;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Joshua.printGreetings();

        String input;
        Scanner in = new Scanner(System.in);

        do {
            System.out.print(">> ");
            input = in.nextLine();
            input = input.toLowerCase();

            if (input.equals("bye")) {

            }
            else if (input.equals("list")) {
                Joshua.printMyList();
            }
            else if (input.startsWith("mark ")) {
                try {
                    int taskNum = parseTaskNumber(input, 5);
                    Joshua.markTaskAsDone(taskNum);
                } catch (ArrayIndexOutOfBoundsException e) {
                    Joshua.joshuaSays("Enter a number from the task list.");
                }
            }
            else if (input.startsWith("unmark ")) {
                try {
                    int taskNum = parseTaskNumber(input, 7);
                    Joshua.markTaskAsNotDone(taskNum);
                } catch (ArrayIndexOutOfBoundsException e) {
                    Joshua.joshuaSays("Enter a number from the task list.");
                }
            }
            else {
                Task task = new Task(input);
                Joshua.addTaskToList(task);
            }
        } while (!input.equals("bye"));

        Joshua.joshuaSays("\nGOODBYE.");
    }

    public static int parseTaskNumber(String input, int beginIndex) {
        int taskNum = -1;
        try {
            taskNum = Integer.parseInt(input.substring(beginIndex));
        } catch (NumberFormatException e) {
            // Did not find a valid integer
            Joshua.joshuaSays("Enter a valid task number.");
        }
        return taskNum;
    }

}
