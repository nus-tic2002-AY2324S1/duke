package duke.parser;

import duke.commands.*;
import duke.commands.add.DeadlineCommand;
import duke.commands.add.EventCommand;
import duke.commands.add.ToDoCommand;
import duke.exceptions.DukeException;

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
        String userCommand = userInput.split(" ")[0];

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
            c = new ToDoCommand(userInput.trim());
            break;
        case "event":
            c = new EventCommand(userInput.trim());
            break;
        case "deadline":
            c = new DeadlineCommand(userInput.trim());
            break;
        case "due":
            c = new DueCommand(userInput.trim());
            break;
        case "set":
            c = new SetCommand(userInput.trim());
            break;
        case "find":
            c = new FindCommand(userInput.trim());
            break;
        default:
            throw new DukeException("Oops! Look at the manual to see accepted inputs!");
        }

        return c;
    }
}
