import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSaver {

    public static Path getSavefilePath(){
        String home = System.getProperty("user.dir");
        return Paths.get(home, "save");
    }

    public static void CreateFile(String fileData) {
        try {
            Path filePath = Paths.get(getSavefilePath() + "\\data.txt");
            if (!java.nio.file.Files.exists(filePath)) {
                Files.createFile(filePath);
                System.out.println("File created: " + filePath.toString() +" ...proceed to save file");
            } else {
                System.out.println("File already exists...proceed to overwrite save file");
            }
            saveToFile(filePath, fileData);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void saveToFile(Path filePath, String fileData) throws IOException {
        try {
            Files.write(filePath, fileData.getBytes());
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
            System.out.println(filePath);
            if (java.nio.file.Files.exists(filePath)) {
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