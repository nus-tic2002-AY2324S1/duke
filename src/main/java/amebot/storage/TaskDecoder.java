package amebot.storage;

import amebot.common.Messages;
import amebot.enumerations.Keyword;
import amebot.commands.AddCommand;

/**
 * TaskDecoder class decodes task from the storage file and
 * add it as a task object.
 */
public class TaskDecoder {
    private static final String MARKED = "✓";

    /**
     * Decodes task from the storage file and
     * add it as a task object.
     *
     * @param task Task to be decoded.
     */
    public static void decodeTask(String task) {
        String[] taskDetail = task.toLowerCase().split("\\|");

        String type = taskDetail[0].replaceAll("\\[", "").replaceAll("]", "");
        Keyword commandType;
        try {
            commandType = Keyword.valueOf(type.trim().toUpperCase());
        } catch (IllegalArgumentException err) {
            commandType = Keyword.INVALID;
        }

        boolean isStatusMarked = taskDetail[1].contains(MARKED);
        String description = taskDetail[2];

        switch (commandType) {
        case TODO:
            new AddCommand(isStatusMarked, description);
            break;
        case EVENT:
            String fromDateTime = taskDetail[3];
            String toDateTime = taskDetail[4];
            new AddCommand(isStatusMarked, description, fromDateTime, toDateTime);
            break;
        case DEADLINE:
            String dueDateTime = taskDetail[3];
            new AddCommand(isStatusMarked, description, dueDateTime);
            break;
        default:
            System.out.println(Messages.ERROR_MESSAGE);
            break;
        }
    }
}
