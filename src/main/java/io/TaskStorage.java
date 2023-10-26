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
     * If the file does not exist, it will create a new file.
     * If the file is empty, it will delete the file.
     *
     * @param tasks the list of tasks.
     */
    public void save(List<Task> tasks) {
        if (!folder.exists()) {
            boolean mkdir = folder.mkdir();
            assert mkdir;
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
        // delete the file if it is empty
        if (file.exists() && file.length() == 0) {
            file.delete();
        }
    }

    /**
     * This method will load the tasks from the file same name as the username.
     * If the file does not exist, it will create a new file.
     *
     * @return the list of tasks.
     */
    public List<Task> load() {
        List<Task> tasks = new ArrayList<>();
        if (file.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(filePath));
                loadLine(reader, tasks);
                reader.close();
            } catch (IOException e) {
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
        assert line.length() > 9;
        char checkType = line.charAt(1);
        String content = line.substring(9);
        switch (checkType) {
        case 'D':
            Deadline deadline = getDeadline(isDone, content);
            tasks.add(deadline);
            break;
        case 'E':
            Event event = getEvent(isDone, content);
            tasks.add(event);
            break;
        default:
            Todo todo = new Todo(content);
            todo.setIsDone(isDone);
            tasks.add(todo);
        }
    }

    private static Deadline getDeadline(boolean isDone, String content) {
        String[] formatDeadline = content.split(" \\(by:");
        assert formatDeadline.length > 0;
        String tmp = formatDeadline[1].substring(0, formatDeadline[1].length() - 1);
        Deadline deadline = new Deadline(formatDeadline[0], tmp);
        deadline.setIsDone(isDone);
        return deadline;
    }

    private static Event getEvent(boolean isDone, String content) {
        String[] formatEvent = content.split(" \\(from:");
        assert formatEvent.length > 0;
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
        return event;
    }

    private static boolean isDone(String line) {
        boolean isDone = false;
        assert line.length() > 5;
        if (line.charAt(5) == '✓') {
            isDone = true;
        }
        return isDone;
    }
}
