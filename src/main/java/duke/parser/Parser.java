package duke.parser;


import duke.command.*;
import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.exception.MissingKeywordException;
import duke.ui.UI;


/**
 * The `Parser` class is responsible for parsing user input and converting it into executable commands.
 * It determines the type of command and creates the appropriate `Command` object for execution.
 */
public class Parser {

    /**
     * Parses the user's input and returns the corresponding `Command` object.
     *
     * @param fullCommand The full command entered by the user.
     * @return A concrete `Command` object based on the user's input.
     * @throws DukeException If an error occurs during command parsing or execution.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] parts = fullCommand.split(" ");
        String command = parts[0].toLowerCase();
        Command newCommand = null;

        switch (command) {
            case "list":
                newCommand = new ListCommand();
                break;
            case "bye":
                newCommand = new ByeCommand();
                break;
            case "delete":
                try {
                    int index = Integer.parseInt(parts[1]) - 1;
                    newCommand = new DeleteCommand(index);
                    break;
                } catch (Exception e) {
                    throw new MissingKeywordException("OOPS!!! Missing keyword, Please input item number you want to delete");
                }
            case "mark":
                try {
                    int index = Integer.parseInt(parts[1]) - 1;
                    newCommand = new MarkCommand(index);
                    break;
                } catch (Exception e) {
                    throw new MissingKeywordException("OOPS!!! Please input item number you want to mark");
                }
            case "unmark":
                try {
                    int index = Integer.parseInt(parts[1]) - 1;
                    newCommand = new UnmarkCommand(index);
                    break;
                } catch (Exception e) {
                    throw new MissingKeywordException("OOPS!!! Missing keyword, Please input item number you want to unmark");
                }
            case "todo":
            case "deadline":
            case "event":
                try {
                    newCommand = new NewTaskCommand(fullCommand);
                    break;
                } catch (Exception e) {
                    throw new InvalidCommandException("OOPS!!! Please input valid Task command");
                }
            case "undo":
                try {
                    newCommand = new UndoCommand();
                    break;
                } catch (Exception e) {
                    UI.showMessage("OOPS!!! unable to perform undo action");
                    break;
                }
            case "find":
                try {
                    String keyword = parts[1];
                    newCommand = new FindCommand(keyword);
                    break;
                } catch (Exception e) {
                    throw new MissingKeywordException("OOPS!!! Missing keyword, Please provide a keyword to search");
                }
            case "help":
                    newCommand = new HelpCommand();
                    break;
            default:
                throw new InvalidCommandException("OOPS!!! I don't recognize that command. Type 'help' for a list of available commands.");
        }
        return newCommand;
    }
}
