package Storage;

import Tasks.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * Manages the saving and loading of the task list into a file
 */
public class Storage {

    /** File path where the task list is stored. */
    private String storageFilePath = "./tasklist/tasklist.txt";
    /** Relative Folder where the task list is stored. */
    private String storageFileFolder = "./tasklist";

    /**
     * Checks if the task list folder exists and creates it if it doesn't.
     */
    public void checkFolderExistence(){
        try {
            Path directoryPath = Paths.get(storageFileFolder);
            if (Files.notExists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }
            assert Files.exists(directoryPath) : "Failed to create the Task List folder";
        }
        catch (IOException e) {
            System.out.println("tasklist folder doesn't exist and unable to create folder: " + e.getMessage());
        }

    }

    /**
     * Saves a list of tasks to a file.
     *
     * @param myList The list of tasks to be saved.
     */
    public void saveTasksToFile(ArrayList<Task> myList, String fileName) {
        String myFilePath = "";
        if (fileName != null){
            myFilePath = storageFileFolder + "/" + fileName;
        } else {
            myFilePath = storageFilePath;
        }

        if (!myFilePath.endsWith(".txt")){
            myFilePath += ".txt";
        }

        checkFolderExistence();
        try {
            FileWriter fileWriter = new FileWriter(myFilePath);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            for (Task task : myList) {
                printWriter.println(task.toFileString());
            }

            printWriter.close();

            assert Files.exists(Paths.get(myFilePath)) : "Failed to create the Task List file";
        } catch (IOException e) {
            System.out.println("Error saving the task list to the file: " + e.getMessage());
        }
    }

    /**
     * Loads a list of tasks from a file.
     *
     * @return An ArrayList containing the loaded tasks.
     */
    public ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(storageFilePath));
            while (scanner.hasNextLine()) {
                String taskDetails = scanner.nextLine();
                Task task = Task.fromString(taskDetails);
                if (task != null) {
                    taskList.add(task);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Task list file not found. Creating a fresh Task list file");
        }
        return taskList;
    }
}
