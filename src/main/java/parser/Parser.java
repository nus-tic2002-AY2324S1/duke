package parser;

import commands.ByeCommand;
import commands.Command;
import commands.DeadlineCommand;
import commands.DeleteCommand;
import commands.EventCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.TodoCommand;
import commands.UnmarkCommand;
import exceptions.DukeException;
import exceptions.UnknownCommandDukeException;

public class Parser {
    public static Command parse(String fullCommand) throws DukeException {
        String trimmedFullCommand = fullCommand.trim();
        String commandName = trimmedFullCommand.split(" ", -1)[0];
        String commandArgs = trimmedFullCommand.substring(commandName.length()).trim();
        switch (commandName) {
        case "bye":
            return new ByeCommand(commandArgs);
        case "deadline":
            return new DeadlineCommand(commandArgs);
        case "delete":
            return new DeleteCommand(commandArgs);
        case "event":
            return new EventCommand(commandArgs);
        case "list":
            return new ListCommand(commandArgs);
        case "mark":
            return new MarkCommand(commandArgs);
        case "todo":
            return new TodoCommand(commandArgs);
        case "unmark":
            return new UnmarkCommand(commandArgs);
        default:
            throw new UnknownCommandDukeException("Input: " + fullCommand);
        }
    }
}
