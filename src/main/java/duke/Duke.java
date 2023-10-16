package duke;

import duke.command.ExitCommand;
import duke.data.UserKeywordArgument;
import duke.exception.MissingDescriptionException;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.command.Command;
import duke.parser.Parser;
import duke.exception.DukeException;

public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private UserKeywordArgument keywordArgument;

    public Duke(String filePath){
        ui = new Ui();
        storage = new Storage(filePath);
        keywordArgument = new UserKeywordArgument();
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
        Command command;
        do {
            String userInputCommand = ui.getUserCommand();
            command = Parser.parse(userInputCommand, keywordArgument);
            executeCommand(command);
        }while(!ExitCommand.isExit());
    }

    private void executeCommand(Command command) {
        if (command == null) {
            return;
        }
        try {
            command.execute(tasks, ui, storage, keywordArgument);
            storage.save(tasks);
        } catch (MissingDescriptionException e) {
            ui.showResponseToUser(e.getMessage());
        }

    }

    public static void main(String[] args) {
        new Duke("src/main/java/duke/data/duke.txt").run();
    }


}
