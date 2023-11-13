package amebot;

import amebot.common.Messages;
import amebot.storage.Storage;
import amebot.ui.UserInterface;
import amebot.parser.Parser;
import amebot.commands.Command;
import amebot.commands.ExitCommand;
import amebot.exceptions.AmebotException;

import java.util.ArrayList;

/**
 * Main class of the application.
 */
public class Amebot {
    private UserInterface userInterface;
    private Storage storage;

    /**
     * Starts and create the application.
     *
     * @param args Arguments passed in by the user.
     */
    public static void main(String[] args) {
        Amebot amebot = new Amebot();
        amebot.launch();
    }

    /**
     * Launches the application.
     */
    public void launch() {
        this.userInterface = new UserInterface();
        this.userInterface.printWelcome();

        this.storage = new Storage();
        this.storage.loadTasks();

        try {
            this.render();
        } catch (AmebotException err) {
            System.out.println(Messages.RENDER_ERROR);
        }
    }

    /**
     * Renders the application.
     */
    public void render() throws AmebotException {
        String commandLine = "";
        Command command = new Command();

        do {
            commandLine = this.userInterface.getInputCommand();
            ArrayList<String> parsedCommand = new Parser().parseCommand(commandLine);
            ArrayList<String> logs = command.executeCommand(parsedCommand);
            this.userInterface.printOutput(logs);
            this.storage.saveTasks();
        } while (!ExitCommand.isExit(commandLine));
    }
}
