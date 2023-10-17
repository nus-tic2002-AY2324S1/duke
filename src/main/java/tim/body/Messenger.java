package tim.body;
import tim.tasks.Task;
import java.util.ArrayList;
import java.util.Date;

public class Messenger {

    static void printDate(){
        System.out.println("|| Current Date: ||");
        Date currentDate = new Date();
        System.out.println(currentDate);
        printDashes();
    }

    static void printLogo(){
        String logo =
                " _______                 \n"
                        +   "|__   __| [ ]  __  __    \n"
                        +   "   | |    | | |  \\/  |  \n"
                        +   "   | |    | | | |\\/| |  \n"
                        +   "   |_|    |_| |_|  |_|   \n";
        System.out.println("Hello from\n" + logo);
    }

    static void greetings(){
        printDashes();
        System.out.println("Hello I'm Tim. \nWhat can I do for you?");
        printDashes();
    }
    static void printDashes(){
        System.out.println();
        System.out.println("____________________________________________________________");
    }

    static void printList(ArrayList<Task> list){
        if (!list.isEmpty()){
            for(int i = 1; i <= list.size() ; i++){
                printSingle(i,list);
            }
        }
        printDashes();
    }

    static void printSingle(int index, ArrayList<Task> list){
        Task current = list.get(index-1);
        System.out.println(index + ". [" + current.getType()  + "] [" + current.getIsDone() + "] " + current.getDescription() );
    }

    static void goodbyeGreet(){
        System.out.println("Bye. Hope to see you again soon!");
        printDashes();
    }
}
