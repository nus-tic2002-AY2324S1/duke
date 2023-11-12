package tim.commands;

import tim.ui.Display;
import tim.util.TaskList;


public class DeleteCommand extends Command{

    /**
     * Identifies index of task to be deleted.
     * Calls TaskList.deleteFromList() to delete task from task list.
     *
     * @param token The user input.
     * @param tasks The list of tasks.
     */
    public void execute(String[] token, TaskList tasks){
        try {
            int deleteIndex = Integer.parseInt(token[1]);
            deleteFromList(deleteIndex, tasks);
        } catch (Exception e) {
            System.out.println("oh no!  please include valid index of task to delete");
        }
    }

    /**
     * Deletes the task at the given index from the list.
     *
     * @param deleteIndex Index of the task to be deleted.
     * @param tasks List of tasks.
     */
    static void deleteFromList(int deleteIndex, TaskList tasks){
        TaskList temp = tasks;
        tasks.remove(deleteIndex-1);
        System.out.print("Deleting: ");
        Display.printSingle(deleteIndex,temp);
        Display.printDash();
        Display.printList(tasks);
    }

}
