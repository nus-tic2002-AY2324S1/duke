package nus.duke.storage;

import nus.duke.data.TaskList;
import nus.duke.data.tasks.Deadline;
import nus.duke.data.tasks.Event;
import nus.duke.data.tasks.Task;
import nus.duke.data.tasks.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;

public class TaskListDecoder {
    public static TaskList decodeTaskList(Collection<String> encodedTaskList) throws StorageOperationException {
        ArrayList<Task> decodedTasks = new ArrayList<>();
        for (String encodedTask : encodedTaskList) {
            decodedTasks.add(decodeTaskFromString(encodedTask));
        }
        return new TaskList(decodedTasks);
    }

    private static Task decodeTaskFromString(String encodedTask) throws StorageOperationException {
        String[] fields = encodedTask.split("\\s*\\|\\s*", -1);
        if (fields.length < 3) {
            throw new StorageOperationException("The number of fields should be grater than 3.");
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
        case "E":
            if (fields.length != 4) {
                throw new StorageOperationException("The number of fields for deadline should be 4.");
            }
            String eventFromTo = fields[3];
            String[] fromToFields = eventFromTo.split(" -> ", -1);
            if (fromToFields.length != 2) {
                throw new StorageOperationException("The from-to field is invalid.");
            }
            LocalDateTime eventFrom = decodeDateTime(fromToFields[0]);
            LocalDateTime eventTo = decodeDateTime(fromToFields[1]);
            return new Event(description, eventFrom, eventTo, isDone);
        case "T": {
            return new Todo(description, isDone);
        }
        default:
            throw new StorageOperationException("Unknown task type.");
        }
    }

    private static boolean decodeIsDoneFromString(String value) throws StorageOperationException {
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
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        try {
            return LocalDateTime.parse(text, dateTimeFormatter);
        } catch (Exception e) {
            throw new StorageOperationException("Invalid DateTime format.");
        }
    }
}
