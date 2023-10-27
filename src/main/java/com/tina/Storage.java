package com.tina;

import com.tina.exception.InvalidFileFormatException;
import com.tina.exception.InvalidFilePathException;
import com.tina.task.Task;
import com.tina.task.TaskList;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;

public class Storage {

    private final Path path;

    public Storage(Path path) {
        this.path = path;
    }

    public ArrayList<Task> load() throws InvalidFileFormatException, InvalidFilePathException {
        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path.toFile()))) {
            String line = br.readLine();
            while (line != null) {
                Task task = Parser.parseStorageToTask(line);
                tasks.add(task);
                line = br.readLine();
            }
        } catch (IOException e) {
            throw new InvalidFilePathException(path);
        }

        return tasks;
    }

    public void save(TaskList taskList) throws InvalidFilePathException {
        ArrayList<String> tasks = Parser.parseTasksToStorage(taskList);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path.toFile()))) {
            for (String line : tasks) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            throw new InvalidFilePathException(path);
        }
    }
}
