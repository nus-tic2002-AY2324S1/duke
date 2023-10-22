package Duke.FileHandler;

import Duke.Task.Task;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;

/**
 * Represents a `FileStorage` class for storing the user's task list as a Duke's data file.
 */
public class FileStorage extends FileHandler {

    /**
     * Stores the list of tasks in a file.
     *
     * @param taskList The list of tasks to be stored in the file.
     */
    public void fileStorage(List<Task> taskList) {

        File outputFile = new File(filePath);

        try {
            // Check if the file exists, and create it if it doesn't
            if (!outputFile.exists()) {
                outputFile.getParentFile().mkdirs();
                outputFile.createNewFile();
            }

            // Open the file for writing
            try (FileOutputStream fileOutputStream = new FileOutputStream(outputFile, false);
                 PrintWriter printWriter = new PrintWriter(fileOutputStream)) {
                // Append output to the file
                for (Task task : taskList) {
                    printWriter.println(task.toFile());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
