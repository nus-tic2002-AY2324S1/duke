package commands;

import common.Messages;
import enumerations.Keyword;
import tasks.Task;

import java.util.ArrayList;

public class Command {
    protected static ArrayList<Task> tasks = new ArrayList<>();
    protected static ArrayList<String> logs;

    public ArrayList<String> executeCommand(ArrayList<String> parsedCommand) {
        logs = new ArrayList<>();

        if (!parsedCommand.isEmpty()) {
            Keyword commandType = Keyword.valueOf(parsedCommand.get(0));
            String description;
            int index;

            switch (commandType) {
                case TODO:
                    description = parsedCommand.get(1);
                    new AddCommand(description);
                    break;
                case EVENT:
                    description = parsedCommand.get(1);
                    String fromDateTime = parsedCommand.get(2);
                    String toDateTime = parsedCommand.get(3);
                    new AddCommand(description, fromDateTime, toDateTime);
                    break;
                case DEADLINE:
                    description = parsedCommand.get(1);
                    String dueTime = parsedCommand.get(2);
                    new AddCommand(description, dueTime);
                    break;
                case SELECT:
                    // Fallthrough
                case UNSELECT:
                    index = Integer.parseInt(parsedCommand.get(1));
                    new UpdateCommand(index, commandType.toString());
                    break;
                case REMOVE:
                    index = Integer.parseInt(parsedCommand.get(1));
                    new DeleteCommand(index);
                    break;
                case LIST:
                    new ListCommand();
                    break;
                case BYE:
                    new ExitCommand();
                    logs.add(Messages.EXIT_MESSAGE);
                    break;
            }
        }

        return logs;
    }

    public static ArrayList<Task> getTasks() {
        return tasks;
    }
}
