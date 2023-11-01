package nus.duke;

import nus.duke.commands.AbstractCommand;
import nus.duke.data.TaskList;
import nus.duke.exceptions.DukeException;
import nus.duke.exceptions.UnknownCommandDukeException;
import nus.duke.parser.Parser;
import nus.duke.storage.Storage;
import nus.duke.ui.Ui;

/**
 * The Duke task manager is a command-line application for managing tasks.
 * It allows users to add, delete, and view tasks, as well as mark tasks as done.
 * Duke stores tasks in a file for persistence and offers a text-based user interface.
 */
public class Duke {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Instantiates a new Duke instance with the specified storage file path.
     *
     * @param filePath The file path where Duke stores and loads tasks.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        TaskList tasks;
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        this.tasks = tasks;
    }

    /**
     * Runs the application.
     *
     * @param args The input arguments passed to the application.
     */
    public static void main(String[] args) {
        Duke duke = new Duke("./data/duke.txt");
        duke.run();
    }

    /**
     * Runs the Duke application.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                AbstractCommand c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                if (e instanceof UnknownCommandDukeException) {
                    ui.showError("OOPS!!! I'm sorry, but I don't know what that means :-(");
                } else {
                    ui.showError("OOPS!!! " + e.getMessage());
                }
            } finally {
                ui.showLine();
            }
        }
    }
}
