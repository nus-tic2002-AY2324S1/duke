package storage;

import commands.InvalidCommand;
import task.Task;
import task.ToDo;
import task.Deadline;
import task.Event;
import wargames.TaskList;
import exceptions.IllegalStorageFormat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Storage {
    public static final Pattern BASIC_STORED_TASK_PATTERN = Pattern.compile("(?<taskType>[^|]) \\| (?<isDone>[^|]) \\| (?<args>.*)");
    public static final Pattern STORED_DEADLINE_ARGS_PATTERN = Pattern.compile("(?<desc>[^|]*) \\| (?<by>[^|]*)");
    public static final Pattern STORED_EVENT_ARGS_PATTERN = Pattern.compile("(?<desc>[^|]*) \\| (?<from>[^|]*) \\| (?<to>[^|]*)");

    private final String FILEPATH = "./data/joshua.txt";

    public Storage() {

    }

    /**
     * Writes the task list from the latest session into joshua.txt
     *
     * @param taskList The task list from the latest session of running the Joshua program
     * @throws IOException If error occurs when writing lines to stored file
     */
    public void save(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(FILEPATH);
        for (int i = 0; i < taskList.listSize(); i++) {
            Task task = taskList.getItem(i);
            fw.write(task.toStorageString() + "\n");
        }
        fw.close();
    }

    /**
     * Reads the stored task list in joshua.txt and returns it as a TaskList object
     *
     * @return TaskList object with all the stored Tasks loaded
     * @throws FileNotFoundException If file cannot be found at the stated filepath
     * @throws IllegalStorageFormat If file is not formatted correctly
     */
    public TaskList load() throws FileNotFoundException, IllegalStorageFormat {
        File file = new File(FILEPATH);
        Scanner scanner = new Scanner(file);
        TaskList tasklist = new TaskList();

        while (scanner.hasNext()) {
            Task task = parseTaskFromStorage(scanner.nextLine());
            tasklist.addToTaskList(task);
        }

        return tasklist;
    }

    /**
     * Reads a line from the text file, determines the type of Task, and returns that Task (ToDo, Deadline or Event)
     * Eg: E | 0 | project meeting | Aug 6th 2pm | Aug 6th 4pm
     *
     * @param txtLine a line from stored file joshua.txt
     * @return A new task object of the specified type (Todo, Deadline, or Event)
     * @throws IllegalStorageFormat If an unsupported task type is provided
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

    private Task prepareDeadline(boolean isDone, String storedArgs) throws IllegalStorageFormat {
        Matcher matcher = STORED_DEADLINE_ARGS_PATTERN.matcher(storedArgs);
        if(!matcher.matches()) {
            throw new IllegalStorageFormat("Invalid arguments for deadline: " + storedArgs);
        }

        String desc = matcher.group("desc").trim();
        String by = matcher.group("by").trim();

        if (desc.isEmpty()) {
            throw new IllegalStorageFormat("The description for this deadline is empty: " + storedArgs);
        }
        if (by.isEmpty()) {
            throw new IllegalStorageFormat("The \"by\" date for this deadline is empty: " + storedArgs);
        }
        return new Deadline(desc, isDone, by);
    }

    private Task prepareEvent(boolean isDone, String storedArgs) throws IllegalStorageFormat {
        Matcher matcher = STORED_EVENT_ARGS_PATTERN.matcher(storedArgs);
        if(!matcher.matches()) {
            throw new IllegalStorageFormat("Invalid arguments for event: " + storedArgs);
        }

        String desc = matcher.group("desc").trim();
        String from = matcher.group("from").trim();
        String to = matcher.group("to").trim();

        if (desc.isEmpty()) {
            throw new IllegalStorageFormat("The description for this event is empty: " + storedArgs);
        }
        if (from.isEmpty()) {
            throw new IllegalStorageFormat("The \"from\" date for this event is empty: " + storedArgs);
        }
        if (to.isEmpty()) {
            throw new IllegalStorageFormat("The \"to\" date for this event is empty: " + storedArgs);
        }
        return new Event(desc, isDone, from, to);
    }
}
