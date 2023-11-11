package duke;

import commands.*;
import commands.add.AddDeadline;
import commands.add.AddEvent;
import commands.add.AddToDo;
import tasks.InvalidInputException;

public class Parser {
    static Command c;

    public static Command parse(String userInput) throws InvalidInputException{
        String userCommand = userInput.split(" ")[0];
        String item;

        switch(userCommand.toLowerCase()){
            case "list":
                c = new ListCommand();
                break;
            case "mark":
                item = userInput.split(" ")[1];
                c = new MarkCommand(item);
                break;
            case "unmark":
                item = userInput.split(" ")[1];
                c = new UnmarkCommand(item);
                break;
            case "bye":
                c = new ByeCommand();
                break;
            case "delete":
                item = userInput.split(" ")[1];
                c = new DeleteCommand(item);
                break;
            case "todo":
                c = new AddToDo(userInput.trim());
                break;
            case "event":
                c = new AddEvent(userInput.trim());
                break;
            case "deadline":
                c = new AddDeadline(userInput.trim());
                break;
            default:
                throw new InvalidInputException("Oops! Look at the manual to see accepted inputs!");

        }
        
        return c;
    }
}
