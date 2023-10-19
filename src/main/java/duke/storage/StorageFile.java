package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import duke.commandsTask.Task;
import duke.data.TaskList;

public class StorageFile {
    private static final String DEFAULT_STORAGE_FILEPATH = "myTaskList.txt";
    private File file;

    public StorageFile() {
        this(DEFAULT_STORAGE_FILEPATH);
    }

    public StorageFile(String file) {
        if (isValidPath(file)) {
            this.file = new File(file);
        };
    }

    public void append(Task task) {
        try {
            FileWriter fw  = new FileWriter(file.getName(), true);
            fw.write(task.toCode() + System.lineSeparator());
            fw.close();
        }
        catch (IOException e){
            System.out.println("Storage File writing error occurred");
        }
    }

    public void write(List <Task> tasklist) {
        try {
            FileWriter fw  = new FileWriter(file.getName(),false);
            for (Task task : tasklist){
                fw.write(task.toCode() + System.lineSeparator());
            }
            fw.close();
        }
        catch (IOException e){
            System.out.println("Storage File writing error occurred");
        }
    }

    public TaskList load() throws FileNotFoundException {
        TaskList tasklist = new TaskList();
        Scanner s = new Scanner(this.file);
        while (s.hasNext()){
            tasklist.processTask(s.nextLine(), this,false);
        }
        return tasklist;
    }

    private static boolean isValidPath(String file) {
        return file.endsWith(".txt");
    }

}
