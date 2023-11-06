package tim.body.commands;

import tim.body.TaskList;
import tim.body.UI;


public class DeleteCommand extends Command{

    /**
     * Identify index of task to be deleted.
     * Calls TaskList.deleteFromList() to delete task.
     *
     * @param token The user input.
     * @param tasks The list of tasks.
     */
    public void execute(String[] token, TaskList tasks){
        try {
            int deleteIndex = Integer.parseInt(token[1]);
            deleteFromList(deleteIndex, tasks);
        } catch (Exception e) {
            System.err.println("please include valid index of task to delete");
        }
    }

    /**
     * Deletes the task at the given index from the list.
     *
     * @param deleteIndex Index of the task to be deleted.
     * @param list List of tasks.
     */
    static void deleteFromList(int deleteIndex, TaskList list){
        System.out.print("Deleting: ");
        UI.printSingle(deleteIndex,list);
        UI.printDash();
        list.remove(deleteIndex-1);
        UI.printList(list);
    }

}
