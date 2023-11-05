package duke.storage;

import duke.command.Command;
import duke.parser.Parser;
import duke.task.Task;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.UI;

import java.io.File;
import java.io.FileNotFoundException;
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
    public Storage(String filePath){
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the data file and populates a `TaskList` object.
     *
     * @return A `TaskList` containing tasks loaded from the data file.
     * @throws DukeException If there is an issue loading task data.
     */
    public static ArrayList load() throws DukeException {
        TaskList taskList = new TaskList();
        try{
            File file = new File(filePath);
            Scanner s = new Scanner(file);
            while (s.hasNextLine()){
                UI.showLine();
                String fullCommand = s.nextLine().trim();
                UI.showMessage(fullCommand);
                UI.showLine();
                Command command = Parser.parse(fullCommand);
                command.execute(taskList, ui, storage);
            }
            s.close();
        } catch (IOException e) {
            throw new DukeException("Task data file not found." + e.getMessage());
        }
        return taskList.getTaskList();
    }

    /**
     * Saves tasks from a `TaskList` to the data file.
     *
     * @param taskList The `TaskList` containing tasks to be saved.
     * @throws DukeException If there is an issue saving task data.
     * @throws IOException If an IO error occurs during the saving process.
     */
    public static void save(TaskList taskList) throws DukeException, IOException {
        String dukeOutPath = "data/dukeOut.txt";
        File file = new File(dukeOutPath);
        //  if file not exist, create it and its parent folder.
        if (!file.exists()){
            try{
                String folderName=dukeOutPath.split("/")[0];
                File folder = new File(folderName);
                if (!folder.exists()){
                    folder.mkdirs();
                }
                file.createNewFile();
            }catch (IOException e) {
                throw new DukeException(e.getMessage());
            }
        }
        try{
            FileWriter fw = new FileWriter(file,false);
            for(Task task : taskList.getTaskList()){
                String taskType = String.valueOf(task.getTaskType());
                String status = String.valueOf(task.getStatus()? 1:0);
                String description = task.getDescription();
                String text = taskType + " | " + status + " | "+description + "\n";
                fw.write(text);
            }
            fw.flush();
            fw.close();
        }catch (IOException e){
            throw new DukeException(e.getMessage());
        }
    }
}
