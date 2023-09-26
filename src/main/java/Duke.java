import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_UNMARK = "unmark";

    private enum ProgramAction {CONTINUE, EXIT}

    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        */

        Scanner in = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        printMessage(String.join(System.lineSeparator(), new String[]{
                " Hello! I'm DukeBot",
                " What can I do for you?"
        }));

        do {
            String input = in.nextLine();
            try {
                if (handleInput(tasks, input) == ProgramAction.EXIT) {
                    break;
                }
            } catch (DukeException e) {
                if (e instanceof InvalidCommandArgsDukeException) {
                    printMessage(" OOPS!!! " + e.getMessage());
                } else if (e instanceof UnknownCommandDukeException) {
                    printMessage(" OOPS!!! I'm sorry, but I don't know what that means :-(");
                } else {
                    printMessage(" OOPS!!! An unhandled Duke exception occurred.");
                }
            } catch (Exception e) {
                printMessage(" OOPS!!! An unknown exception occurred.");
            }
        } while (true);
    }

    private static ProgramAction handleInput(ArrayList<Task> tasks, String input) throws DukeException {
        if (isForCommand(input, COMMAND_BYE)) {
            String commandArgs = input.substring(COMMAND_BYE.length()).trim();
            handleByeCommand(commandArgs);
            return ProgramAction.EXIT;
        } else if (isForCommand(input, COMMAND_LIST)) {
            String commandArgs = input.substring(COMMAND_LIST.length()).trim();
            handleListCommand(tasks, commandArgs);
        } else if (isForCommand(input, COMMAND_MARK)) {
            String commandArgs = input.substring(COMMAND_MARK.length()).trim();
            handleMarkCommand(tasks, commandArgs);
        } else if (isForCommand(input, COMMAND_UNMARK)) {
            String commandArgs = input.substring(COMMAND_UNMARK.length()).trim();
            handleUnmarkCommand(tasks, commandArgs);
        } else if (isForCommand(input, COMMAND_TODO)) {
            String commandArgs = input.substring(COMMAND_TODO.length()).trim();
            handleTodoCommand(tasks, commandArgs);
        } else if (isForCommand(input, COMMAND_DEADLINE)) {
            String commandArgs = input.substring(COMMAND_DEADLINE.length()).trim();
            handleDeadlineCommand(tasks, commandArgs);
        } else if (isForCommand(input, COMMAND_EVENT)) {
            String commandArgs = input.substring(COMMAND_EVENT.length()).trim();
            handleEventCommand(tasks, commandArgs);
        } else {
            throw new UnknownCommandDukeException("Input: " + input);
        }
        return ProgramAction.CONTINUE;
    }

    private static void handleByeCommand(String args) throws InvalidCommandArgsDukeException {
        if (!args.isEmpty()) {
            throw new InvalidCommandArgsDukeException("The bye command should not take any arguments.");
        }

        printMessage(" Bye. Hope to see you again soon!");
    }

    private static void handleListCommand(ArrayList<Task> tasks, String args) throws InvalidCommandArgsDukeException {
        if (!args.isEmpty()) {
            throw new InvalidCommandArgsDukeException("The list command should not take any arguments.");
        }

        String[] lines = new String[tasks.size()];
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            lines[i] = String.format(" %d.%s", i + 1, task.toString());
        }
        printMessage(String.join(System.lineSeparator(), lines), true);
    }

    private static void handleMarkCommand(ArrayList<Task> tasks, String args) throws InvalidCommandArgsDukeException {
        if (args.isEmpty()) {
            throw new InvalidCommandArgsDukeException("The task number to be marked cannot be empty.");
        }

        Integer taskNumber = tryParseInt(args);
        if (taskNumber == null || taskNumber < 1 || taskNumber > tasks.size()) {
            throw new InvalidCommandArgsDukeException("Invalid task number to be marked.");
        }

        Task task = tasks.get(taskNumber - 1);
        task.setDone(true);
        printMessage(String.join(System.lineSeparator(), new String[]{
                " Nice! I've marked this task as done:",
                "   " + task.toString()
        }));
    }

    private static void handleUnmarkCommand(ArrayList<Task> tasks, String args) throws InvalidCommandArgsDukeException {
        if (args.isEmpty()) {
            throw new InvalidCommandArgsDukeException("The task number to be unmarked cannot be empty.");
        }

        Integer taskNumber = tryParseInt(args);
        if (taskNumber == null || taskNumber < 1 || taskNumber > tasks.size()) {
            throw new InvalidCommandArgsDukeException("Invalid task number to be unmarked.");
        }

        Task task = tasks.get(taskNumber - 1);
        task.setDone(false);
        printMessage(String.join(System.lineSeparator(), new String[]{
                " OK, I've marked this task as not done yet:",
                "   " + task.toString()
        }));
    }

    private static void handleTodoCommand(ArrayList<Task> tasks, String args) throws InvalidCommandArgsDukeException {
        if (args.isEmpty()) {
            throw new InvalidCommandArgsDukeException("The description of a todo cannot be empty.");
        }

        Todo toto = new Todo(args);
        tasks.add(toto);
        printAddMessage(toto, tasks.size());
    }

    private static void handleDeadlineCommand(ArrayList<Task> tasks, String args) throws InvalidCommandArgsDukeException {
        if (args.isEmpty()) {
            throw new InvalidCommandArgsDukeException("The description of a deadline cannot be empty.");
        }

        String[] array = args.split(" /by ", -1);
        if (array.length != 2) {
            throw new InvalidCommandArgsDukeException("The \"/by {date/time}\" of a deadline is required.");
        }

        Deadline deadline = new Deadline(array[0], array[1]);
        tasks.add(deadline);
        printAddMessage(deadline, tasks.size());
    }

    private static void handleEventCommand(ArrayList<Task> tasks, String args) throws InvalidCommandArgsDukeException {
        if (args.isEmpty()) {
            throw new InvalidCommandArgsDukeException("The description of a event cannot be empty.");
        }

        String[] array = args.split(" /from ", -1);
        if (array.length != 2) {
            throw new InvalidCommandArgsDukeException("The \"/from {date/time}\" of a event is required.");
        }

        String[] fromToArray = array[1].split(" /to ", -1);
        if (fromToArray.length != 2) {
            throw new InvalidCommandArgsDukeException("The \"/to {date/time}\" of a event is required.");
        }

        Event event = new Event(array[0], fromToArray[0], fromToArray[1]);
        tasks.add(event);
        printAddMessage(event, tasks.size());
    }

    private static void printMessage(String message) {
        printMessage(message, false);
    }

    private static void printMessage(String message, boolean skipEmptyMessage) {
        System.out.println(HORIZONTAL_LINE);
        if (!skipEmptyMessage || !message.isEmpty()) {
            System.out.println(message);
        }
        System.out.println(HORIZONTAL_LINE);
    }

    private static void printAddMessage(Task addedTask, int tasksLatestSize) {
        printMessage(String.join(System.lineSeparator(), new String[]{
                " Got it. I've added this task:",
                "   " + addedTask.toString(),
                String.format(" Now you have %d tasks in the list.", tasksLatestSize)
        }));
    }

    private static boolean isForCommand(String input, String commandName) {
        return input.equals(commandName) || input.startsWith(commandName + " ");
    }

    private static Integer tryParseInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return null;
        }
    }
}
