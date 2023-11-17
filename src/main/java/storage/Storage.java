package storage;

import task.Task;
import task.ToDo;
import task.Deadline;
import task.Event;
import joshua.TaskList;
import exceptions.IllegalStorageFormat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Saves and loads tasks for the existing task list.
 */
public class Storage {
    public static final Pattern BASIC_STORED_TASK_PATTERN = Pattern.compile("(?<taskType>[^|]) \\| (?<isDone>[^|]) \\| (?<args>.*)");
    public static final Pattern STORED_DEADLINE_ARGS_PATTERN = Pattern.compile("(?<desc>[^|]*) \\| (?<by>[^|]*)");
    public static final Pattern STORED_EVENT_ARGS_PATTERN = Pattern.compile("(?<desc>[^|]*) \\| (?<from>[^|]*) \\| (?<to>[^|]*)");

    private final String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Writes the task list from the latest session into joshua.txt
     *
     * @param taskList The task list from the latest session of running the Joshua program.
     * @throws IOException If error occurs when writing lines to stored file.
     */
    public void save(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(filepath);
        for (int i = 0; i < taskList.listSize(); i++) {
            int taskNum = i + 1;
            Task task = taskList.getTask(taskNum);
            fw.write(task.toStorageString() + "\n");
        }
        fw.close();
    }

    /**
     * Reads the stored task list in joshua.txt and returns it as a TaskList object
     *
     * @return TaskList object with all the stored Tasks loaded.
     * @throws FileNotFoundException If the storage file is not found.
     * @throws IllegalStorageFormat If the storage file has an illegal format.
     */
    public TaskList load() throws FileNotFoundException, IllegalStorageFormat {
        File file = new File(filepath);
        Scanner scanner = new Scanner(file);
        TaskList tasklist = new TaskList();

        while (scanner.hasNext()) {
            Task task = parseTaskFromStorage(scanner.nextLine());
            tasklist.addToTaskList(task);
        }

        scanner.close();
        return tasklist;
    }

    /**
     * Reads a line from the text file, determines the type of Task, and returns that Task (ToDo, Deadline or Event)
     * Eg: E | 0 | project meeting | Aug 6th 2pm | Aug 6th 4pm
     * <p>
     * The method checks if the line matches the basic stored task pattern (ie: taskType | isDone | args)
     * and passes the args to their respective constructor or "prepare" methods to create their Task objects.
     *
     * @param txtLine A line from stored file joshua.txt.
     * @return A new task object of the specified type (Todo, Deadline, or Event).
     * @throws IllegalStorageFormat If an unsupported task type is provided.
     */
    private Task parseTaskFromStorage(String txtLine) throws IllegalStorageFormat {
        Matcher matcher = BASIC_STORED_TASK_PATTERN.matcher(txtLine);
        if(!matcher.matches()) {
            throw new IllegalStorageFormat("This line in joshua.txt does not meet the required format: " + txtLine);
        }

        String taskType = matcher.group("taskType");
        boolean isDone = matcher.group("isDone").equals("1");
        String args = matcher.group("args");

        switch (taskType) {
        case "T":
            return new ToDo(args, isDone);
        case "D":
            return prepareDeadline(isDone, args);
        case "E":
            return prepareEvent(isDone, args);
        default:
            throw new IllegalStorageFormat("Could not detect task type.");
        }
    }

    /**
     * Returns a Deadline object instantiated with the task description, "isDone" status and
     * "by" date as parsed from the storedArgs.
     *
     * @param isDone The task has been marked done.
     * @param storedArgs The arguments from a line in storage.
     * @return A Deadline object.
     * @throws IllegalStorageFormat If the stored string is of illegal format.
     */
    private Task prepareDeadline(boolean isDone, String storedArgs) throws IllegalStorageFormat {
        Matcher matcher = STORED_DEADLINE_ARGS_PATTERN.matcher(storedArgs);
        if(!matcher.matches()) {
            throw new IllegalStorageFormat("Invalid arguments for deadline: " + storedArgs);
        }

        String desc = matcher.group("desc").trim();
        String by = matcher.group("by").trim();

        if (desc.isEmpty()) {
            throw new IllegalStorageFormat("The stored description for this deadline is empty: " + storedArgs);
        }
        if (by.isEmpty()) {
            throw new IllegalStorageFormat("The stored \"by\" date for this deadline is empty: " + storedArgs);
        }
        return new Deadline(desc, isDone, by);
    }

    /**
     * Returns an Event object instantiated with the task description, "isDone" status,
     * "from" date and "to" date as parsed from the storedArgs.
     *
     * @param isDone The task has been marked done.
     * @param storedArgs The arguments from a line in storage.
     * @return An Event object.
     * @throws IllegalStorageFormat If the stored string is of illegal format.
     */
    private Task prepareEvent(boolean isDone, String storedArgs) throws IllegalStorageFormat {
        Matcher matcher = STORED_EVENT_ARGS_PATTERN.matcher(storedArgs);
        if(!matcher.matches()) {
            throw new IllegalStorageFormat("Invalid arguments for event: " + storedArgs);
        }

        String desc = matcher.group("desc").trim();
        String from = matcher.group("from").trim();
        String to = matcher.group("to").trim();

        if (desc.isEmpty()) {
            throw new IllegalStorageFormat("The stored description for this event is empty: " + storedArgs);
        }
        if (from.isEmpty()) {
            throw new IllegalStorageFormat("The stored \"from\" date for this event is empty: " + storedArgs);
        }
        if (to.isEmpty()) {
            throw new IllegalStorageFormat("The stored \"to\" date for this event is empty: " + storedArgs);
        }
        return new Event(desc, isDone, from, to);
    }
}
