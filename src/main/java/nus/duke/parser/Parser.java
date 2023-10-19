package nus.duke.parser;

import nus.duke.commands.ByeCommand;
import nus.duke.commands.Command;
import nus.duke.commands.DeadlineCommand;
import nus.duke.commands.DeleteCommand;
import nus.duke.commands.EventCommand;
import nus.duke.commands.ListCommand;
import nus.duke.commands.MarkCommand;
import nus.duke.commands.TodoCommand;
import nus.duke.commands.UnmarkCommand;
import nus.duke.exceptions.DukeException;
import nus.duke.exceptions.UnknownCommandDukeException;

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
