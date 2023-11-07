package storage;

import exceptions.IllegalStorageFormat;
import task.*;
import wargames.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.SplittableRandom;

public class Storage {

    private final String FILEPATH = "data/joshua.txt";

    public Storage() {

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
            Task task = parseTaskFromTxt(scanner.nextLine());
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
    private Task parseTaskFromTxt(String txtLine) throws IllegalStorageFormat {
        ArrayList<String> lineArrayList = textToArrayList(txtLine);
        if (lineArrayList.size() < 3) {
            throw new IllegalStorageFormat("This line in joshua.txt does not meet the required format: " + txtLine);
        }

        String taskType = lineArrayList.get(0);
        boolean isDone = lineArrayList.get(1).equals("1");
        String desc = lineArrayList.get(2);
        String from = null;
        String to = null;

        switch (taskType) {
            case "T":
                taskType = "todo";
                break;
            case "D":
                taskType = "deadline";
                from = lineArrayList.get(3);
                break;
            case "E":
                taskType = "event";
                from = lineArrayList.get(3);
                to = lineArrayList.get(4);
                break;
            default:
                throw new IllegalStorageFormat("Could not detect task type.");
        }

        return TaskFactory.createTask(taskType, isDone, desc, from, to);
    }

    /**
     * Takes in a string and splits it by the "|" (pipe) symbol
     *
     * @param txtLine a line from stored file joshua.txt
     * @return An ArrayList of the parts of the command
     */
    private ArrayList<String> textToArrayList(String txtLine) {
        String[] items = txtLine.split(" \\| "); // Split on | symbol
        ArrayList<String> itemList = new ArrayList<>(Arrays.asList(items));
        // Remove leading and trailing spaces from each item
        itemList.replaceAll(String::trim);
        return itemList;
    }

    public void save(TaskList tasklist) throws IOException {
        FileWriter fw = new FileWriter(FILEPATH);
        for (int i = 0; i < tasklist.listSize(); i++) {
            Task task = tasklist.getItem(i);
            String encodedTask = encodeTaskForStorage(task);
            fw.write(encodedTask);
        }
        fw.close();
    }

    private String encodeTaskForStorage(Task task) {
        final StringBuilder encodedTaskBuilder = new StringBuilder();
        String taskType = encodeTaskType(task);

        encodedTaskBuilder.append(taskType).append(" | ");
        encodedTaskBuilder.append(task.getIsDone() ? "1" : "0").append(" | ");
        encodedTaskBuilder.append(task.getDesc());

        if (taskType.equals("D")) {
            Deadline deadline = (Deadline) task;
            encodedTaskBuilder.append(" | ").append(deadline.getBy());
        }
        else if (taskType.equals("E")) {
            Event event = (Event) task;
            encodedTaskBuilder.append(" | ").append(event.getFrom()).append(" | ").append(event.getTo());
        }

        encodedTaskBuilder.append("\n");

        return encodedTaskBuilder.toString();
    }

    private String encodeTaskType(Task task) {
        String taskType = "";
        if (task.getClass().equals(ToDo.class)) {
            taskType = "T";
        }
        else if (task.getClass().equals(Deadline.class)) {
            taskType = "D";
        }
        else if (task.getClass().equals(Event.class)) {
            taskType = "E";
        }
        return taskType;
    }
}
