package nus.duke.storage;

import nus.duke.data.TaskList;
import nus.duke.data.tasks.AbstractTask;
import nus.duke.data.tasks.Deadline;
import nus.duke.data.tasks.Event;
import nus.duke.data.tasks.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;

/**
 * The `TaskListDecoder` class is responsible for decoding a collection of encoded task representations
 * into a `TaskList`. Each encoded task is a string that is parsed to reconstruct the corresponding task object.
 */
public class TaskListDecoder {
    /**
     * Decodes a collection of encoded task representations and constructs a `TaskList` from them.
     *
     * @param encodedTaskList The collection of encoded task representations.
     * @return A `TaskList` containing the decoded tasks.
     * @throws StorageOperationException If there's an issue with decoding or parsing the data.
     */
    public static TaskList decodeTaskList(Collection<String> encodedTaskList) throws StorageOperationException {
        assert encodedTaskList != null;

        ArrayList<AbstractTask> decodedTasks = new ArrayList<>();
        for (String encodedTask : encodedTaskList) {
            decodedTasks.add(decodeTaskFromString(encodedTask));
        }
        return new TaskList(decodedTasks);
    }

    private static AbstractTask decodeTaskFromString(String encodedTask) throws StorageOperationException {
        assert encodedTask != null;

        String[] fields = encodedTask.split("\\s*\\|\\s*", -1);
        if (fields.length < 3) {
            throw new StorageOperationException("The number of fields should be greater than 3.");
        }

        String type = fields[0];
        boolean isDone = decodeIsDoneFromString(fields[1]);
        String description = fields[2];
        switch (type) {
        case "D": {
            if (fields.length != 4) {
                throw new StorageOperationException("The number of fields for deadline should be 4.");
            }
            LocalDateTime deadlineBy = decodeDateTime(fields[3]);
            return new Deadline(description, deadlineBy, isDone);
        }
        case "E": {
            if (fields.length != 4) {
                throw new StorageOperationException("The number of fields for an event should be 4.");
            }
            String eventFromTo = fields[3];
            String[] fromToFields = eventFromTo.split(" -> ", -1);
            if (fromToFields.length != 2) {
                throw new StorageOperationException("The from-to field is invalid.");
            }
            LocalDateTime eventFrom = decodeDateTime(fromToFields[0]);
            LocalDateTime eventTo = decodeDateTime(fromToFields[1]);
            return new Event(description, eventFrom, eventTo, isDone);
        }
        case "T": {
            return new Todo(description, isDone);
        }
        default:
            throw new StorageOperationException("Unknown task type.");
        }
    }

    private static boolean decodeIsDoneFromString(String value) throws StorageOperationException {
        assert value != null;

        switch (value) {
        case "1":
            return true;
        case "0":
            return false;
        default:
            throw new StorageOperationException("The isDone field should be 0 or 1.");
        }
    }

    private static LocalDateTime decodeDateTime(String text) throws StorageOperationException {
        assert text != null;

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        try {
            return LocalDateTime.parse(text, dateTimeFormatter);
        } catch (Exception e) {
            throw new StorageOperationException("Invalid DateTime format.");
        }
    }
}
