package duke.storage;

import duke.common.Message;
import duke.exception.FileStorageException;
import duke.exception.InvalidArgumentException;
import duke.task.Task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * The Storage class is responsible for managing the data storage of the application.
 * It provides methods for loading data from a file into the application and saving data from the application to a file.
 */
public class Storage {
    public final Path path;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The file path to be associated with the Storage object.
     */
    public Storage(String filePath) {
        path = Paths.get(filePath);
    }

    /**
     * Loads tasks from the storage file and returns them as an ArrayList.
     *
     * @return An ArrayList containing tasks loaded from the storage file.
     * @throws FileStorageException     If there are issues with file existence or reading.
     * @throws InvalidArgumentException If the command arguments are invalid, an exception is thrown with an error
     *                                  message.
     */
    public ArrayList<Task> load() throws FileStorageException, InvalidArgumentException {
        try {
            if (!Files.exists(path)) {
                String message = Message.concat(Message.MESSAGE_FILE_NOT_EXIST,
                        Message.MESSAGE_MAKE_NEW_INSTANCE);
                throw new FileStorageException(message);
            }
            return TaskDecoder.decodeStringsToTaskList(Files.readAllLines(path));
        } catch (IOException e) {
            throw new FileStorageException("Error reading storage file: " + path);
        } catch (InvalidArgumentException e) {
            throw new InvalidArgumentException(e.getMessage());
        }
    }

    /**
     * Saves the provided TaskList to the storage file.
     *
     * @param tasks The TaskList to be saved to the storage file.
     * @throws FileStorageException If there are issues with writing to the file.
     */
    public void save(ArrayList<Task> tasks) throws FileStorageException {
        try {
            ArrayList<String> listOfTasks = TaskEncoder.encodeTaskListToStringList(tasks);
            if (!Files.exists(path)) {
                Files.createDirectories(path.getParent());
            }
            Files.write(path, listOfTasks);
        } catch (IOException e) {
            throw new FileStorageException("Error writing storage file: " + path);
        }
    }

    /**
     * Retrieves the file path as a string.
     *
     * @return A string representing the file path.
     */
    public String getPath() {
        return path.toString();
    }
}
