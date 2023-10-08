import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;


public class Duke {

    public static void listTasks(ArrayList<Task> mylist){
        for (int i = 0; i< mylist.size(); i++){
            System.out.println("(" + (i + 1) + ") " + mylist.get(i));
        }

    }

    public static void deleteTask(ArrayList<Task> myList, int indexToDelete){
        myList.remove(indexToDelete);
    }

    //Todo
    public static void storeTasks(ArrayList<Task> mylist, String userInput) {

        mylist.add(new Task(userInput));

    }

    // Deadline
    public static void storeTasks(ArrayList<Task> mylist, String userInput, String doneBefore){

        mylist.add(new Deadline(userInput, doneBefore));

    }

    //Event
    public static void storeTasks(ArrayList<Task> mylist, String userInput, String doneFrom, String doneTo){


        mylist.add(new Event(userInput, doneFrom, doneTo));

    }

    public static boolean checkTaskRange(ArrayList<Task> mylist, String taskString){

        int taskIndex = Integer.parseInt(taskString) - 1;
        if (taskIndex >= 0 && taskIndex < mylist.size()){
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
        ArrayList<Task> myList = new ArrayList<>();
        //Task[] myList = new Task[100];
        String acceptedCommands = "Accepted commands are:\n"
                + "\tlist = List all the tasks that are stored\n"
                + "\ttodo <Task to be added in> = Adds a task to the list\n"
                + "\tdeadline <Task to be added in> /by <Deadline> = Adds a deadline task to the list\n"
                + "\tevent <Task to be added in> /from <Start date or time> /to <End date or time> = Adds a deadline task to the list\n"
                + "\tmark <Task number to be marked> = marks the task as completed\n"
                + "\tunmark <Task number to be unmarked> = mark the task as not completed\n"
                + "\tdelete <Task number to be deleted> = deletes the task\n"
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

                        String[] inputParts = secondWord.split("/");
                            switch (wordArray[0]){
                                case "todo":
                                    try {
                                        if (wordArray.length < 2) {
                                            throw new InvalidCommandException("[-] Missing task description - todo <Task to be added in>");
                                        } else {
                                            secondWord = String.join(" ", Arrays.copyOfRange(wordArray, 1, wordArray.length));
                                            System.out.println("[+] Got it. Storing - " + secondWord + " as a todo.");
                                            storeTasks(myList, secondWord);
                                            listTasks(myList);
                                        }
                                    } catch (InvalidCommandException e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;

                                case "deadline":
                                    try {
                                        if (inputParts.length == 2) {
                                            System.out.println("[+] Got it. Storing - " + inputParts[0].trim() + " as a deadline.");
                                            storeTasks(myList, inputParts[0].trim(), inputParts[1].trim());
                                        } else {
                                            throw new InvalidCommandException("[-] Missing Task description - deadline <Task to be added in> /by <Deadline>");
                                        }
                                        listTasks(myList);
                                        break;
                                    } catch (InvalidCommandException e) {
                                        System.out.println("" + e.getMessage());
                                    }
                                    break;

                                case "event":
                                    try {
                                        if (inputParts.length == 3) {
                                            System.out.println("[+] Got it. Storing - " + inputParts[0].trim() + " as an event.");
                                            storeTasks(myList, inputParts[0].trim(), inputParts[1].trim(), inputParts[2].trim());
                                        } else {
                                            throw new InvalidCommandException("[-] Missing Task description - event <Task to be added in> /from <Start date or time> /to <End date or time>");
                                        }
                                        listTasks(myList);
                                        break;
                                    } catch (InvalidCommandException e) {
                                        System.out.println("" + e.getMessage());
                                    }
                                    break;
                                case "mark":
                                    try {
                                        if (checkTaskRange(myList, secondWord) == true) {
                                            System.out.println("Marked task " + secondWord + " as done!");
                                            myList.get(Integer.parseInt(secondWord) - 1).markDone();


                                        } else {
                                            throw new InvalidRangeException("Task index is wrong");
                                        }
                                        listTasks(myList);
                                        break;
                                    } catch (InvalidRangeException e) {
                                        System.out.println("[-] Error: Task index is wrong");
                                        break;
                                    } catch (NumberFormatException e) {
                                        System.out.println("[-] Error: Not a number");
                                        break;
                                    }
                                case "unmark":
                                    try{
                                        if (checkTaskRange(myList, secondWord) == true) {
                                            System.out.println("Unmarked task " + secondWord + " !");
                                            myList.get(Integer.parseInt(secondWord) - 1).unmarkDone();
                                        } else {
                                            throw new InvalidRangeException("Task index is wrong");
                                        }
                                        listTasks(myList);
                                        break;
                                    } catch (InvalidRangeException e) {
                                        System.out.println("[-] Error: Task index is wrong");
                                        break;
                                    } catch (NumberFormatException e) {
                                        System.out.println("[-] Error: Not a number");
                                        break;
                                    }
                                case "delete":
                                    try{
                                        if (checkTaskRange(myList, secondWord) == true) {
                                            System.out.println("Deleted task " + secondWord + " !");
                                            deleteTask(myList,Integer.parseInt(secondWord) - 1);
                                        } else {
                                            throw new InvalidRangeException("Task index is wrong");
                                        }
                                        listTasks(myList);
                                        break;
                                    } catch (InvalidRangeException e) {
                                        System.out.println("[-] Error: " + e.getMessage());
                                        break;
                                    } catch (NumberFormatException e) {
                                        System.out.println("[-] Error: " + e.getMessage());
                                        break;
                                    }

                                default:
                                    try {
                                        throw new InvalidCommandException("I do not recognize this command - " + userInput);
                                    } catch (InvalidCommandException e) {
                                            System.out.println("[-] Error: " + e.getMessage());
                                    }
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
