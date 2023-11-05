package tim.body;

import tim.body.commands.*;


/**
 * Represents as a Logic object.
 * This class is the logic of the program.
 * It executes the actions based on the parsed user input from the parser.
 */
public class Logic {

    /**
     * Executes the action based on the parsed user input from the parser.
     *
     * @param mode  Action to be executed.
     * @param tasks  List of tasks.
     * @param token User input split into an array of strings.
     */
    static void executeAction (String mode, TaskList tasks, String[] token){
        int index;
        String taskName;
        switch (mode) {
        case "bye":
            UI.goodbyeGreet();
            break;
        case "list":
            UI.printList(tasks);
            break;
        case "date":
            UI.printDate();
            break;
        case "mark":
            new MarkCommand().execute(token, tasks);
            break;
        case "unmark":
            new UnmarkCommand().execute(token, tasks);
            break;
        case "todo":
            new ToDoCommand().execute(token, tasks);
            break;
        case "deadline":
            new DeadlineCommand().execute(token, tasks);
            break;
        case "event":
            new EventCommand().execute(token, tasks);
            break;
        case "delete":
            new DeleteCommand().execute(token, tasks);
            break;
        case "find":
            new FindCommand().execute(token, tasks);
            break;
        case "search" :
            new SearchCommand().execute(token, tasks);
            break;
        case "snooze" :
            new SnoozeCommand().execute(token, tasks);
            break;
        default:
            System.err.println("I've not learn to do this yet!!");
        }
        if (!(mode.equals("list") || mode.equals("date") || mode.equals("bye"))) {
            Storage.saveList(tasks);
        }
    }
}
