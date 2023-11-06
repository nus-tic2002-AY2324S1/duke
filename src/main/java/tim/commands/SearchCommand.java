package tim.body.commands;

import tim.body.Search;
import tim.body.TaskList;


public class SearchCommand extends Command{

    /**
     * Identify the keyword to be searched.
     * Calls Search.strictSearchTaskList() to search for tasks containing keyword.
     *
     * @param token The user input.
     * @param tasks The list of tasks.
     */
    public void execute(String[] token, TaskList tasks){
        try {
            String keyword = token[1];
            Search.searchTaskList(keyword, tasks);
        } catch (Exception e) {
            System.err.println("please include valid keyword to find");
        }
    }
}
