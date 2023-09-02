import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    public static void listTasks(Task[] mylist){

        for (int i = 0; i < mylist.length; i ++){
            if(mylist[i] != null){
                System.out.println((i+1) + ") " + mylist[i]);
            } else {
                break;
            }
        }
    }

    public static void storeTasks(Task[] mylist, String userInput) {

        for (int i = 0; i < mylist.length; i++){
            if (mylist[i] == null) {
                mylist[i] = new Task(userInput);
                break;
            }
        }

    }

    public static boolean checkTaskRange(Task[] mylist, String taskString){

        int taskIndex = Integer.parseInt(taskString) - 1;
        if (taskIndex >= 0 && taskIndex < mylist.length && mylist[taskIndex] != null){
            return true;
        } else {
            return false;
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
        Task[] myList = new Task[100];
        String acceptedCommands = "Accepted commands are:\n"
                + "\tlist = List all the tasks that are stored\n"
                + "\ttodo <Task to be added in> = Adds a task to the list\n"
                + "\tmark <Task number to be marked> = marks the task as completed\n"
                + "\tunmark <Task number to be unmarked> = mark the task as not completed\n"
                + "\tbye = Say goodbye to Chat";


        String userInput;
        Scanner in = new Scanner(System.in);

        System.out.println(logo + "Hello! My name is Chat.");
        System.out.print(horizontalLine);

        while (true){
            System.out.println(acceptedCommands +"\n");

            System.out.print("What do you wish to do? > ");
            if (in.hasNextLine()){
                userInput = in.nextLine();
                switch(userInput) {
                    case "list":
                        System.out.println("Listing out all stored tasks");
                        listTasks(myList);
                        break;
                    case "bye":
                        System.out.println("Goodbye. See you soon");
                        System.exit(0);
                        break;
                    default:
                        String[] wordArray = userInput.split(" ");
                        String secondWord = String.join(" ", Arrays.copyOfRange(wordArray, 1, wordArray.length));

                        if (!secondWord.trim().isEmpty()){
                            switch (wordArray[0]){
                                case "todo":
                                    System.out.println("Got it. Storing " + secondWord);
                                    storeTasks(myList, secondWord);
                                    listTasks(myList);
                                    break;
                                case "mark":
                                    if (checkTaskRange(myList, secondWord) == true) {
                                        System.out.println("Marked task " + secondWord + " as done!");
                                        myList[(Integer.parseInt(secondWord)-1)].markDone();
                                        listTasks(myList);
                                    } else {
                                        System.out.println("[-] Task index is wrong.");
                                        listTasks(myList);
                                    }

                                    break;
                                case "unmark":
                                    if (checkTaskRange(myList, secondWord) == true) {
                                        System.out.println("Marked task " + secondWord + " as done!");
                                        myList[(Integer.parseInt(secondWord)-1)].unmarkDone();
                                        listTasks(myList);
                                    } else {
                                        System.out.println("[-] Task index is wrong.");
                                        listTasks(myList);
                                    }

                                    break;
                                default:
                                    System.out.println("[-] Wrong command. Please refer to the help guide.");
                                    break;
                            }
                        } else {
                            System.out.println("[-] Wrong command format!");
                        }

                        break;

                }

            } else{
                System.exit(0);
            }

            System.out.print(horizontalLine);
        }
    }
}
