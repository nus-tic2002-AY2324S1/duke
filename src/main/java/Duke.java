import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Duke {

    public static ArrayList<Task> actions = new ArrayList<>();
    public static int inputCount =0;

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

    public static void addTasks(String input){
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
            inputCount++;

            //print task number as of now
            System.out.println(
                    "     Now you have "+inputCount+ " tasks in the list.\n" +
                            "    ____________________________________________________________\n");
        }
    }

    public static void removeTasks(String input){
        int taskNo = getNumber(input);
        System.out.println(
                "    ____________________________________________________________\n" +
                        "     Noted. I've removed this task:");
        //e.g. "   [E][ ] project meeting (from: Aug 6th 2pm to: 4pm)"
        System.out.print("       ");
        actions.get(taskNo-1).printTask();

        actions.remove(taskNo-1) ;
        inputCount--;
        //print task number as of now
        System.out.println(
                "     Now you have "+inputCount+ " tasks in the list.\n" +
                        "    ____________________________________________________________\n");

    }

    public static void printTaskList(){
        System.out.println("    ____________________________________________________________\n"+
                "    Here are the tasks in your list: ");
        for (int i = 1; i <= inputCount; i++){
            System.out.print("       ");
            System.out.print(" " + i +".");
            actions.get(i-1).printTask();
        }
        System.out.println("    ____________________________________________________________");
    }

    public static boolean validateInput(String input){
        String trimInput = input.trim().toLowerCase();
        if (!trimInput.startsWith("todo")&&!trimInput.startsWith("deadline")
                &&!trimInput.startsWith("event")){
            return false;
        }
        return true;
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

    public static Task getNewTask (String input){
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

    public static void convertToTxtFile() throws IOException {
        //handling file/folder does not exit
        String filePath = "./data/duke.txt";
        File file = new File(filePath);


//        if(!file.exists() && !file.isDirectory()) {
//            File file = new File(filePath);
//        }
        try {
            if (file.exists()) {
                if (file.delete()) {
                    System.out.println("File deleted successfully.");
                } else {
                    System.out.println("Failed to delete the file.");
                    return;
                }
            }

            FileWriter fw = new FileWriter(filePath);
            for (Task task : actions) {
                fw.write(task.toTextFile());
            }
            fw.close();

        }catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
            //handle file overwritting
//            try {
//                FileWriter fw = new FileWriter(filePath);
//                for (Task task : actions) {
//                    fw.write(task.toTextFile());
//                }
//                fw.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

    }

    public static void main(String[] args) {
        //Adding testing items in Task
        //what if the situation without todo
        Task task1 = new Task("todo read book", false);
        Task task2 = new Task("todo return book", false);
        Task task3 = new Task("todo buy bread", false);

        actions.add(task1);
        actions.add(task2);
        actions.add(task3);
        inputCount +=3;

        Scanner scanner = new Scanner(System.in);
        printHeader();
        String input = scanner.nextLine();
        String trimInput = input.trim().toLowerCase();
        //read string and execute functions accordingly.
        while (!input.trim().equalsIgnoreCase("bye")){
            //added function that handles whitespace as well
//            if (input.trim().isEmpty()){
//                continue;
//            }
            if (input.trim().toLowerCase().startsWith("mark")){
                int index = getNumber(input);
                actions.get(index-1).setIsDone(true);
                System.out.println("    ____________________________________________________________\n"+
                        "    Nice! I've marked this task as done: ");
                System.out.print("       ");
                //print "[X] return book"
                actions.get(index-1).printTask();
                System.out.println("    ____________________________________________________________");
            }else if (input.trim().toLowerCase().startsWith("unmark")){
                int index = getNumber(input);
                actions.get(index-1).setIsDone(false);
                System.out.println("    ____________________________________________________________\n"+
                        "    OK, I've marked this task as not done yet: ");
                System.out.print("       ");
                //print "[ ] return book"
                actions.get(index-1).printTask();
                System.out.println("    ____________________________________________________________");

            }else if (input.trim().toLowerCase().startsWith("delete")){
                removeTasks(input);
            }else if (!input.trim().equalsIgnoreCase("list")) {
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
