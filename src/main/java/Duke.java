
import java.io.IOException;
import java.io.InvalidObjectException;
import java.util.Scanner;

import commands.*;
import duke.Parser;
import duke.Storage;
import duke.UI;
import tasks.TaskList;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private UI ui;

    public Duke(String filePath){
        ui = new UI();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }


    // Parser: deals with making sense of the user command
    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            // try {
            //     String fullCommand = ui.readCommand();
            //     ui.showLine(); // show the divider line ("_______")
            //     Command c = Parser.parse(fullCommand);
            //     c.execute(tasks, ui, storage);
            //     isExit = c.isExit();
            // } catch (IOException e) {
            //     ui.showError(e.getMessage());
            // } finally {
            //     ui.showLine();
            // }
            String fullCommand = ui.readCommand();
            ui.showLine(); // show the divider line ("_______")
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
            ui.showLine();
        }
    }
    

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

}
