package main.java;
import ExceptionClasses.*;
import TaskClasses.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws DupeException {
        ArrayList<Task> loadedTasks = new ArrayList<>();

        try {
            File file = new File(filePath);

            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNext()) {
                    String taskString = scanner.nextLine();
                    try {
                        Task task = Task.fromFileString(taskString);
                        if (task != null) {
                            loadedTasks.add(task);
                        }
                    } catch (CorruptedFileException | IncompleteDataException e) {
                        System.err.println("Error loading task from file: " + e.getMessage());
                    }
                }
                scanner.close();
            }
        } catch (IOException e) {
            throw new DupeException("Error loading tasks from file: " + e.getMessage());
        }

        return loadedTasks;
    }

    public void save(ArrayList<Task> tasks) throws DupeException {
        try {
            // Specify the directory path
            String directoryPath = "./data/";

            // Create the directory if it doesn't exist
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdirs(); // Creates the directory and any necessary parent directories
            }

            // Now, save the tasks to the file within the directory
            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks) {
                writer.write(task.toFileString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new DupeException("Error saving tasks to file: " + e.getMessage());
        }
    }
}