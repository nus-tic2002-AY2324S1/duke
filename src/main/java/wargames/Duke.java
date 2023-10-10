package wargames;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
                // TODO: Handle the case where mark is empty; no number is given
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
            else if (input.startsWith("todo ")) {
                List<String> inputArrayList = stringToArrayList(input);
                List<String> descArrayList = inputArrayList.subList(1, inputArrayList.size());
                String desc = String.join(" ", descArrayList);
                Task todo = new ToDo(desc);
                Joshua.addTaskToList(todo);
            }
            else if (input.startsWith("deadline ")) {
                // TODO: Check that the input contains /by first, handle the case where it doesn't
                // Get "desc" and "by" from input
                List<String> inputArrayList = stringToArrayList(input);
                int byMarker = inputArrayList.indexOf("/by");
                List<String> descArrayList = inputArrayList.subList(1, byMarker);
                String desc = String.join(" ", descArrayList);
                List<String> byArrayList = inputArrayList.subList(byMarker+1, inputArrayList.size());
                String by = String.join(" ", byArrayList);
                // Create Deadline and add to list
                Task deadline = new Deadline(desc, by);
                Joshua.addTaskToList(deadline);
            }
            else if (input.startsWith("event ")) {
                // Get "from" and "to" from the input
                List<String> inputArrayList = stringToArrayList(input);
                int fromMarker = inputArrayList.indexOf("/from");
                int toMarker = inputArrayList.indexOf("/to");
                List<String> descArrayList = inputArrayList.subList(1, fromMarker);
                String desc = String.join(" ", descArrayList);
                List<String> fromArrayList = inputArrayList.subList(fromMarker+1, toMarker);
                String from = String.join(" ", fromArrayList);
                List<String> toArrayList = inputArrayList.subList(toMarker+1, inputArrayList.size());
                String to = String.join(" ", toArrayList);
                // Create Event and add to list
                Task event = new Event(desc, from, to);
                Joshua.addTaskToList(event);
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

    public static ArrayList<String> stringToArrayList(String str) {
        String[] strArray = str.split("\\s+"); // Split on any number of whitespaces
        List<String> strList = new ArrayList<>(Arrays.asList(strArray));
        return new ArrayList<>(strList);
    }

}
