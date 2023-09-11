import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
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
                    lines[i] = String.format(" %d.[%s] %s", i + 1, task.getStatusIcon(), task.getDescription());
                }
                echoMessage(String.join(System.lineSeparator(), lines), true);
                continue;
            } else if (input.equals(COMMAND_MARK) || input.startsWith(COMMAND_MARK + " ")) {
                String taskNumberInput = input.substring(COMMAND_MARK.length()).trim();
                Integer taskNumber = tryParseInt(taskNumberInput);
                if (taskNumber == null || taskNumber < 1 || taskNumber > tasks.size()) {
                    echoMessage(" Invalid task number!");
                    continue;
                }
                Task task = tasks.get(taskNumber - 1);
                task.setDone(true);
                echoMessage(String.join(System.lineSeparator(), new String[]{
                        " Nice! I've marked this task as done:",
                        String.format("   [%s] %s", task.getStatusIcon(), task.getDescription())
                }));
                continue;
            } else if (input.equals(COMMAND_UNMARK) || input.startsWith(COMMAND_UNMARK + " ")) {
                String taskNumberInput = input.substring(COMMAND_UNMARK.length()).trim();
                Integer taskNumber = tryParseInt(taskNumberInput);
                if (taskNumber == null || taskNumber < 1 || taskNumber > tasks.size()) {
                    echoMessage(" Invalid task number!");
                    continue;
                }
                Task task = tasks.get(taskNumber - 1);
                task.setDone(false);
                echoMessage(String.join(System.lineSeparator(), new String[]{
                        " OK, I've marked this task as not done yet:",
                        String.format("   [%s] %s", task.getStatusIcon(), task.getDescription())
                }));
                continue;
            }

            Task task = new Task(input);
            tasks.add(task);
            echoMessage(" added: " + task.getDescription());
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

    private static Integer tryParseInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return null;
        }
    }
}
