package duke.storage;

import duke.common.Message;
import duke.exception.FileStorageException;
import duke.task.TaskList;
import duke.task.Task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {
    public final Path path;
    public Storage(String filePath) {
        path = Paths.get(filePath);
    }

    public ArrayList<Task> load() throws FileStorageException {
        try {
            if(!Files.exists(path)){
                String message = Message.concat(Message.MESSAGE_FILE_NOT_EXIST,
                        Message.MESSAGE_MAKE_NEW_INSTANCE);
                throw new FileStorageException(message);
            }
            return TaskDecoder.decodeTask(Files.readAllLines(path));
        } catch (IOException e) {
            throw new FileStorageException("Error reading storage file: " + path);
        }
    }

    public void save(TaskList tasks) {
        try {
            ArrayList<String> listOfTasks = TaskEncoder.encodeTask(tasks);
            Files.write(path, listOfTasks);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
