package duke.parser;


import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ListCommand;
import duke.command.ByeCommand;
import duke.command.MarkCommand;
import duke.command.UndoCommand;
import duke.command.UnmarkCommand;
import duke.command.FindCommand;
import duke.command.NewTaskCommand;
import duke.exception.DukeException;
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
                    UI.showMessage("OOPS!!! Please input item number you want to delete");
                    break;
                }
            case "mark":
                try {
                    int index = Integer.parseInt(parts[1]) - 1;
                    newCommand = new MarkCommand(index);
                    break;
                } catch (Exception e) {
                    UI.showMessage("OOPS!!! Please input item number you want to mark");
                    break;
                }
            case "unmark":
                try {
                    int index = Integer.parseInt(parts[1]) - 1;
                    newCommand = new UnmarkCommand(index);
                    break;
                } catch (Exception e) {
                    UI.showMessage("OOPS!!! Please input item number you want to unmark");
                    break;
                }
            case "todo":
            case "deadline":
            case "event":
                try {
                    newCommand = new NewTaskCommand(fullCommand);
                    break;
                } catch (Exception e) {
                    UI.showMessage("OOPS!!! Please input valid Task command");
                    break;
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
                    UI.showMessage("OOPS!!! Please provide a keyword to search");
                    break;
                }
            default:
                UI.showMessage("OOPS!!! Please input valid  command");
                break;
        }
        return newCommand;
    }
}
