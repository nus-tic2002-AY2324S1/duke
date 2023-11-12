package duke;

import commands.*;
import commands.add.AddDeadline;
import commands.add.AddEvent;
import commands.add.AddToDo;

public class Parser {
    static Command c;

    // Stretch goal: Use dates and times in more meaningful ways. 
    // e.g., add a command to print deadlines/events occurring on a specific date.
    public static Command parse(String userInput) throws DukeException{
        String userCommand = userInput.split(" ")[0];
        int item = 0;

        if (userCommand.toLowerCase().equals("mark") || userCommand.toLowerCase().equals("unmark") || userCommand.toLowerCase().equals("delete")){
            if (userInput.split(" ").length < 2){
                throw new DukeException("Missing item number!");
            }
            try{
                item = Integer.parseInt(userInput.split(" ")[1]);
            }
            catch (NumberFormatException e){
                throw new DukeException("Ensure the item number is a valid integer!");
            }        
        }

        switch(userCommand.toLowerCase()){
            case "list":
                c = new ListCommand();
                break;
            case "mark":
                c = new MarkCommand(item);
                break;
            case "unmark":
                c = new UnmarkCommand(item);
                break;
            case "bye":
                c = new ByeCommand();
                break;
            case "delete":
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
            case "due":
                c = new DueCommand(userInput.trim());
                break;
            default:
                throw new DukeException("Oops! Look at the manual to see accepted inputs!");

        }
        
        return c;
    }
}
