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

public class Storage {
    private static Storage storage;
    private TaskList taskList;
    private static UI ui;

    public static String filePath;
    public Storage(String filePath){
        this.filePath = filePath;
    }
    public static TaskList load() throws DukeException {
        TaskList taskList = new TaskList();
        try{
            File file = new File(filePath);
            Scanner s = new Scanner(file);
            while (s.hasNextLine()){
                String fullCommand = s.nextLine().trim();
                Command command = Parser.parse(fullCommand);
                command.execute(taskList, ui, storage);
            }
            s.close();
        } catch (IOException e) {
            throw new DukeException("archive.Task data file not found." + e.getMessage());
        }
        return taskList;
    }

    public static void save(TaskList taskList) throws DukeException, IOException {
        String dukeOutPath = "data/dukeOut.txt";
        File file = new File(dukeOutPath);
        //  if file not exist create
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
