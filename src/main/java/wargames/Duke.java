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
        JoshuaParser JParser = new JoshuaParser();
        MyList myList = new MyList();

        do {
            System.out.print(">> ");
            input = in.nextLine();
            input = input.toLowerCase();

            if (JParser.isByeCommand(input)) {

            }
            else if (JParser.isListCommand(input)) {
                Joshua.joshuaSays("Here is your current list:");
                Joshua.joshuaSays(myList.toString());
            }
            else if (JParser.isMarkCommand(input)) {
                int taskNum = JParser.parseTaskNum(input);
                myList.markTaskAsDone(taskNum);
            }
            else if (JParser.isUnmarkCommand(input)) {
                int taskNum = JParser.parseTaskNum(input);
                myList.markTaskAsNotDone(taskNum);
            }
            else if (JParser.isToDoCommand(input)) {
                try {
                    Task todo = JParser.createToDo(input);
                    myList.addToTaskList(todo);
                    Joshua.joshuaSays("ADDED TASK: " + todo);
                }
                catch (InvalidCommandException e) {
                    Joshua.joshuaSays(e.getMessage());
                }
            }
            else if (JParser.isDeadlineCommand(input)) {
                try {
                    Task deadline = JParser.createDeadline(input);
                    myList.addToTaskList(deadline);
                    Joshua.joshuaSays("ADDED TASK: " + deadline);
                }
                catch (InvalidCommandException e) {
                    Joshua.joshuaSays(e.getMessage());
                }
            }
            else if (JParser.isEventCommand(input)) {
                try {
                    Task event = JParser.createEvent(input);
                    myList.addToTaskList(event);
                    Joshua.joshuaSays("ADDED TASK: " + event);
                }
                catch (InvalidCommandException e) {
                    Joshua.joshuaSays(e.getMessage());
                }
            }
            else {
                Joshua.joshuaSays("Please be more articulate, Professor Falken.");
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
