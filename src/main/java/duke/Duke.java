package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.IncorrectCommandFormatException;
import duke.history.History;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.UI;

import java.io.IOException;
import java.util.ArrayList;


public class Duke {
    private final Storage storage;
    private TaskList taskList;
    private final UI ui;
    private History history;


    /**
     * Constructs a duke.Duke instance with the specified file path. It initializes the user interface (UI), storage, and task list. If the file exists, it loads existing tasks; otherwise, it creates a new task list.
     *
     * @param filePath The file path for storing and retrieving tasks.
     */
    public Duke(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(Storage.load());
            if (taskList.getTaskList().isEmpty()){
                UI.showMessage("The data file is empty. Starting BotGenius with an empty task list...\n");
            } else {
                UI.showMessage("Tasks loaded successfully. BotGenius is starting up...\n");
            }
        } catch (DukeException e) {
            taskList = new TaskList(new ArrayList<>());
            UI.showMessage("Task data source not exist, proceed with empty task list.");
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
                if (c != null) {
                    if (c.isChangingState()) {
                        History.saveHistory(taskList);
                    }
                    c.execute(taskList, ui, storage);
                    isExit = c.isExit();
                }
            } catch (DukeException | IOException e) {
                UI.showMessage(e.getMessage());
            } finally {
                UI.showLine();
            }
        }
    }

    public static void main(String[] args) {
        String filePath = "data/dukeOut.txt"; // Default file path
        if (args.length > 0) {
        filePath = args[0]; // Use provided file path if available
        }
        new Duke(filePath).run();
    }
}


