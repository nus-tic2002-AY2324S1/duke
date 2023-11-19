package duke.storage;

import duke.command.Command;
import duke.exception.DukeException;
import duke.history.History;
import duke.parser.Parser;
import duke.task.*;
import duke.ui.UI;
import duke.utils.Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The `Storage` class is responsible for loading and saving task data from/to a file.
 */
public class Storage {
    private static Storage storage;
    private TaskList taskList;
    private static UI ui;

    public static String filePath;

    /**
     * Constructs a `Storage` object with the specified file path.
     *
     * @param filePath The file path for reading and writing task data.
     */
    public Storage(String filePath) {
        Storage.filePath = filePath;
    }

    /**
     * Loads tasks from the data file into the task list.
     * The method reads the file specified by the 'filePath' variable,
     * parses each line according to the task type, and reconstructs the task list.
     * This allows the task list to be restored to its previous state when the program is restarted.
     *
     * @return An ArrayList of {@code Task} objects representing the loaded tasks.
     * @throws DukeException If the data file does not exist or an IO error occurs.
     */
    public static ArrayList<Task> load() throws DukeException {
        TaskList taskList = new TaskList();
        try {
            File file = new File(filePath);
            assert file.exists() : "Data file should exist for loading tasks";
            Scanner s = new Scanner(file);
            UI.showMessage("Attempting to load the task list from data source to restore the previous session's history.");
            while (s.hasNextLine()) {
                String line = s.nextLine().trim();
                if (line.isEmpty()) {
                    continue; // Skip empty lines
                }

                String[] parts = line.split(" \\| ");
                String type = parts[0];
                boolean isDone = parts[1].trim().equals("1");
                String description = parts[2].trim();

                Task task = null;
                switch (type) {
                    case "T":
                        task = new Todo(description);
                        break;
                    case "D":
                        String by = parts.length > 3 ? parts[3].trim() : "";
                        task = new Deadline(description, Utils.parseDateTime(by));
                        break;
                    case "E":
                        String start = parts.length > 3 ? parts[3].trim() : "";
                        String end = parts.length > 4 ? parts[4].trim() : "";
                        task = new Event(description, Utils.parseDateTime(start), Utils.parseDateTime(end));
                        break;
                }

                if (task != null) {
                    if (isDone) task.markAsDone();
                    taskList.addTask(task);
                }
            }
            s.close();
        } catch (IOException e) {
            throw new DukeException("Task data file not found: " + e.getMessage());
        }
        return taskList.getTaskList();
    }

    /**
     * Saves tasks from a `TaskList` to the data file.
     *
     * @param taskList The `TaskList` containing tasks to be saved.
     * @throws DukeException If there is an issue saving task data.
     * @throws IOException   If an IO error occurs during the saving process.
     */
    public static void save(TaskList taskList) throws DukeException, IOException {
        assert taskList != null : "TaskList to be saved should not be null";
        String dukeOutPath = "data/dukeOut.txt";
        File file = new File(dukeOutPath);
        //  if file not exist, create it and its parent folder.
        if (!file.exists()) {
            try {
                String folderName = dukeOutPath.split("/")[0];
                File folder = new File(folderName);
                if (!folder.exists()) {
                    folder.mkdirs();
                }
                file.createNewFile();
            } catch (IOException e) {
                throw new DukeException(e.getMessage());
            }
        }
        try {
            FileWriter fw = new FileWriter(file, false);
            for (Task task : taskList.getTaskList()) {
                String taskType = String.valueOf(task.getTaskType());
                String status = String.valueOf(task.getStatus() ? 1 : 0);
                String description = task.getDescription();
                String text = taskType + " | " + status + " | " + description + "\n";
                fw.write(text);
            }
            fw.flush();
            fw.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    public static void setStorage(Storage storage) {
        Storage.storage = storage;
    }
}
