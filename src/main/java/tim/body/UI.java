package tim.body;
import tim.commands.ListOfCommands;
import tim.tasks.Task;
import tim.util.TaskList;
import java.util.ArrayList;
import java.util.Date;

/**
 * Represents as a UI object.
 * This class is responsible for printing messages and task list to the user.
 */
public class UI {

    /**
     * Prints the help message with list of usable commands.
     */
    static void printHelp(){
        System.out.println("Here are the list of commands you can use:");
        for (ListOfCommands command : ListOfCommands.values()) {
            System.out.println(command);
        }
        printDash();
    }
    /**
     * Prints the current date.
     */
    static void printDate(){
        System.out.println("|| Current Date: ||");
        Date currentDate = new Date();
        System.out.println(currentDate);
        printDash();
    }

    /**
     * Prints the Tim's logo.
     */
    public static void printLogo(){
        String logo =
                " _______                 \n"
                        +   "|__   __| [ ]  __  __    \n"
                        +   "   | |    | | |  \\/  |  \n"
                        +   "   | |    | | | |\\/| |  \n"
                        +   "   |_|    |_| |_|  |_|   \n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Prints Tim's greeting.
     */
    public static void greetings(){
        printDash();
        System.out.println("Hello I'm Tim. \nWhat can I do for you?");
        printDash();
    }

    /**
     * Prints a dash.
     */
    public static void printDash(){
        System.out.println();
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the entire list of tasks.
     *
     * @param tasks List of tasks.
     */
    public static void printList(TaskList tasks){
        if (!tasks.isEmpty()){
            for(int i = 1; i <= tasks.size() ; i++){
                printSingle(i,tasks);
            }
        } else {
            System.out.println("There is no task in the list!");
        }
        printDash();
    }

    /**
     * Prints the task at the given index of the list.
     *
     * @param index Index of the task in the list.
     * @param tasks List of tasks.
     */
    public static void printSingle(int index, ArrayList<Task> tasks){
        if(index>tasks.size()){
            System.out.println("There is no task at index " + index + "!");
        } else {
            Task current = tasks.get(index-1);
            System.out.println(index + ". [" + current.getType()  + "] [" + current.getIsDone() + "] " + current.getDescription() );
        }

    }

    /**
     * Prints the result of the search.
     *
     * @param listOfMatchedIndex List of indexes of the tasks that matches the keywords.
     * @param tasks List of tasks.
     */
    public static void printSearchResult(ArrayList<Integer> listOfMatchedIndex, ArrayList<Task> tasks){
        if(listOfMatchedIndex.isEmpty()){
            System.out.println("No task matches the keywords!");
        } else {
            System.out.println("I found task"
                    + (listOfMatchedIndex.size() > 1 ? "s" : "")
                    + " that contain the keywords:");

            for(int i = 0; i < listOfMatchedIndex.size(); i++){
                printSingle(listOfMatchedIndex.get(i),tasks);
            }
        }
        printDash();
    }

    /**
     * Prints the result of the strict search.
     *
     * @param listOfMatchedIndex List of indexes of the tasks that matches the keywords.
     * @param tasks List of tasks.
     */
    public static void printStrictSearchResult(ArrayList<Integer> listOfMatchedIndex, ArrayList<Task> tasks){
        if(listOfMatchedIndex.isEmpty()){
            System.out.println("No task matches the keywords!");
        } else {
            System.out.println("I found task"
                    + (listOfMatchedIndex.size() > 1 ? "s" : "")
                    + " that exactly matches the keywords:");
            for(int i = 0; i < listOfMatchedIndex.size(); i++){
                printSingle(listOfMatchedIndex.get(i),tasks);
            }
        }
        printDash();
    }

    /**
     * Prints the goodbye message.
     */
    static void goodbyeGreet(){
        System.out.println("Bye. Hope to see you again soon!");
        printDash();
    }
}
