package wargames;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        JoshuaUi.printGreetings();

        String input;
        Scanner in = new Scanner(System.in);
        JoshuaParser JParser = new JoshuaParser();
        TaskList taskList = new TaskList();

        do {
            System.out.print(">> ");
            input = in.nextLine();
            input = input.toLowerCase();

            if (JParser.isByeCommand(input)) {

            }
            else if (JParser.isListCommand(input)) {
                JoshuaUi.joshuaSays("Here is your current list:");
                JoshuaUi.joshuaSays(taskList.toString());
            }
            else if (JParser.isMarkCommand(input)) {
                int taskNum = JParser.parseTaskNum(input);
                taskList.markTaskAsDone(taskNum);
            }
            else if (JParser.isUnmarkCommand(input)) {
                int taskNum = JParser.parseTaskNum(input);
                taskList.markTaskAsNotDone(taskNum);
            }
            else if (JParser.isDeleteCommand(input)) {
                int taskIdx = JParser.parseTaskNum(input) - 1;
                Task task = taskList.getItem(taskIdx);
                JoshuaUi.joshuaSays("The following task will be deleted:\n\t" + task.toString());
                taskList.deleteFromTaskList(task);
                JoshuaUi.joshuaSays("You now have " + taskList.listSize() + " item(s) in your list.");
            }
            else if (JParser.isToDoCommand(input)) {
                try {
                    Task todo = JParser.createToDo(input);
                    taskList.addToTaskList(todo);
                    JoshuaUi.joshuaSays("ADDED TASK: " + todo);
                }
                catch (InvalidCommandException e) {
                    JoshuaUi.joshuaSays(e.getMessage());
                }
            }
            else if (JParser.isDeadlineCommand(input)) {
                try {
                    Task deadline = JParser.createDeadline(input);
                    taskList.addToTaskList(deadline);
                    JoshuaUi.joshuaSays("ADDED TASK: " + deadline);
                }
                catch (InvalidCommandException e) {
                    JoshuaUi.joshuaSays(e.getMessage());
                }
            }
            else if (JParser.isEventCommand(input)) {
                try {
                    Task event = JParser.createEvent(input);
                    taskList.addToTaskList(event);
                    JoshuaUi.joshuaSays("ADDED TASK: " + event);
                }
                catch (InvalidCommandException e) {
                    JoshuaUi.joshuaSays(e.getMessage());
                }
            }
            else {
                JoshuaUi.joshuaSays("Please be more articulate, Professor Falken.");
            }
        } while (!input.equals("bye"));

        JoshuaUi.joshuaSays("\nGOODBYE.");
    }

    public static int parseTaskNumber(String input, int beginIndex) {
        int taskNum = -1;
        try {
            taskNum = Integer.parseInt(input.substring(beginIndex));
        } catch (NumberFormatException e) {
            // Did not find a valid integer
            JoshuaUi.joshuaSays("Enter a valid task number.");
        }
        return taskNum;
    }

}
