import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Storage {

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
                fw.write(task.toTextFile());
                //line seperator in the file
                fw.write(System.lineSeparator());
            }
            fw.close();
        }catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }

    }
}
