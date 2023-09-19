import java.util.Arrays;
import java.util.Scanner;
import java.util.Date;
public class Tim {
    private static Task[] list = {};

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
        if (list.length > 0){
            for(int i = 1; i <= list.length ; i++){
                printSingle(i);
            }
        }
        printDashes();
    }

    public static void printSingle(int index){
        Task current = list[index-1];
        System.out.println(index + ". [" + current.type  + "] [" + current.getIsDone() + "] " + current.getDescription() );
    }

    public static void addToDo (String inputEntry){
        list = Arrays.copyOf(list,list.length+1);
        list[list.length-1]= new ToDo(inputEntry);
        System.out.println("Gotcha! Added this task:");
        printSingle(list.length);
        System.out.println("now there is: "+ list.length + " item(s)");
        printDashes();
    }

    public static void addDeadline (String inputEntry){
        String[] inputTokenized = {};
        String by = "";
        try{
            inputTokenized = inputEntry.split(" /by ",2);
            by = inputTokenized[1];
            String description = inputTokenized[0];
            list = Arrays.copyOf(list,list.length+1);
            list[list.length-1]= new Deadline(description,by);
            System.out.println("Gotcha! Added this task:");
            printSingle(list.length);
            System.out.println("now there is: "+ list.length + " item(s)");
            printDashes();
        } catch (Exception e) {
            System.err.println("you've missed out [/by] !");
        }

    }

    public static void addEvent (String inputEntry){
        String[] inputTokenized = {};
        String from = "";
        String to = "";
        try {
            inputTokenized =  inputEntry.split(" /from ",2);
            String description = inputTokenized[0];
            to = inputTokenized[1].split(" /to ", 2)[1];
            from = inputTokenized[1].split(" /to ", 2)[0];
            list = Arrays.copyOf(list,list.length+1);
            list[list.length-1]= new Event(description,from,to);
            System.out.println("Gotcha! Added this task:");
            printSingle(list.length);
            System.out.println("now there is: "+ list.length + " item(s)");
            printDashes();
        } catch (Exception e) {
            System.err.println("you've missed out either [/from] or [/to] !");
        }

    }

    public static void markUnmarkTask(int index, boolean markUnmark){
        Task target = list[index-1];
        if(markUnmark){
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        target.setIsDone(markUnmark);
        printSingle(index);
        printDashes();
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
        greetings();
        String input = "init" ;
        int index = -1;
        Scanner in = new Scanner(System.in);

        while(!input.equalsIgnoreCase("bye")){
            input = in.nextLine();
            String[] token = input.split(" ",2);
            String taskName;
            String mode = token[0].toLowerCase();
            System.out.println("|| "+ mode + " ||");
            switch (mode) {
                case "bye": goodbyeGreet();
                    break;
                case "list": printList();
                    break;
                case "date": printDate();
                    break;
                case "mark":
                    try {
                        index = Integer.parseInt(token[1]);
                        markUnmarkTask(index, true);
                    } catch (ArrayIndexOutOfBoundsException AIO){
                        System.err.println("incorrect input for index!");
                    }
                    break;
                case "unmark":
                    try {
                        index = Integer.parseInt(token[1]);
                        markUnmarkTask(index, false);
                    } catch (ArrayIndexOutOfBoundsException AIO){
                        System.err.println("incorrect input for index!");
                    }
                    break;
                case "todo":
                    try {
                        if(token[1].isBlank()){
                            throw new BlankInputException("todo blank");
                        }
                        taskName = token[1];
                        addToDo(taskName);
                    } catch (ArrayIndexOutOfBoundsException | BlankInputException AIO){
                        System.err.println("what is the name of the task to complete?");
                    }
                    break;
                case "deadline":
                    try {
                        if(token[1].isBlank()){
                            throw new BlankInputException("event blank");
                        }
                        taskName = token[1];
                        addDeadline(taskName);
                    } catch (ArrayIndexOutOfBoundsException | BlankInputException AIO){
                        System.err.println("what is the name of the deadline agenda?");
                    }
                    break;
                case "event":
                    try {
                        if(token[1].isBlank()){
                            throw new BlankInputException("event blank");
                        }
                        taskName = token[1];
                        addEvent(taskName);
                    } catch (ArrayIndexOutOfBoundsException | BlankInputException AIO){
                        System.err.println("what is the name of the event?");
                    }
                    break;
                default: System.err.println("I've not learn to do this yet!!");
            }
        }



    }
}
