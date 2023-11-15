package parser;

import commands.ByeCommand;
import commands.Command;
import commands.DeleteCommand;
import commands.DueCommand;
import commands.FindCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.SetCommand;
import commands.UnmarkCommand;
import commands.add.DeadlineCommand;
import commands.add.EventCommand;
import commands.add.ToDoCommand;
import constants.ErrorMessages;
import constants.RegExp;
import exceptions.DukeException;

public class Parser {
    static Command c;

    /**
     * Returns a {@code Command} object based on user input.
     * <p>
     * Further validation will be done inside the {@code Command} object. Users can later call the
     * {@link Command#execute()} method on the returned object.
     * </p>
     *
     * @param userInput the user input received.
     * @return A {@code Command} object based on user input.
     * @throws DukeException if input validations fail.
     */
    public static Command parse(String userInput) throws DukeException {
        String userCommand = userInput.split(RegExp.SPACE_DELIMITER)[0];
        userInput = userInput.trim().toLowerCase();

        switch (userCommand.toLowerCase()) {
        case "list":
            c = new ListCommand();
            break;
        case "mark":
            c = new MarkCommand(userInput);
            break;
        case "unmark":
            c = new UnmarkCommand(userInput);
            break;
        case "bye":
            c = new ByeCommand();
            break;
        case "delete":
            c = new DeleteCommand(userInput);
            break;
        case "todo":
            c = new ToDoCommand(userInput);
            break;
        case "event":
            c = new EventCommand(userInput);
            break;
        case "deadline":
            c = new DeadlineCommand(userInput);
            break;
        case "due":
            c = new DueCommand(userInput);
            break;
        case "set":
            c = new SetCommand(userInput);
            break;
        case "find":
            c = new FindCommand(userInput);
            break;
        default:
            throw new DukeException(ErrorMessages.INVALID_USER_INPUT);
        }

        return c;
    }
}
