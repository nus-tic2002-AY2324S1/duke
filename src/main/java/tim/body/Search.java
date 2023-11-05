package tim.body;
import tim.tasks.Task;

import java.security.MessageDigest;
import java.util.ArrayList;

/**
 * Represents a Search class.
 */
public class Search {
    /**
     * Searches and prints tasks from task list that matches the keywords.
     * Partial Match is allowed.
     *
     * @param keyword The keyword to search for.
     * @param tasks The list of tasks to search from.
     */
    static void searchTaskList(String keyword, ArrayList<Task> tasks){
        ArrayList<Integer> listOfMatchedIndex = new ArrayList<Integer>();;
        for(int i = 0; i < tasks.size(); i++){
            Task x = tasks.get(i);
            int trueIndex = i + 1;     // user-facing index of task in list (from 1)
            if(x.getDescription().contains(keyword)){
                listOfMatchedIndex.add(trueIndex);
            }
        }
        Messenger.printSearchResult(listOfMatchedIndex, tasks);
    }

    /**
     * Searches and prints tasks from task list that matches the keywords exactly.
     *
     * @param keyword The keyword to search for.
     * @param tasks The list of tasks to search from.
     */
    static void strictSearchTaskList(String keyword, ArrayList<Task> tasks){
        ArrayList<Integer> listOfMatchedIndex = new ArrayList<Integer>();;
        for(int i = 0; i < tasks.size(); i++){
            Task x = tasks.get(i);
            int trueIndex = i + 1;     // user-facing index of task in list (from 1)
            if(x.getDescription().equals(keyword)){
                listOfMatchedIndex.add(trueIndex);
            }
        }
        Messenger.printStrictSearchResult(listOfMatchedIndex, tasks);
    }

}
