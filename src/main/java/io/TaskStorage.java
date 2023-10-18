package io;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

import java.util.ArrayList;
import java.util.List;


public class TaskStorage {
    String filePath;
    private final String DIRECTORY_PATH = "./data";
    File folder = new File(DIRECTORY_PATH);
    File file;


    public TaskStorage(String fileName) {
        file = new File(DIRECTORY_PATH + "/" + fileName);
        this.filePath = DIRECTORY_PATH + "/" + fileName;
    }
    /**
     * This method will save the tasks into the file.
     * @param tasks the list of tasks.
     */
    public void save(List<Task> tasks) {
        if (!folder.exists()) {
            folder.mkdir();
        }
        try {
            StringBuilder content = new StringBuilder();
            for (Task task : tasks) {
                content.append(task.toStorageString());
                content.append(System.lineSeparator());
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath));
            writer.write(content.toString());
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * This method will load the tasks from the file.
     * @return the list of tasks.
     */
    public List<Task> load() {
        List<Task> tasks = new ArrayList<>();
        if (file.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(filePath));
                loadLine(reader, tasks);
                reader.close();
            } catch (IOException e) {;
                e.printStackTrace();
            }
        } else {
            save(tasks);
        }
        return tasks;
    }

    private static void loadLine(BufferedReader reader, List<Task> tasks) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            boolean isDone = isDone(line);
            addByType(tasks, line, isDone);
        }
    }

    private static void addByType(List<Task> tasks, String line, boolean isDone) {
        char checkType = line.charAt(1);
        String content = line.substring(9);
        switch (checkType) {
            case 'D':
                String[] formatDeadline = content.split(" \\(by:");
                String tmp = formatDeadline[1].substring(0, formatDeadline[1].length() - 1);
                Deadline deadline = new Deadline(formatDeadline[0], tmp);
                deadline.setIsDone(isDone);
                tasks.add(deadline);
                break;
            case 'E':
                String[] formatEvent = content.split(" \\(from:");
                String time = formatEvent[1];
                time = time.substring(0, time.length() - 1);
                Event event;
                if (time.contains("to:")) {
                    String[] tmp1 = time.split(" to: ");
                    event = new Event(formatEvent[0], tmp1[0], tmp1[1]);
                } else {
                    event = new Event(formatEvent[0], time);
                }
                event.setIsDone(isDone);
                tasks.add(event);
                break;
            default:
                Todo todo = new Todo(content);
                todo.setIsDone(isDone);
                tasks.add(todo);
        }
    }
    private static boolean isDone(String line) {
        boolean isDone = false;
        if (line.charAt(5) == '✓') {
            isDone = true;
        }
        return isDone;
    }
}
