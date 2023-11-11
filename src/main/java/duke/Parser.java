package duke;
import commands.*;

public class Parser {
    static Command c;

    public static Command parse(String userInput){
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
            case "event":
            case "deadline":
                c = new AddCommand(userInput);
                break;
        }
        
        return c;
    }
}
