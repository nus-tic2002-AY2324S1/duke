package duke;
import UI.ListTask;
import UI.Storage;
import UI.UI;

import java.io.FileNotFoundException;
import java.io.IOException;
public class Duke {


    public static void main(String[] args){
        String filepath = "src\\main\\java\\DataStorage.txt";
        UI ui = new UI();
        Storage storage = new Storage(filepath);
        ListTask list;
        try {
            list = storage.load();
        } catch (FileNotFoundException e) {
            System.out.println("Cate is unable to load file , starting a new list");
            list = new ListTask();
        }
        ui.run(list);
        try{
            storage.save(list);
        }
        catch (IOException e) {
            System.out.println("Error, Failed to save file");
        }
    }

}
