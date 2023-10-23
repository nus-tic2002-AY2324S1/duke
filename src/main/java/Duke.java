import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.UI;

import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private UI ui;

    public Duke(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load().getTaskList());
        } catch (DukeException e) {
            UI.showLoadingError();
            taskList = new TaskList();
        }
    }

    public void run() {
        UI.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = UI.readCommand();
                UI.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException | IOException e) {
                UI.showError(e.getMessage());
            } finally {
                UI.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}


