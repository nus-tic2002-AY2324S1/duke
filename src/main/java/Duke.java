import java.util.Hashtable;
import java.util.Scanner;

public class Duke {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final Hashtable<String, DuckCommandHandler> commandHandlerMap = new Hashtable<>() {
        {
            put(DuckCommand.BYE.toString(), Duke::handleByeCommand);
            put(DuckCommand.DEADLINE.toString(), Duke::handleDeadlineCommand);
            put(DuckCommand.DELETE.toString(), Duke::handleDeleteCommand);
            put(DuckCommand.EVENT.toString(), Duke::handleEventCommand);
            put(DuckCommand.LIST.toString(), Duke::handleListCommand);
            put(DuckCommand.MARK.toString(), Duke::handleMarkCommand);
            put(DuckCommand.TODO.toString(), Duke::handleTodoCommand);
            put(DuckCommand.UNMARK.toString(), Duke::handleUnmarkCommand);
        }
    };
    private final Storage storage;
    private final TaskList tasks;

    private enum ProgramAction {CONTINUE, EXIT}

    private enum DuckCommand {
        BYE("bye"),
        DEADLINE("deadline"),
        DELETE("delete"),
        EVENT("event"),
        LIST("list"),
        MARK("mark"),
        TODO("todo"),
        UNMARK("unmark");

        private final String commandName;

        DuckCommand(String commandName) {
            this.commandName = commandName;
        }

        @Override
        public String toString() {
            return commandName;
        }
    }

