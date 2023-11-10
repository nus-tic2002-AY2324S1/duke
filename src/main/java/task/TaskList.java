package task;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> actions;
    private int inputCount;

    public TaskList(ArrayList<Task> actions) {
        this.actions = actions;
        this.inputCount = actions.size();
    }
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

    public int getNumber(String input){
        String[] words = input.split(" ");
        for (String word : words) {
            try {
                int no = Integer.parseInt(word);
                return no;
            } catch (NumberFormatException nfe) {
                continue;
            }
        }
        return -1;
    }

    public boolean validateInput(String input){
        String trimInput = input.trim().toLowerCase();
        if (!trimInput.startsWith("todo")&&!trimInput.startsWith("deadline")
                &&!trimInput.startsWith("event")){
            return false;
        }
        return true;
    }
    public Task getNewTask (String input){
        if (input.trim().toLowerCase().startsWith("deadline")){
            Deadline newDeadline = new Deadline(input, false);
            return newDeadline;
        }else if (input.trim().toLowerCase().startsWith("event")){
            Event newEvent = new Event(input, false);
            return newEvent;
        }else  {
            Task newTask = new Task(input, false);
            return newTask;
        }
    }
}
