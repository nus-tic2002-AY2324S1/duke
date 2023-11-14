package task;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * A functional class contains All the operations to a <code>Task</code> list.
 * Including add OR delete <code>Task</code>,
 * also print all the <code>Task</code> in the <code>Task</code> list.
 */
public class TaskList {

    /**
     * Constructor for TaskList class
     */
    public TaskList(){}
    /**
     * Returns list of <code>Task</code> after removing the input task.
     * function will only be called when user wish to delete particular task.
     * Task to be deleted will identify the task
     * by calling <code>getNumber()</code> function to get the index of the task.
     *
     * @param input String input contains task to be removed by user.
     * @param actions the latest version of list of <code>Task</code>
     * @return the updated list of <code>Task</code>.
     */
    public ArrayList<Task> removeTasks(String input, ArrayList<Task> actions){
        int taskNo = getNumber(input);
        //e.g. "   [E][ ] project meeting (from: Aug 6th 2pm to: 4pm)"

        actions.get(taskNo-1).printTask();
        actions.remove(taskNo-1) ;
        //print task number as of now
        System.out.println(
                "     Now you have "+actions.size()+ " tasks in the list.\n" +
                        "    ____________________________________________________________\n");
        return actions;
    }

    /**
     * function to print out the list of task with numbers in sequence.
     *
     * @param actions the latest version of list of <code>Task</code>
     */
    public void printTaskList(ArrayList<Task> actions){
        System.out.println("    ____________________________________________________________\n"+
                "    Here are the tasks in your list: ");
        for (int i = 1; i <= actions.size(); i++){
            System.out.print("       ");
            System.out.print(" " + i +".");
            actions.get(i-1).printTask();
        }
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Returns list of <code>Task</code> after adding the input task.
     * function will only be called when user wish to add particular task.
     *
     * @param input String input contains task to be added by user.
     * @param actions the latest version of list of <code>Task</code>
     * @return updated list of <code>Task</code>.
     */
    public ArrayList<Task> addTasks(String input, ArrayList<Task> actions){
        boolean isValid = validateInput(input);
        //if isValid ==false, stop process.
        Task newTask = getNewTask(input);
        if (!isValid){
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        } else if(newTask.getDescription()==null){
            System.out.println("OOPS!!! The description of a todo task cannot be empty.");
        }else{
            System.out.println(
                    "    ____________________________________________________________\n" +
                            "     Got it. I've added this task:");
            //e.g. "   [E][ ] project meeting (from: Aug 6th 2pm to: 4pm)"
            System.out.print("       ");
            newTask.printTask();

            actions.add(newTask) ;
            //addInputCount();

            //print task number as of now
            System.out.println(
                    "     Now you have "+actions.size()+ " tasks in the list.\n" +
                            "    ____________________________________________________________\n");
        }

        return actions;
    }

    /**
     * Returns index in the <code>Task</code> list of selected task.
     * If there is no number found in the input String, NaN is returned.
     * function will only be called when user entered a Task number
     *
     * @param input String input contains index of the task user wish to action on.
     * @return integer that represents index in the <code>Task</code> list of selected task.
     * @throws  NumberFormatException if the string cannot be converted to Integer.
     */
    public int getNumber(String input){
        String[] words = input.split(" ");
        for (String word : words) {
            try {
                return Integer.parseInt(word);
            } catch (NumberFormatException nfe) {
                continue;
            }
        }
        return -1;
    }


    /**
     * Returns true if user input fits into <code>Task</code> object.
     * If input doesn't starts with Todo OR Event OR Deadline , return false.
     *
     * @param input String input contains task to be added by user.
     * @return updated list of <code>Task</code>.
     */
    public boolean validateInput(String input){
        String trimInput = input.trim().toLowerCase();
        return trimInput.startsWith("todo") || trimInput.startsWith("deadline")
                || trimInput.startsWith("event");
    }

    /**
     * Identifies the type of <code>Task</code>, <code>Deadline</code>
     * OR <code>Event</code> OR todo task.
     *
     * @param input String input contains task to be added by user.
     * @return A new <code>Task</code> according to the type of the task.
     */
    public Task getNewTask (String input){
        if (input.trim().toLowerCase().startsWith("deadline")){
            return new Deadline(input, false);
        }else if (input.trim().toLowerCase().startsWith("event")){
            return new Event(input, false);
        }else  {
            return new Task(input, false);
        }
    }
}
