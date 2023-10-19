package nus.duke;

import nus.duke.commands.Command;
import nus.duke.data.TaskList;
import nus.duke.exceptions.DukeException;
import nus.duke.exceptions.UnknownCommandDukeException;
import nus.duke.parser.Parser;
import nus.duke.storage.Storage;
import nus.duke.ui.Ui;

public class Duke {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

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

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
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

    public static void main(String[] args) {
        Duke duke = new Duke("./data/duke.txt");
        duke.run();
    }
}
