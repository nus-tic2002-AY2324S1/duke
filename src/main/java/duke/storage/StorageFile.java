package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import duke.commands.Task;
import duke.data.TaskList;

/**
 * StorageFile class
 */
public class StorageFile {
    private static final String DEFAULT_STORAGE_FILEPATH = "myTaskList.txt";
    private File file;

    /**
     * StorageFile empty constructor
     */
    public StorageFile() {
        this(DEFAULT_STORAGE_FILEPATH);
    }

    /**
     * StorageFile with one string argument constructor
     *
     * @param file string file address
     */
    public StorageFile(String file) {
        if (isValidPath(file)) {
            this.file = new File(file);
        }
    }

    /**
     * Appends task into StorageFile
     *
     * @param task Task
     */
    public void append(Task task) {
        try {
            FileWriter fw = new FileWriter(file.getName(), true);
            fw.write(task.toCode() + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            System.out.println("Storage File writing error occurred");
        }
    }

    /**
     * Overwrites StorageFile with the entries in the task list
     *
     * @param tasklist user's task list
     */
    public void write(List <Task> tasklist) {
        try {
            FileWriter fw = new FileWriter(file.getName(), false);
            for (Task task : tasklist) {
                fw.write(task.toCode() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Storage File writing error occurred");
        }
    }

    /**
     * Returns the list of tasks  from the StorageFile
     *
     * @return TaskList
     * @throws FileNotFoundException
     */
    public TaskList load() throws FileNotFoundException {
        TaskList tasklist = new TaskList();
        Scanner s = new Scanner(this.file);
        while (s.hasNext()) {
            tasklist.processTask(s.nextLine(), this, false);
        }
        return tasklist;
    }

    /**
     * Checks if a file ends in .txt
     *
     * @param file string file path
     * @return boolean
     */
    private static boolean isValidPath(String file) {
        return file.endsWith(".txt");
    }

}
