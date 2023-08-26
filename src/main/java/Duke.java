import java.util.Arrays;
import java.util.Scanner;


public class Duke {

    public static void listTasks(String[] mylist){

        for (int i = 0; i < mylist.length; i ++){
            if(mylist[i] != null){
                System.out.println((i+1) + ") " + mylist[i]);
            } else {
                break;
            }
        }
    }

    public static void storeTasks(String[] mylist, String userInput) {

        for (int i = 0; i < mylist.length; i++){
            if (mylist[i] == null) {
                mylist[i] = userInput;
                break;
            }
        }

    }

    public static void main(String[] args) {
        String logo = "  _____ _           _        \n" +
                " / ____| |         | |       \n" +
                "| |    | |__   __ _| |_  \n" +
                "| |    | '_ \\ / _` | __/ \n" +
                "| |____| | | | (_| | |  \n" +
                " \\_____|_| |_|\\__,_|\\__\\\n";

        String horizontalLine = "_______________________________________________\n";
        String[] myList = new String[100];
        String acceptedCommands = "Accepted commands are:\n"
                + "\tlist = List all the tasks that are stored\n"
                + "\ttodo = Add a task\n"
                + "\tbye = Say goodbye to Chat";


        String userInput;
        Scanner in = new Scanner(System.in);

        System.out.println(logo + "Hello! My name is Chat.");
        System.out.print(horizontalLine);

        while (true){
            System.out.println(acceptedCommands +"\n");

            System.out.print("What do you wish to do? > ");
            userInput = in.nextLine();
            switch(userInput) {
                case "list":
                    System.out.println("Listing out all stored tasks");
                    listTasks(myList);
                    break;
                case "todo":
                    System.out.println("Please enter the task to be stored > ");
                    userInput = in.nextLine();
                    System.out.println("Got it. Storing " + userInput);
                    storeTasks(myList, userInput);
                    break;
                case "bye":
                    System.out.println("Goodbye. See you soon");
                    System.exit(0);
                    break;
            }
            System.out.print(horizontalLine);

        }
    }
}
