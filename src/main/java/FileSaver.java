import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SaveFiler {

    public static Path getSavefilePath(){
        String home = System.getProperty("user.dir");
        return Paths.get(home, "save");
    }

    public static void CreateFile() {
        try {
            Path filePath = Paths.get(getSavefilePath() + "\\data.txt");
            if (!java.nio.file.Files.exists(filePath)) {
                Files.createFile(filePath);
                System.out.println("File created: " + filePath.toString() +" ...proceed to save file");
            } else {
                System.out.println("File already exists...proceed to save file");
            }
            saveToFile(filePath);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void saveToFile(Path filePath) throws IOException {
        try {
            Files.write(filePath, "Taskskskskskskk 232".getBytes());
            System.out.println("Tasks saved successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static String loadFile(String fileName) throws IOException {
        String shelfData = "";
        try {
            Path filePath = Paths.get(getSavefilePath() + "\\" + fileName + ".txt");
            if (!java.nio.file.Files.exists(filePath)) {
                System.out.println("File loaded, Welcome Back!");
                shelfData = Files.readString(filePath);
            } else {
                System.out.println("File not found, please create a new one");
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return shelfData;
    }

}