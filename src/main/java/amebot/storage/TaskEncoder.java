package amebot.storage;

import amebot.common.Messages;
import amebot.enumerations.Keyword;
import amebot.tasks.Task;
import amebot.tasks.Event;
import amebot.tasks.Deadline;

/**
 * TaskEncoder class encodes Task object into a string.
 */
public class TaskEncoder {
    private static final String DELIMITER = "|";

    /**
     * Returns encoded Task object as string.
     *
     * @param task Task to be encoded.
     * @return Encoded task.
     */
    public static String encodeTask(Task task) {
        String taskDetail = "";

        String type = task.getType().replaceAll("\\[", "").replaceAll("]", "");
        Keyword commandType;
        try {
            commandType = Keyword.valueOf(type.trim().toUpperCase());
        } catch (IllegalArgumentException err) {
            commandType = Keyword.INVALID;
        }

        String status = task.getStatus();
        String description = task.getDescription();

        switch (commandType) {
        case TODO:
            taskDetail = type + DELIMITER + status + DELIMITER + description;
            break;
        case EVENT:
            String fromDateTime = ((Event) task).getFromDateTime();
            String toDateTime = ((Event) task).getToDateTime();
            taskDetail = type + DELIMITER + status + DELIMITER + description + DELIMITER + fromDateTime + DELIMITER + toDateTime;
            break;
        case DEADLINE:
            String dueDateTime = ((Deadline) task).getDueDateTime();
            taskDetail = type + DELIMITER + status + DELIMITER + description + DELIMITER + dueDateTime;
            break;
        default:
            System.out.println(Messages.ERROR_MESSAGE);
            break;
        }

        return taskDetail;
    }
}
