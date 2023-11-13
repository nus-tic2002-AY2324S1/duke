package Duke;
import Task.Shelf;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {

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
            if (java.nio.file.Files.exists(filePath)) {
                System.out.println("Loading file from: " + filePath);
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

    public static Shelf FileParser(String shelfData) throws DukeException { //converts file string into Task.Shelf arraylist
        Shelf newlist = new Shelf();
        String type = "";
        String marking = "";
        int line_no = 1;
        String[] split = shelfData.split("[|\n]");
        for (int i = 1; i-1 < split.length; i++) {
            if (i%3 == 0) {
                type = Parser.TypeParser(split[i-3]);
                marking = split[i-2];
                if(split[i-1].contains("/by")){
                    String[] datesplit = split[i-1].split(" /by ");
                    newlist.addDateTask(datesplit[0], type, datesplit[1]);
                }else {
                    newlist.addSpecialTask(split[i - 1], type);
                }
                System.out.println(split[i-1]);
                if(!marking.equals(" ")){
                    newlist.markTask(new String[]{"mark", Integer.toString(line_no)});
                }
                line_no++;
                type = "";
                marking = "";
            }
        }
        System.out.println("File loaded, Welcome Back Taskmaster!");
        return newlist;
    }

}