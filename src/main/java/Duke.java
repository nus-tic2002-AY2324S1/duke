import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Duke {

    public static ArrayList<Task> actions = new ArrayList<>();
    public static int inputCount =0;


    public static String taskStatus(boolean status){
        if (status){
            return "[X]";
        }else {
            return "[ ]";
        }
    }
    public static void addTasks(String input){
        System.out.println(
                "    ____________________________________________________________\n" +
                        "     added: " + input + "\n" +
                        "    ____________________________________________________________\n" );
        Task newTask = new Task(input, false);
        actions.add(newTask) ;
        inputCount++;
    }

    public static int getNumber(String input){
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



    public static void printTaskList(){
        System.out.println("    ____________________________________________________________\n"+
                "    Here are the tasks in your list: ");
        for (int i = 1; i <= inputCount; i++){
            System.out.println("    "+ i + "."+taskStatus(actions.get(i-1).getIsDone())+actions.get(i-1).getDescription());
        }
        System.out.println("    ____________________________________________________________");
    }

    public static void printHeader(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\n"+
                "Hello! I'm venni\n" +
                "What can I do for you?\n"
        );
    }

    public static void main(String[] args) {
        //Adding testing items in Task
        Task task1 = new Task("read book", false);
        Task task2 = new Task("return book", false);
        Task task3 = new Task("buy bread", false);

        actions.add(task1);
        actions.add(task2);
        actions.add(task3);
        inputCount +=3;

        Scanner scanner = new Scanner(System.in);
        printHeader();
        String input = scanner.nextLine();

        //read string and execute functions accordingly.
        while (!input.trim().equalsIgnoreCase("bye")){
            //added function that handles whitespace as well
//            if (input.trim().isEmpty()){
//                continue;
//            }
            if (input.startsWith("mark")){
                int index = getNumber(input);
                actions.get(index-1).setIsDone(true);
                System.out.println("    ____________________________________________________________\n"+
                        "    Nice! I've marked this task as done: ");
                //print "[X] return book"
                System.out.println("    "+  " "+taskStatus(actions.get(index-1).getIsDone())+ " "+actions.get(index-1).getDescription());
                System.out.println("    ____________________________________________________________");
            }else if (input.startsWith("unmark")){
                int index = getNumber(input);
                actions.get(index-1).setIsDone(false);
                System.out.println("    ____________________________________________________________\n"+
                        "    OK, I've marked this task as not done yet: ");
                //print "[ ] return book"
                System.out.println("    "+  " "+taskStatus(actions.get(index-1).getIsDone())+ " "+actions.get(index-1).getDescription());
                System.out.println("    ____________________________________________________________");
            }else if (!input.trim().equals("list")){
                addTasks(input);
            }else{
                printTaskList();
            }
            input = scanner.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!\n" +
                            "____________________________________________________________");
    }

}
