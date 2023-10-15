package tim;

import java.util.ArrayList;
import java.util.Date;

public class Messenger {

    public static void printDate(){
        System.out.println("|| Current Date: ||");
        Date currentDate = new Date();
        System.out.println(currentDate);
        printDashes();
    }

    public static void printLogo(){
        String logo =
                " _______                 \n"
                        +   "|__   __| [ ]  __  __    \n"
                        +   "   | |    | | |  \\/  |  \n"
                        +   "   | |    | | | |\\/| |  \n"
                        +   "   |_|    |_| |_|  |_|   \n";
        System.out.println("Hello from\n" + logo);
    }

    public static void greetings(){
        printDashes();
        System.out.println("Hello I'm Tim. \nWhat can I do for you?");
        printDashes();
    }
    public static void printDashes(){
        System.out.println();
        System.out.println("____________________________________________________________");
    }

    public static void printList(ArrayList<Task> list){
        if (!list.isEmpty()){
            for(int i = 1; i <= list.size() ; i++){
                printSingle(i,list);
            }
        }
        printDashes();
    }

    public static void printSingle(int index, ArrayList<Task> list){
        Task current = list.get(index-1);
        System.out.println(index + ". [" + current.type  + "] [" + current.getIsDone() + "] " + current.getDescription() );
    }

    public static void goodbyeGreet(){
        System.out.println("Bye. Hope to see you again soon!");
        printDashes();
    }
}
