import java.util.Arrays;
import java.util.Scanner;
import java.util.Date;
public class Duke {
    private static String[] list = {};

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
        System.out.println("Hello I'm Tim\nWhat can I do for you?");
        printDashes();
    }
    public static void printDashes(){
        System.out.println();
        System.out.println("____________________________________________________________");
    }

    public static void printList(){
        System.out.println("|| List: ||");
        if (list.length > 0){
            for(int i = 1; i <= list.length ; i++){
                System.out.println(i + ". " + list[i-1]);
            }
        }
        printDashes();
    }

    public static void addList(String inputEntry){
        list = Arrays.copyOf(list,list.length+1);
        list[list.length-1]=inputEntry;
        System.out.println("now there is: "+ list.length + " item(s)");
        System.out.println("added: "+ list[list.length-1] );
    }

    public static void printDate(){
        System.out.println("|| Current Date: ||");
        Date currentDate = new Date();
        System.out.println(currentDate);
        printDashes();
    }

    public static void goodbyeGreet(){
        System.out.println("Bye. Hope to see you again soon!");
        printDashes();
    }
    public static void main(String[] args) {
        printLogo();
        String input = "init" ;
        Scanner in = new Scanner(System.in);
        while(!input.equals("bye")){
            greetings();
            input = in.nextLine().toLowerCase();
            switch (input) {
                case "bye": goodbyeGreet();
                    break;
                case "list": printList();
                    break;
                case "date": printDate();
                    break;
                default: addList(input);
            }
        }



    }
}
