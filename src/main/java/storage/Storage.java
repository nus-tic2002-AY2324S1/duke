package storage;
import task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * A festure class contains function that convert the TaskList to a Text file duke.txt
 * The text file and file path will be created if doesn't exist in the directory.
 * whenever the TaskList being updated the text file will be updated too.
 */
public class Storage {
    /**
     * This is a function returns nothing
     * but take in an ArrayList of <code>Task</code> and put them into duke.txt file.
     *
     * @param actions ArrayList of Tasks.
     * @return nothing will be returned.
     * @throws IOException If file does not exist.
     */
    public static void convertToTxtFile(ArrayList<Task> actions) throws IOException {
        //handling file/folder does not exit
        String filePath = "./data/duke.txt";
        String folderPath = "./data";
        File file = new File(filePath);
        File folder = new File(folderPath);
        Path createFolder = Path.of(folderPath);

        try {
            if (file.exists()) {
                if (file.delete()) {
                    //System.out.println("File deleted successfully.");
                } else {
                    //System.out.println("Failed to delete the file.");
                    return;
                }
            }else if (!folder.exists()) {
                Files.createDirectories(createFolder);
            }
            FileWriter fw = new FileWriter(filePath);
            for (Task task : actions) {
                fw.write(task.getTask());

                //line seperator in the file
                fw.write(System.lineSeparator());
            }
            fw.close();
        }catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }

    }
}
