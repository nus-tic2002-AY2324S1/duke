package duke;

import duke.data.TaskList;
import duke.storage.StorageFile;
import duke.ui.TextUI;

import java.io.FileNotFoundException;


public class Duke {

    private static final TextUI ui = new TextUI();
    private static TaskList tasklist = new TaskList();
    private static StorageFile myStorage;

    public static void main(String[] args) {
        loadData(args);
        ui.printWelcomeMsg();
        runLoop();
        ui.printGoodbyeMsg();
    }

    private static void loadData(String[] args){
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
    }

    private static void runLoop(){
        for (;;){
            String line = ui.getCommand();
            String message = tasklist.processTask(line, myStorage, true);
            if (message.equals("exit")) { break; }
            ui.printMessage(message);
        }
    }



}
