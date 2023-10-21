package amebot;

import amebot.storage.Storage;
import amebot.ui.UserInterface;
import amebot.parser.Parser;
import amebot.commands.Command;
import amebot.commands.ExitCommand;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The main class of the Duke application.
 */
public class Amebot {
    private UserInterface userInterface;
    private Storage storage;

    /**
     * The main method of the Duke application.
     *
     * @param args The command line arguments.
     * @throws IOException If there is an error loading the file.
     */
    public static void main(String[] args) throws IOException {
        Amebot amebot = new Amebot();
        amebot.launch();
    }

    /**
     * Launches the Duke application.
     *
     * @throws IOException If there is an error loading the file.
     */
    public void launch() throws IOException {
        this.userInterface = new UserInterface();
        this.userInterface.printWelcome();

        Command command = new Command();
        this.storage = new Storage();
        this.storage.loadTasks(command);

        render(command);

        this.storage.saveTasks();
    }

    /**
     * Renders the Duke application.
     *
     * @param command The command to be executed.
     */
    public void render(Command command) {
        String commandLine;

        do {
            commandLine = this.userInterface.getInputCommand();
            ArrayList<String> parsedCommand = new Parser().parseCommand(commandLine);
            ArrayList<String> logs = command.executeCommand(parsedCommand);
            this.userInterface.printOutput(logs);
        } while (!ExitCommand.isExit(commandLine));
    }
}
