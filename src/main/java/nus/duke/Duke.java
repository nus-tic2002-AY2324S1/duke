package nus.duke;

import nus.duke.commands.AbstractCommand;
import nus.duke.commands.AbstractQueryCommand;
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
     * Instantiates a new Duke instance with the default storage file path.
     */
    public Duke() {
        this("./data/duke.txt");
    }

    /**
     * Instantiates a new Duke instance with the specified storage file path.
     *
     * @param filePath The file path where Duke stores and loads tasks.
     */
    public Duke(String filePath) {
        assert filePath != null;

        this.ui = new Ui();
        this.storage = new Storage(filePath);
        TaskList taskList;
        try {
            taskList = storage.load();
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
        this.tasks = taskList;
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
                String[] outputLines = c.execute(tasks, storage);
                ui.showMessages(outputLines);
                isExit = c.isExit();
            } catch (UnknownCommandDukeException e) {
                ui.showError("OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (Exception e) {
                ui.showError("OOPS!!! " + e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Generates a response based on the provided user input.
     * Parses the input to create a command, executes the command on the current task list
     * and storage, and returns the resulting output or error message.
     *
     * @param input The user input to be processed.
     * @return A String representing the response generated based on the input.
     */
    public String getResponse(String input) {
        try {
            AbstractCommand c = Parser.parse(input);
            String[] outputLines = c.execute(tasks, storage);
            if (outputLines.length == 0 && c instanceof AbstractQueryCommand) {
                return "No tasks found.";
            }
            return String.join(System.lineSeparator(), outputLines);
        } catch (UnknownCommandDukeException e) {
            return "OOPS!!! I'm sorry, but I don't know what that means :-(";
        } catch (Exception e) {
            return "OOPS!!! " + e.getMessage();
        }
    }
}
