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
public class Storage{

    /** File path where the task list is stored. */
    private String myFilePath = "./tasklist/tasklist.txt";
    /** Relative Folder where the task list is stored. */
    private String myFileFolder = "./tasklist";

    /**
     * Checks if the task list folder exists and creates it if it doesn't.
     */
    public void checkFolderExistence(){
        try {
            Path directoryPath = Paths.get(myFileFolder);
            if (Files.notExists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }
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
    public void saveTasksToFile(ArrayList<Task> myList) {
        checkFolderExistence();
        try {
            FileWriter fileWriter = new FileWriter(myFilePath);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            for (Task task : myList) {
                printWriter.println(task.toFileString());
            }

            printWriter.close();
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
            Scanner scanner = new Scanner(new File(myFilePath));
            while (scanner.hasNextLine()) {
                String taskDetails = scanner.nextLine();
                Task task = Task.fromString(taskDetails);
                if (task != null) {
                    taskList.add(task);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Task list file not found: " + e.getMessage());
        }
        return taskList;
    }
}
