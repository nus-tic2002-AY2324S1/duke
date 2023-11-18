package duke;

import duke.command.ExitCommand;
import duke.data.UserKeywordArgument;
import duke.exception.InvalidArgumentException;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.command.Command;
import duke.parser.Parser;
import duke.exception.FileStorageException;

/**
 * Duke is the main entry point of the Luna chatbot application
 * Initializes the applications and starts the interaction with the user
 */
public class Duke {

    final private Ui ui;
    final private Storage storage;
    private TaskList tasks;
    private UserKeywordArgument keywordArgument;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileStorageException | InvalidArgumentException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the program and print the welcome message
     */
    public void run() {
        ui.showWelcome();
        loopUntilExitCommand();
    }

    /**
     * Reads the user command and executes it, until user issues the 'bye' exit command.
     */
    private void loopUntilExitCommand() {
        Command command;
        do {
            String userInputCommand = ui.getUserCommand();
            keywordArgument = new UserKeywordArgument(userInputCommand);
            command = Parser.parseKeywordToCommand(keywordArgument);
            executeCommand(command);
        } while (!ExitCommand.isExit());
    }

    /**
     * Executes the user command and saves the data to the "duke.txt" file.
     *
     * @param command user command
     */
    private void executeCommand(Command command) {
        if (command == null) {
            return;
        }
        try {
            command.executeCommand(tasks, ui, keywordArgument);
            storage.save(tasks.getTasks());
        } catch (FileStorageException | InvalidArgumentException e) {
            ui.showResponseToUser(e.getMessage());
        }

    }

    public static void main(String[] args) {
        new Duke("src/main/java/duke/data/duke.txt").run();
    }


}
