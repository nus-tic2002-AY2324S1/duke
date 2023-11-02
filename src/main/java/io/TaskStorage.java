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
     * Sends the list of tasks as input and save the tasks into the file.
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

    /**
     * Returns the list of tasks loaded from the file.
     * load the line from the file
     * line format: type || status || description || time (optional) || time (optional)
     * type: T, D, E
     * status: 0, 1
     * description: String
     * time: yyyy/MM/dd HHmm
     *
     * @param reader the reader to read the file
     *               the list of tasks
     *               the line from the file
     * @param tasks  the list of tasks to store the tasks
     */
    private static void loadLine(BufferedReader reader, List<Task> tasks) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] split = line.split("\\|\\|");
            String type = split[0].trim();
            String status = split[1].trim();
            String description = split[2].trim();
            switch (type) {
            case "T":
                tasks.add(new Todo(description));
                break;
            case "D":
                String time = split[3].trim();
                tasks.add(new Deadline(description, time));
                break;
            case "E":
                String startTime = split[3].trim();
                if (split.length == 5) {
                    String endTime = split[4].trim();
                    tasks.add(new Event(description, startTime, endTime));
                    break;
                }
                tasks.add(new Event(description, startTime));
                break;
            default:
                break;
            }
            if (status.equals("1")) {
                tasks.get(tasks.size() - 1).setDone(true);
            }
        }
    }

}
