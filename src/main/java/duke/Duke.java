package duke;
import UI.ListTask;
import UI.Storage;
import UI.UI;
import parser.Parser;

import java.io.FileNotFoundException;
import java.io.IOException;
public class Duke {

    public static void main(String[] args) {
        UI ui = new UI();
        Storage storage = new Storage();
        Parser parse = new Parser();
        ListTask list;
        try {
            list = storage.load();
        } catch (FileNotFoundException e) {
            list = new ListTask();
        }
        ui.run(list, parse);
        try{
            storage.save(list);
        }
        catch (IOException e) {
            System.out.println("Error in saving, data is lost");
        }
    }

}
