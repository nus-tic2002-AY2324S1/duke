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

        echoMessage(String.join(System.lineSeparator(), new String[]{
                " Hello! I'm DukeBot",
                " What can I do for you?"
        }));

        do {
            String input = in.nextLine();
            if (input.equals(COMMAND_BYE)) {
                echoMessage(" Bye. Hope to see you again soon!");
                break;
            } else if (input.equals(COMMAND_LIST)) {
                String[] lines = new String[tasks.size()];
                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.get(i);
                    lines[i] = String.format(" %d.%s", i + 1, task.toString());
                }
                echoMessage(String.join(System.lineSeparator(), lines), true);
            } else if (isMulticomponentCommand(input, COMMAND_MARK)) {
                String additionalInput = input.substring(COMMAND_MARK.length()).trim();
                Integer taskNumber = tryParseInt(additionalInput);
                if (taskNumber == null || taskNumber < 1 || taskNumber > tasks.size()) {
                    echoMessage(" Invalid task number!");
                    continue;
                }
                Task task = tasks.get(taskNumber - 1);
                task.setDone(true);
                echoMessage(String.join(System.lineSeparator(), new String[]{
                        " Nice! I've marked this task as done:",
                        "   " + task.toString()
                }));
            } else if (isMulticomponentCommand(input, COMMAND_UNMARK)) {
                String additionalInput = input.substring(COMMAND_UNMARK.length()).trim();
                Integer taskNumber = tryParseInt(additionalInput);
                if (taskNumber == null || taskNumber < 1 || taskNumber > tasks.size()) {
                    echoMessage(" Invalid task number!");
                    continue;
                }
                Task task = tasks.get(taskNumber - 1);
                task.setDone(false);
                echoMessage(String.join(System.lineSeparator(), new String[]{
                        " OK, I've marked this task as not done yet:",
                        "   " + task.toString()
                }));
            } else if (isMulticomponentCommand(input, COMMAND_TODO)) {
                String additionalInput = input.substring(COMMAND_TODO.length()).trim();
                Todo toto = new Todo(additionalInput);
                tasks.add(toto);
                echoAddMessage(toto, tasks.size());
            } else if (isMulticomponentCommand(input, COMMAND_DEADLINE)) {
                String additionalInput = input.substring(COMMAND_DEADLINE.length()).trim();
                String[] array = additionalInput.split(" /by ", -1);
                if (array.length != 2) {
                    echoMessage(" Invalid deadline!");
                    continue;
                }
                Deadline deadline = new Deadline(array[0], array[1]);
                tasks.add(deadline);
                echoAddMessage(deadline, tasks.size());
            } else if (isMulticomponentCommand(input, COMMAND_EVENT)) {
                String additionalInput = input.substring(COMMAND_EVENT.length()).trim();
                String[] array = additionalInput.split(" /from ", -1);
                if (array.length != 2) {
                    echoMessage(" Invalid event!");
                    continue;
                }
                String[] fromToArray = array[1].split(" /to ", -1);
                if (fromToArray.length != 2) {
                    echoMessage(" Invalid event!");
                    continue;
                }
                Event event = new Event(array[0], fromToArray[0], fromToArray[1]);
                tasks.add(event);
                echoAddMessage(event, tasks.size());
            } else {
                echoMessage(" Unknown command!");
            }
        } while (true);
    }

    private static void echoMessage(String message) {
        echoMessage(message, false);
    }

    private static void echoMessage(String message, boolean skipEmptyMessage) {
        System.out.println(HORIZONTAL_LINE);
        if (!skipEmptyMessage || !message.isEmpty()) {
            System.out.println(message);
        }
        System.out.println(HORIZONTAL_LINE);
    }

    private static void echoAddMessage(Task addedTask, int tasksLatestSize) {
        echoMessage(String.join(System.lineSeparator(), new String[]{
                " Got it. I've added this task:",
                "   " + addedTask.toString(),
                String.format(" Now you have %d tasks in the list.", tasksLatestSize)
        }));
    }

    private static boolean isMulticomponentCommand(String input, String commandName) {
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
