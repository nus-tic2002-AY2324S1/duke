package duke;

import duke.command.Bye;
import duke.data.UserKeywordArgument;
import duke.data.UserKeywordArgument;
import duke.exception.MissingDescriptionException;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.command.ICommand;
import duke.parser.Parser;
import duke.exception.DukeException;

public class Main {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private UserKeywordArgument userKeywordArgument;

    public Main(String filePath){
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Run the program and print the welcome message
     */
    public void run(){
        ui.showWelcome();
        loopUntilExitCommand();
    }

    /**
     * Reads the user command and executes it, until user issues the exit command.
     */
    private void loopUntilExitCommand() {
        ICommand command;
        do {
            String userInputCommand = ui.getUserCommand();
            command = Parser.parse(userInputCommand, userKeywordArgument);
            command.execute(tasks, ui, storage, userKeywordArgument);
        }while(!(command instanceof Bye));
    }

    /**
     * Show Goodbye message and exits.
     */
    private void exit() {
        ui.showGoodByeMessage();
    }

    public static void main(String[] args) {
        new Main("data/tasks.txt").run();
    }


}
