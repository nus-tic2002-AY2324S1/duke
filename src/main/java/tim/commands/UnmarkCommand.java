package tim.commands;

import tim.util.TaskList;

public class UnmarkCommand extends MarkCommand{

    /**
     * Identifies index of task to be unmarked.
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
            System.out.println("oh no!  missing input for index!");
        } catch (IllegalArgumentException IAE){
        System.out.println("oh no!  please include valid index of task to mark");
    }

    }
}
