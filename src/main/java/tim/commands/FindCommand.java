package tim.commands;

import tim.ui.Display;
import tim.util.Search;
import tim.util.TaskList;


public class FindCommand extends Command{

    /**
     * Identifies the keyword to be searched.
     * Calls Search.strictSearchTaskList() to search for tasks matching keyword.
     *
     * @param token The user input.
     * @param tasks The list of tasks.
     */
    public void execute(String[] token, TaskList tasks){
        try {
            String keyword = token[1];
            Search.strictSearchTaskList(keyword, tasks);
        } catch (Exception e) {
            System.out.println("oh no!  please include valid keyword to find");
            Display.printDash();
        }
    }
}
