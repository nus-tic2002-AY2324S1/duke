package task;

import command.*;

/**
 * CommandCreator class is a class that creates the command.
 * It has a method to create the command.
 */
public class CommandCreator {

    /**
     * Returns the command based on the keyword.
     *
     * @param keyword The keyword of the command.
     * @return The command.
     */
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
        case CHECKLISTS:
            return new ChecklistCommand();
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
