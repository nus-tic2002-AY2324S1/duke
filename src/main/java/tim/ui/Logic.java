package tim.ui;

import tim.commands.*;
import tim.util.Storage;
import tim.util.TaskList;



/**
 * Represents as a Logic object.
 * Executes the actions based on the parsed user input from the parser.
 * Saves the list of tasks to storage if list is modified.
 */
public class Logic {

    /**
     * Executes the action based on the parsed user input from the parser.
     *
     * @param command  Action to be executed.
     * @param tasks  List of tasks.
     * @param token User input split into an array of strings.
     */
    static void executeAction(String command, TaskList tasks, String[] token) {
        int index;
        String taskName;
        try {
            ListOfCommands verifiedCommands = ListOfCommands.valueOf(command.toUpperCase());
            switch (verifiedCommands) {
            case BYE:
                Display.goodbyeGreet();
                break;
            case LIST:
                Display.printList(tasks);
                break;
            case DATE:
                Display.printDate();
                break;
            case MARK:
                new MarkCommand().execute(token, tasks);
                Storage.saveList(tasks);
                break;
            case UNMARK:
                new UnmarkCommand().execute(token, tasks);
                Storage.saveList(tasks);
                break;
            case TODO:
                new ToDoCommand().execute(token, tasks);
                Storage.saveList(tasks);
                break;
            case DEADLINE:
                new DeadlineCommand().execute(token, tasks);
                Storage.saveList(tasks);
                break;
            case EVENT:
                new EventCommand().execute(token, tasks);
                Storage.saveList(tasks);
                break;
            case DELETE:
                new DeleteCommand().execute(token, tasks);
                Storage.saveList(tasks);
                break;
            case FIND:
                new FindCommand().execute(token, tasks);
                break;
            case SEARCH:
                new SearchCommand().execute(token, tasks);
                break;
            case SNOOZE:
                new SnoozeCommand().execute(token, tasks);
                Storage.saveList(tasks);
                break;
            case CLEAR:
                new ClearCommand().execute(token, tasks);
                Storage.saveList(tasks);
                break;
            case HELP:
                Display.printHelp();
                break;
            default:
                System.out.println("oh no!  this should not happen!");
            }
        } catch (IllegalArgumentException IAE) {
            System.out.println("oh no!  I've not learn to do this yet!!");
        }
    }
}
