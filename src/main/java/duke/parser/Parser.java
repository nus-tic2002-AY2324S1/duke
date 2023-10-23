package duke.parser;


import duke.command.*;
import duke.exception.DukeException;

public class Parser {

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
                try{
                    return new NewTaskCommand(fullCommand);
                } catch (Exception e) {
                    throw new DukeException("OOPS!!! Please input valid todo task command");
                }
            case "deadline":
                try{

                    return new NewTaskCommand(fullCommand);
                } catch (Exception e) {
                    throw new DukeException("OOPS!!! Please input valid deadline task command");
                }
            case "event":
                try{
                    return new NewTaskCommand(fullCommand);
                } catch (Exception e) {
                    throw new DukeException("OOPS!!! Please input valid event task command");
                }
            default:
                throw new DukeException("OOPS!!! Please input valid  command");
        }
    }
}
