package tim.commands;

import tim.util.TaskList;
import tim.body.UI;
import tim.tasks.Task;

public class MarkCommand extends Command{

    /**
     * Identify index of task to be marked.
     * Calls TaskList.markUnmarkTask() to mark task.
     *
     * @param token The user input.
     * @param tasks The list of tasks.
     */
    public void execute(String[] token, TaskList tasks){
        int index;
        try {
            index = Integer.parseInt(token[1]);
            markUnmarkTask(index, true, tasks);
        } catch (ArrayIndexOutOfBoundsException AIO) {
            System.err.println("incorrect input for index!");
        }
    }

    /**
     * Marks or unmarks the task at the given index from the list.
     *
     * @param index Index of the task to be marked or unmarked.
     * @param markUnmark True if the task is to be marked, false if the task is to be unmarked.
     * @param list List of tasks.
     */
    static void markUnmarkTask(int index, boolean markUnmark, TaskList list){
        Task target = list.get(index-1);

        if((target.getIsDone().equals("x")) != markUnmark){
            if(markUnmark){
                System.out.println("Nice! I've marked this task as done:");
            } else {
                System.out.println("OK, I've marked this task as not done yet:");
            }
            target.setIsDone(markUnmark);
            UI.printSingle(index,list);
        } else {
            System.out.print("Task is already " + (markUnmark ? "marked" : "unmarked") + ".");

        }
        UI.printDash();

    }

}
