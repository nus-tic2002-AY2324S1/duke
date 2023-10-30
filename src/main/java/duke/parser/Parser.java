package duke.parser;


import duke.command.*;
import duke.exception.DukeException;


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
    public static Command parse (String fullCommand) throws DukeException {
        String[] parts = fullCommand.split(" ");
        String command = parts[0].toLowerCase();

        switch (command){
            case "list":
                return new ListCommand();
            case "bye":
                return new ByeCommand();
            case "delete":
                try{
                    int index = Integer.parseInt(parts[1])-1;
                    return new DeleteCommand(index);
                } catch (Exception e){
                    throw new DukeException("OOPS!!! Please input item number you want to delete");
                }
            case "mark":
                try{
                    int index = Integer.parseInt(parts[1])-1;
                    return new MarkCommand(index);
                } catch (Exception e){
                    throw new DukeException("OOPS!!! Please input item number you want to mark");
                }
            case "unmark":
                try{
                    int index = Integer.parseInt(parts[1])-1;
                    return new UnmarkCommand(index);
                } catch (Exception e) {
                    throw new DukeException("OOPS!!! Please input item number you want to unmark");
                }
            case "todo":
            case "deadline":
            case "event":
                try{
                    return new NewTaskCommand(fullCommand);
                } catch (Exception e) {
                    throw new DukeException("OOPS!!! Please input valid todo task command");
                }
            default:
                throw new DukeException("OOPS!!! Please input valid  command");
        }
    }
}
