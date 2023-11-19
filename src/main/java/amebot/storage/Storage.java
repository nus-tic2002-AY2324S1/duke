package amebot.storage;

import amebot.common.Messages;
import amebot.commands.Command;
import amebot.tasks.Task;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Storage class handles the tasks loaded from file and
 * saves tasks to file.
 */
public class Storage {
    protected static final String FOLDER_PATHNAME = "./data/";
    protected static final File TASKS_FOLDER = new File(FOLDER_PATHNAME);
    protected static final String FILE_PATHNAME = "./data/amebot.txt";
    protected static final File TASKS_FILE = new File(FILE_PATHNAME);

    /**
     * Loads tasks from the file.
     */
    public void loadTasks() {
        boolean isTasksFileEmpty = TASKS_FILE.length() == 0;

        if (!isTasksFileFound() || isTasksFileEmpty) {
            return;
        }

        try {
            FileReader tasksFileReader = new FileReader(TASKS_FILE);
            BufferedReader bufferedReader = new BufferedReader(tasksFileReader);
            String task = "";

            while ((task = bufferedReader.readLine()) != null) {
                TaskDecoder.decodeTask(task);
            }

            bufferedReader.close();
            tasksFileReader.close();
        } catch (IOException err) {
            System.out.println(Messages.ERROR_MESSAGE);
        }
    }

    /**
     * Returns true if the file exists.
     *
     * @return True if the file exists, false otherwise.
     */
    public boolean isTasksFileFound() {
        if (TASKS_FILE.exists()) {
            return true;
        }

        createNewTasksFile();
        return false;
    }

    /**
     * Creates a folder and file if they do not exist.
     */
    public void createNewTasksFile() {
        try {
            boolean isFolderCreated = TASKS_FOLDER.mkdir();

            if (isFolderCreated) {
                TASKS_FILE.createNewFile();
            }
        } catch (IOException err) {
            System.out.println(Messages.ERROR_MESSAGE);
        }
    }

    /**
     * Saves tasks to the file.
     */
    public void saveTasks() {
        ArrayList<Task> tasksList = Command.getTasks();
        String taskDetail = "";

        try {
            FileWriter tasksFileWriter = new FileWriter(TASKS_FILE);
            BufferedWriter bufferedWriter = new BufferedWriter(tasksFileWriter);

            for (Task task : tasksList) {
                taskDetail = TaskEncoder.encodeTask(task);

                bufferedWriter.write(taskDetail);
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
            tasksFileWriter.close();
        } catch (IOException err) {
            System.out.println(Messages.ERROR_MESSAGE);
        }
    }
}
