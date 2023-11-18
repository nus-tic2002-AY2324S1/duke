package nus.duke.storage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import nus.duke.data.TaskList;

/**
 * The `Storage` class is responsible for loading and saving the task list to a file.
 * It provides methods to load and save task data from and to a specified file path.
 */
public class Storage {
    private final Path path;

    /**
     * Instantiates a new `Storage` with the provided file path.
     *
     * @param filePath The file path where the task list data is stored.
     * @throws IllegalArgumentException If the provided file path does not end with '.txt'.
     */
    public Storage(String filePath) {
        assert filePath != null;

        if (!isValidPath(filePath)) {
            throw new IllegalArgumentException("Storage file should end with '.txt'");
        }

        this.path = Paths.get(filePath);
    }

    private static boolean isValidPath(String filePath) {
        assert filePath != null;

        return filePath.endsWith(".txt");
    }

    private static void ensureParentPathExists(Path path) throws IOException {
        assert path != null;

        Path parentPath = path.getParent();
        if (parentPath != null) {
            Files.createDirectories(parentPath);
        }
    }

    /**
     * Gets the file path where the task list is stored.
     *
     * @return The `Path` object representing the file path.
     */
    public Path getPath() {
        return this.path;
    }

    /**
     * Loads the task list data from the specified file.
     *
     * @return The loaded `TaskList` object.
     * @throws StorageOperationException If an error occurs while reading from the file.
     */
    public TaskList load() throws StorageOperationException {
        try {
            List<String> encodedTaskList = Files.readAllLines(path, StandardCharsets.UTF_8);
            return TaskListDecoder.decodeTaskList(encodedTaskList);
        } catch (NoSuchFileException e) {
            return new TaskList();
        } catch (IOException e) {
            throw new StorageOperationException("Error reading from file: " + path, e);
        }
    }

    /**
     * Saves the task list data to the specified file.
     *
     * @param taskList The `TaskList` object to be saved.
     * @throws StorageOperationException If an error occurs while writing to the file.
     */
    public void save(TaskList taskList) throws StorageOperationException {
        assert taskList != null;

        Collection<String> encodedTaskList = TaskListEncoder.encodeTaskList(taskList);
        try {
            ensureParentPathExists(path);
            Files.write(path, encodedTaskList, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new StorageOperationException("Error writing to file: " + path, e);
        }
    }
}
