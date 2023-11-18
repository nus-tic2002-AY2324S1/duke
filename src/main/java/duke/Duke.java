package duke;
import UI.ListTask;
import UI.Storage;
import UI.UI;
import parser.Parser;

import java.io.FileNotFoundException;
import java.io.IOException;
public class Duke {


    public static void main(String[] args){
        String filepath = "src\\main\\java\\DataStorage.txt";
        UI ui = new UI();
        Storage storage = new Storage(filepath);
        Parser parser = new Parser();
        ListTask list;
        try {
            list = storage.load();
        } catch (FileNotFoundException e) {
            list = new ListTask();
        }
        ui.run(list, parser);
        try{
            storage.save(list);
        }
        catch (IOException e) {
            System.out.println("Error, Failed to save file");
        }
    }

}
