package duke;

import java.io.FileNotFoundException;

import duke.data.TaskList;
import duke.storage.StorageFile;
import duke.ui.TextUI;


/**
 * Duke class
 */
public class Duke {

    private static final TextUI ui = new TextUI();
    private static TaskList tasklist = new TaskList();
    private static StorageFile myStorage;

    /**
     * Main function to run the program
     * @param args String provided by user to indicate the txt file to be used as storage
     */
    public static void main(String[] args) {
        loadData(args);
        ui.printWelcomeMsg();
        runLoop();
        ui.printGoodbyeMsg();
    }

    /**
     * Loads data into myStorage
     * @param args string file path
     */
    private static void loadData(String[] args) {
        try {
            if (args.length != 0) {
                myStorage = new StorageFile(args[0]);
            } else {
                myStorage = new StorageFile();
            }
            tasklist = myStorage.load();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    /**
     * Runs in a loop until the message "exit" is passed
     */
    private static void runLoop() {
        for (;;) {
            String line = ui.getCommand();
            String message = tasklist.processTask(line, myStorage, true);
            if (message.equals("exit")) {
                break;
            }
            ui.printMessage(message);
        }
    }



}
