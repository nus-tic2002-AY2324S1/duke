package tim.commands;

import tim.ui.Display;
import tim.util.TaskList;
import tim.tasks.ToDo;

public class ToDoCommand extends Command{

    /**
     * Identifies ToDo's task name to be added.
     * Calls TaskList.addToDo() to add task.
     *
     * @param token The user input.
     * @param tasks The list of tasks.
     */
    public void execute(String[] token, TaskList tasks){
        try {
            String taskName = token[1];
            addToDo(taskName, tasks);
        } catch (ArrayIndexOutOfBoundsException AIO) {
            System.out.println("oh no! what is the name of the task to complete?");
        }
    }

    /**
     * Adds a Todo task to the back of the list.
     *
     * @param inputEntry The input string from the user.
     * @param tasks List of tasks.
     */
    static void addToDo (String inputEntry, TaskList tasks){
        tasks.add(new ToDo(inputEntry));
        System.out.println("Gotcha! Added this task:");
        Display.printSingle(tasks.size(),tasks);
        System.out.println("now there is: "+ tasks.size() + " item(s)");
        Display.printDash();
    }
}
