package amebot.storage;

import amebot.common.Messages;
import amebot.commands.Command;
import amebot.tasks.Task;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.io.IOException;

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
        if (!isTasksFileFound() || isTasksFileEmpty()) {
            return;
        }

        try {
            FileReader tasksFileReader = new FileReader(TASKS_FILE);
            BufferedReader bufferedReader = new BufferedReader(tasksFileReader);
            String task = "";

            while ((task = bufferedReader.readLine()) != null) {
                TaskDecoder.decodeTask(task);
            }

            System.out.println(Messages.SUCCESS_LOAD_TASK);

            bufferedReader.close();
            tasksFileReader.close();
        } catch (IOException err) {
            System.out.println((Messages.ERROR_MESSAGE));
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

        System.out.println(Messages.FILE_NOT_FOUND);
        createNewTasksFile();
        return false;
    }

    /**
     * Creates a file if it does not exist.
     */
    public void createNewTasksFile() {
        try {
            boolean isFolderCreated = TASKS_FOLDER.mkdir();
            if (isFolderCreated) {
                System.out.println(Messages.FOLDER_CREATED);
            }

            boolean isCreated = TASKS_FILE.createNewFile();
            if (isCreated) {
                System.out.println(Messages.FILE_CREATED);
            }
        } catch (IOException err) {
            System.out.println((Messages.ERROR_MESSAGE));
        }
    }

    /**
     * Returns true if the file is empty.
     *
     * @return True if the file is empty, false otherwise.
     */
    public boolean isTasksFileEmpty() {
        if (TASKS_FILE.length() == 0) {
            System.out.println((Messages.EMPTY_FILE));
            return true;
        }

        return false;
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
            System.out.println((Messages.ERROR_MESSAGE));
        }
    }
}
