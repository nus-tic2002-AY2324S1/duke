package Duke;
import Shelf.Shelf;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.lang.Integer.parseInt;

/**
 * Methods for saving and loading file operation
 *
 */
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

    public static Shelf FileParser(String shelfData) throws DukeException { //converts file string into Shelf.Shelf arraylist
        Shelf newlist = new Shelf();
        int line_no = 1;

        String[] lines = shelfData.split("\n");
        for(String line : lines){
            String[] split = line.split("\\|");
            String type = Parser.TypeParser(split[0]);
            String marking = split[1];
            String item = split[2];
            String date = split[3];
            String tags = split[4];
            if(!date.equals("~")){
                newlist.addDateTask(type, item, date);
            }else {
                newlist.addSpecialTask(type, item);
            }

            if(!marking.equals("~")){
                newlist.markTask(new String[]{"mark", Integer.toString(line_no)});
            }
            if(!tags.equals("~")){
                newlist.addTagslist(tags, String.valueOf(line_no));
            }
            line_no++;
        }
        System.out.println("File loaded, Welcome Back Taskmaster!");
        return newlist;
    }

}