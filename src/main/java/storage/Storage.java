package storage;

import data.TaskList;
import storage.StorageOperationException;
import storage.TaskListDecoder;
import storage.TaskListEncoder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;

public class Storage {
    private final Path path;

    public Storage(String filePath) {
        if (!isValidPath(filePath)) {
            throw new IllegalArgumentException("Storage file should end with '.txt'");
        }

        this.path = Paths.get(filePath);
    }

    public Path getPath() {
        return this.path;
    }

    public TaskList load() throws StorageOperationException {
        try {
            List<String> encodedTaskList = Files.readAllLines(path, StandardCharsets.UTF_8);
            return TaskListDecoder.decodeTaskList(encodedTaskList);
        } catch (FileNotFoundException e) {
            return new TaskList();
        } catch (IOException e) {
            throw new StorageOperationException("Error reading from file: " + path, e);
        }
    }

    public void save(TaskList taskList) throws StorageOperationException {
        Collection<String> encodedTaskList = TaskListEncoder.encodeTaskList(taskList);
        try {
            ensureParentPathExists(path);
            Files.write(path, encodedTaskList, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new StorageOperationException("Error writing to file: " + path, e);
        }
    }

    private static boolean isValidPath(String filePath) {
        return filePath.endsWith(".txt");
    }

    private static void ensureParentPathExists(Path path) throws IOException {
        Path parentPath = path.getParent();
        if (parentPath != null) {
            Files.createDirectories(parentPath);
        }
    }
}
