package nus.duke.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import nus.duke.data.TaskAfterOption;
import nus.duke.data.TaskList;
import nus.duke.data.tasks.AbstractTask;
import nus.duke.data.tasks.Deadline;
import nus.duke.data.tasks.Event;
import nus.duke.data.tasks.Todo;
import nus.duke.util.IntegerUtils;

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
        String type = fields[0];
        switch (type) {
        case "D":
            return decodeDeadline(fields);
        case "E":
            return decodeEvent(fields);
        case "T":
            return decodeTodo(fields);
        default:
            throw new StorageOperationException("Unknown task type.");
        }
    }

    private static Deadline decodeDeadline(String[] fields) throws StorageOperationException {
        assert fields != null;

        if (fields.length != 5) {
            throw new StorageOperationException("The number of fields for deadline should be 5.");
        }

        boolean isDone = decodeIsDoneFromString(fields[1]);
        String description = fields[2];
        TaskAfterOption afterOption = decodeAfterOption(fields[fields.length - 1]);
        LocalDateTime deadlineBy = decodeDateTime(fields[3]);
        Deadline deadline = new Deadline(description, deadlineBy, isDone);
        deadline.setAfterOption(afterOption);
        return deadline;
    }

    private static Event decodeEvent(String[] fields) throws StorageOperationException {
        assert fields != null;

        if (fields.length != 5) {
            throw new StorageOperationException("The number of fields for an event should be 5.");
        }

        boolean isDone = decodeIsDoneFromString(fields[1]);
        String description = fields[2];
        TaskAfterOption afterOption = decodeAfterOption(fields[fields.length - 1]);
        String eventFromTo = fields[3];
        String[] fromToFields = eventFromTo.split(" -> ", -1);
        if (fromToFields.length != 2) {
            throw new StorageOperationException("The from-to field is invalid.");
        }
        LocalDateTime eventFrom = decodeDateTime(fromToFields[0]);
        LocalDateTime eventTo = decodeDateTime(fromToFields[1]);
        Event event = new Event(description, eventFrom, eventTo, isDone);
        event.setAfterOption(afterOption);
        return event;
    }

    private static Todo decodeTodo(String[] fields) throws StorageOperationException {
        assert fields != null;

        if (fields.length != 4) {
            throw new StorageOperationException("he number of fields for an event should be 4.");
        }

        boolean isDone = decodeIsDoneFromString(fields[1]);
        String description = fields[2];
        TaskAfterOption afterOption = decodeAfterOption(fields[fields.length - 1]);
        Todo todo = new Todo(description, isDone);
        todo.setAfterOption(afterOption);
        return todo;
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

    private static TaskAfterOption decodeAfterOption(String text) throws StorageOperationException {
        assert text != null;

        if (text.isEmpty()) {
            return null;
        }

        Integer taskNumber = IntegerUtils.tryParseInt(text);
        if (taskNumber != null) {
            return new TaskAfterOption(taskNumber);
        }

        try {
            LocalDateTime dateTime = decodeDateTime(text);
            return new TaskAfterOption(dateTime);
        } catch (Exception e) {
            throw new StorageOperationException("Invalid after option format.");
        }
    }
}
