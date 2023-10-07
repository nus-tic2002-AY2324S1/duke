package io;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

public class TaskStorage {
    String filePath;
    String directoryPath = "./data";
    File folder = new File(directoryPath);
    File file = new File("./data/craby.txt");

    public TaskStorage(String filePath) {
        this.filePath = filePath;
    }

    public void save(List<Task> tasks) {
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
    public List<Task> load() {
        List<Task> tasks = new ArrayList<>();
        if (file.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(filePath));
                String line;
                while ((line = reader.readLine()) != null) {
                    char checkTask = line.charAt(1);
                    boolean isDone = false;
                    if (line.charAt(5) == '✓') {
                        isDone = true;
                    }
                    String content = line.substring(9);
                    switch (checkTask) {
                        case 'D':
                            String[] formatDeadline = content.split(" \\(by:");
                            String tmp = formatDeadline[1].substring(0,formatDeadline[1].length()-1);
                            Deadline deadline = new Deadline(formatDeadline[0], tmp);
                            deadline.setDone(isDone);
                            tasks.add(deadline);
                            break;
                        case 'E':
                            String[] formatEvent = content.split(" \\(from:");
                            String time = formatEvent[1];
                            time = time.substring(0, time.length() - 1);
                            Event event = new Event(formatEvent[0], time);
                            event.setDone(isDone);
                            tasks.add(event);
                            break;
                        default:
                            Todo todo = new Todo(content);
                            todo.setDone(isDone);
                            tasks.add(todo);
                    }
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            folder.mkdir();
            save(tasks);
        }
        return tasks;
    }
}