    @FunctionalInterface
    private interface DuckCommandHandler {
        void apply(TaskList tasks, String input) throws DukeException;
    }

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        TaskList tasks;
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            printMessage(String.format(" Failed to load tasks from '%s'. An empty task list will be used instead.", storage.getPath()));
            tasks = new TaskList();
        }
        this.tasks = tasks;
    }

    public void run() {
        Scanner in = new Scanner(System.in);

        printMessage(String.join(System.lineSeparator(), new String[]{
                " Hello! I'm DukeBot",
                " What can I do for you?"
        }));

        do {
            String input = in.nextLine();
            try {
                if (handleInput(storage, tasks, input) == ProgramAction.EXIT) {
                    break;
                }
            } catch (DukeException e) {
                if (e instanceof UnknownCommandDukeException) {
                    printMessage(" OOPS!!! I'm sorry, but I don't know what that means :-(");
                } else {
                    printMessage(" OOPS!!! " + e.getMessage());
                }
            } catch (Exception e) {
                printMessage(" OOPS!!! An unknown exception occurred.");
            }
        } while (true);
    }

    public static void main(String[] args) {
        Duke duke = new Duke("./data/duke.txt");
        duke.run();
    }

    private static ProgramAction handleInput(Storage storage, TaskList tasks, String input) throws DukeException {
        String commandName = input.trim().split(" ", -1)[0];
        DuckCommandHandler commandHandler = commandHandlerMap.getOrDefault(commandName, null);
        if (commandHandler != null) {
            String commandArgs = input.substring(commandName.length()).trim();
            commandHandler.apply(tasks, commandArgs);
            storage.save(tasks);
            if (commandName.equals(DuckCommand.BYE.toString())) {
                return ProgramAction.EXIT;
            }
        } else {
            throw new UnknownCommandDukeException("Input: " + input);
        }
        return ProgramAction.CONTINUE;
    }

    private static void handleByeCommand(TaskList tasks, String args) throws InvalidCommandArgsDukeException {
        if (!args.isEmpty()) {
            throw new InvalidCommandArgsDukeException("The bye command should not take any arguments.");
        }

        printMessage(" Bye. Hope to see you again soon!");
    }

    private static void handleListCommand(TaskList tasks, String args) throws InvalidCommandArgsDukeException {
        if (!args.isEmpty()) {
            throw new InvalidCommandArgsDukeException("The list command should not take any arguments.");
        }

        String[] lines = new String[tasks.size()];
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.getTask(i);
            lines[i] = String.format(" %d.%s", i + 1, task.toString());
        }
        printMessage(String.join(System.lineSeparator(), lines), true);
    }

    private static void handleMarkCommand(TaskList tasks, String args) throws InvalidCommandArgsDukeException {
        if (args.isEmpty()) {
            throw new InvalidCommandArgsDukeException("The task number to be marked cannot be empty.");
        }

        Integer taskNumber = tryParseInt(args);
        if (taskNumber == null || taskNumber < 1 || taskNumber > tasks.size()) {
            throw new InvalidCommandArgsDukeException("Invalid task number to be marked.");
        }

        Task task = tasks.getTask(taskNumber - 1);
        task.setDone(true);
        printMessage(String.join(System.lineSeparator(), new String[]{
                " Nice! I've marked this task as done:",
                "   " + task.toString()
        }));
    }

    private static void handleUnmarkCommand(TaskList tasks, String args) throws InvalidCommandArgsDukeException {
        if (args.isEmpty()) {
            throw new InvalidCommandArgsDukeException("The task number to be unmarked cannot be empty.");
        }

        Integer taskNumber = tryParseInt(args);
        if (taskNumber == null || taskNumber < 1 || taskNumber > tasks.size()) {
            throw new InvalidCommandArgsDukeException("Invalid task number to be unmarked.");
        }

        Task task = tasks.getTask(taskNumber - 1);
        task.setDone(false);
        printMessage(String.join(System.lineSeparator(), new String[]{
                " OK, I've marked this task as not done yet:",
                "   " + task.toString()
        }));
    }

    private static void handleTodoCommand(TaskList tasks, String args) throws InvalidCommandArgsDukeException {
        if (args.isEmpty()) {
            throw new InvalidCommandArgsDukeException("The description of a todo cannot be empty.");
        }

        Todo toto = new Todo(args);
        tasks.addTask(toto);
        printTaskAddedMessage(toto, tasks.size());
    }

    private static void handleDeadlineCommand(TaskList tasks, String args) throws InvalidCommandArgsDukeException {
        if (args.isEmpty()) {
            throw new InvalidCommandArgsDukeException("The description of a deadline cannot be empty.");
        }

        String[] array = args.split(" /by ", -1);
        if (array.length != 2) {
            throw new InvalidCommandArgsDukeException("The \"/by {date/time}\" of a deadline is required.");
        }

        Deadline deadline = new Deadline(array[0], array[1]);
        tasks.addTask(deadline);
        printTaskAddedMessage(deadline, tasks.size());
    }

    private static void handleEventCommand(TaskList tasks, String args) throws InvalidCommandArgsDukeException {
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
        tasks.addTask(event);
        printTaskAddedMessage(event, tasks.size());
    }

    private static void handleDeleteCommand(TaskList tasks, String args) throws InvalidCommandArgsDukeException {
        if (args.isEmpty()) {
            throw new InvalidCommandArgsDukeException("The task number to be deleted cannot be empty.");
        }

        Integer taskNumber = tryParseInt(args);
        if (taskNumber == null || taskNumber < 1 || taskNumber > tasks.size()) {
            throw new InvalidCommandArgsDukeException("Invalid task number to be deleted.");
        }

        Task task = tasks.getTask(taskNumber - 1);
        tasks.removeTask(task);
        printTaskDeletedMessage(task, tasks.size());
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

    private static void printTaskAddedMessage(Task addedTask, int tasksLatestSize) {
        printMessage(String.join(System.lineSeparator(), new String[]{
                " Got it. I've added this task:",
                "   " + addedTask.toString(),
                String.format(" Now you have %d tasks in the list.", tasksLatestSize)
        }));
    }

    private static void printTaskDeletedMessage(Task deletedTask, int tasksLatestSize) {
        printMessage(String.join(System.lineSeparator(), new String[]{
                " Noted. I've removed this task:",
                "   " + deletedTask.toString(),
                String.format(" Now you have %d tasks in the list.", tasksLatestSize)
        }));
    }

    private static Integer tryParseInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return null;
        }
    }
}
