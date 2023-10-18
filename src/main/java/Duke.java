import data.TaskList;
import exception.DukeException;
import storage.StorageFile;
import ui.TextUI;

import java.io.FileNotFoundException;


public class Duke {

    private static final TextUI ui = new TextUI();
    private static TaskList tasklist = new TaskList();
    private static StorageFile myStorage;

    public static void main(String[] args) {
        ui.printWelcomeMsg();
        try {
            if (args.length !=0){
                myStorage = new StorageFile(args[0]);
            } else {
                myStorage = new StorageFile();
            }
            tasklist = myStorage.load();
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }

        for (;;){
            String line = ui.getCommand();
            if (!line.isEmpty()) {
                String[] words = line.split(" ");
                String firstWord = words[0];
                if (firstWord.equals("bye")) {
                    ui.printGoodbyeMsg(); break;
                } else {
                    String message = tasklist.processTask(line, myStorage, true);
                    ui.printMessage(message);
                }
            }
        }

    }

}
