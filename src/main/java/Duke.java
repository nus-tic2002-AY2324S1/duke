import ui.UserInterface;
import parser.Parser;
import commands.Command;
import commands.ExitCommand;

import java.util.ArrayList;

public class Duke {
    private UserInterface userInterface;

    public static void main(String[] args) {
        Duke amebot = new Duke();
        amebot.launch();
    }

    public void launch() {
        this.userInterface = new UserInterface();
        this.userInterface.printWelcome();

        render();
    }

    public void render() {
        String commandLine;
        Command command = new Command();

        do {
            commandLine = this.userInterface.getInputCommand();
            ArrayList<String> parsedCommand = new Parser().parseCommand(commandLine);
            ArrayList<String> logs = command.executeCommand(parsedCommand);
            this.userInterface.printOutput(logs);
        } while (!ExitCommand.isExit(commandLine));
    }
}
