

import java.io.IOException;
import commands.Command;
import exceptions.DukeException;
import parser.Parser;
import storage.Storage;
import tasks.TaskList;
import ui.UI;

public class TaskMaster {

    private Storage storage;
    private TaskList tasks;
    private UI ui;

    public TaskMaster(String filePath) {
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
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new TaskMaster("data/temp.txt").run();
    }

}
