import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.UI;

import java.io.IOException;


/**
 * Duke is the main class for the Duke program. It manages the initialization of UI, Storage, and TaskList, and handles the core program execution.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private UI ui;


    /**
     * Constructs a Duke instance with the specified file path. It initializes the user interface (UI), storage, and task list. If the file exists, it loads existing tasks; otherwise, it creates a new task list.
     *
     * @param filePath The file path for storing and retrieving tasks.
     */
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


    /**
     * The main run loop of the Duke program. It displays a welcome message and continues to accept and process user commands until the exit command is received.
     */
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


