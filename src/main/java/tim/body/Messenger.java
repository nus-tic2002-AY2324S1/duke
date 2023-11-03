package tim.body;
import tim.tasks.Task;
import java.util.ArrayList;
import java.util.Date;

/**
 * Represents as a Messenger object.
 * This class is responsible for printing messages and task list to the user.
 */
public class Messenger {

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
    static void printLogo(){
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
    static void greetings(){
        printDash();
        System.out.println("Hello I'm Tim. \nWhat can I do for you?");
        printDash();
    }

    /**
     * Prints a dash.
     */
    static void printDash(){
        System.out.println();
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the entire list of tasks.
     *
     * @param list List of tasks.
     */
    static void printList(ArrayList<Task> list){
        if (!list.isEmpty()){
            for(int i = 1; i <= list.size() ; i++){
                printSingle(i,list);
            }
        }
        printDash();
    }

    /**
     * Prints the task at the given index of the list.
     *
     * @param index Index of the task in the list.
     * @param list List of tasks.
     */
    static void printSingle(int index, ArrayList<Task> list){
        Task current = list.get(index-1);
        System.out.println(index + ". [" + current.getType()  + "] [" + current.getIsDone() + "] " + current.getDescription() );
    }

    /**
     * Prints the goodbye message.
     */
    static void goodbyeGreet(){
        System.out.println("Bye. Hope to see you again soon!");
        printDash();
    }
}
