import java.io.FileNotFoundException;
import java.io.IOException;
public class Duke {

    public static void main(String[] args) {
        UI ui = new UI();
        Storage storage = new Storage();
        Parser parse = new Parser();
        ListTask listofitems;
        try {
            listofitems = storage.load();
        } catch (FileNotFoundException e) {
            listofitems = new ListTask();
        }
        ui.Run(listofitems, parse);
        try{
            storage.save(listofitems);
        }
        catch (IOException e) {
            System.out.println("Error in saving, data is lost");
        }
    }

}
