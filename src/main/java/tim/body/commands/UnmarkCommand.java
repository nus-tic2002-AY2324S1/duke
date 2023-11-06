package tim.body.commands;

import tim.body.TaskList;

public class UnmarkCommand extends MarkCommand{

    /**
     * Identify index of task to be unmarked.
     * Calls TaskList.markUnmarkTask() to unmark task.
     *
     * @param token The user input.
     * @param tasks The list of tasks.
     */
    public void execute(String[] token, TaskList tasks){
        int index;
        try {
            index = Integer.parseInt(token[1]);
            markUnmarkTask(index, false, tasks);
        } catch (ArrayIndexOutOfBoundsException AIO) {
            System.err.println("incorrect input for index!");
        }
    }
}
