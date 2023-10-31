package task;

//import command.*;

import command.ByeCommand;
import command.ListCommand;
import command.AddTaskCommand;
import command.BlahCommand;
import command.DeleteCommand;
import command.FindCommand;
import command.HelpCommand;
import command.MarkCommand;
import command.SortCommand;
import command.SwitchCommand;
import command.UnmarkCommand;
import command.UndoCommand;
import command.CommandInterface;

public class CommandCreator {

    public CommandInterface create(Keyword keyword) {
        switch (keyword) {
        case LIST:
            return new ListCommand();
        case BLAH:
            return new BlahCommand();
        case BYE:
            return new ByeCommand();
        case MARK:
            return new MarkCommand();
        case UNMARK:
            return new UnmarkCommand();
        case DELETE:
            return new DeleteCommand();
        case FIND:
            return new FindCommand();
        case SORT:
            return new SortCommand();
        case HELP:
            return new HelpCommand();
        case UNDO:
            return new UndoCommand();
        case SWITCH:
            return new SwitchCommand();
        case ADD:
        case TODO:
        case DEADLINE:
        case EVENT:
        case DEFAULT:
        default:
            return new AddTaskCommand();

        }
    }
}
