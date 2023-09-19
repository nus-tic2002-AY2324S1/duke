import java.util.Arrays;
import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Task[] taskList = new Task[100];
        int index = 0;

        printWelcomeMessage();

        while(true) {
            String input = in.nextLine();
            String[] stringArray = input.split(" ");
            String command = stringArray[0];
//            System.out.println(Arrays.toString(stringArray));

            switch (command) {
                case "bye" : {
                    printByeMessage();
                    return;
                }
                case "list" : {
                    printTaskList(taskList);
                    break;
                }
                case "mark" : {
                    checkMarkUnmark(taskList, input);
                    break;
                }
                case "unmark" : {
                    checkMarkUnmark(taskList, input);
                    break;
//                    // check if any int is supplied with the command "mark"
//                    if (stringArray.length == 1) {
//                        printMessageWithBorder("Please indicate which task you want to unmark");
//                        break;
//                    }
//
//                    int taskNumber = Integer.parseInt(stringArray[1]) - 1;
//
//                    // check if the list is empty
//                    if (taskList[0] == null) {
//                        printMessageWithBorder("The list is empty, there is nothing to unmark");
//                        break;
//                    }
//                    // check if the given int is within bounds (0-100)
//                    else if (taskNumber < 0 || taskNumber > 100) {
//                        printMessageWithBorder("Please enter a valid number");
//                        break;
//                    }
//                    //check if the task is already marked
//                    else if (!taskList[taskNumber].getStatus()) {
//                        printMessageWithBorder("This task is already marked as not done");
//                        break;
//                    }
//
//                    // mark the task
//                    taskList[taskNumber].setDone(false);
//                    String message = "OK, I've marked this task as not done yet:" + System.lineSeparator() +
//                            "\t" + taskList[taskNumber].getStatusIcon() + taskList[taskNumber].description;
//                    printMessageWithBorder(message);
                }
                default : {
                    taskList[index] = new Task(input);
                    index++;
                    printEchoMessage(input);
                }
            }
        }
    }

    public static void printMessageWithBorder(String message) {
        System.out.println("\t─────────────────────────────────────────────");
        System.out.println("\t" + message);
        System.out.println("\t─────────────────────────────────────────────");
    }

    public static void printWelcomeMessage() {
        printMessageWithBorder("Hello! I'm Bott!\n\tWhat can I do for you?");
    }

    public static void printByeMessage() {
        printMessageWithBorder("Bye. Hope to see you again soon!");
    }

    public static void printEchoMessage(String input) {
        printMessageWithBorder("added: " + input);
    }

    public static void printTaskList(Task[] taskList) {
        System.out.println("\t────────────────────────────────────────"
                            + "\n\tHere are the tasks in your list:");
        for (int i = 0; taskList[i] != null; i++) {
            System.out.println("\t" + (i+1) + ". " + taskList[i].getStatusIcon() + taskList[i].description);
        }
        System.out.println("\t────────────────────────────────────────");
    }

    public static void checkMarkUnmark(Task[] taskList, String input) {
        String[] stringArray = input.split(" ");
        String check = (stringArray[0].equals("mark") ? "mark" : "unmark");

        // check if any int is supplied with the command "mark"
        if (stringArray.length == 1) {
            printMessageWithBorder("Please indicate which task you want to " + check);
            return;
        } else {
            int taskNumber = Integer.parseInt(stringArray[1]) - 1;
//            System.out.println(Arrays.toString(stringArray));
//            System.out.println(taskNumber + " " + taskList[taskNumber].getStatus());

            // check if the list is empty
            if (taskList[0] == null) {
                printMessageWithBorder("The list is empty, there is nothing to " + check);
                return;
            }
            // check if the given int is within bounds (0-100)
            else if (taskNumber < 0 || taskNumber > 100) {
                printMessageWithBorder("Please enter a valid number");
                return;
            }
            //check if the task is already marked
//            else if (taskList[taskNumber].getStatus()) {
//                String message;
//                if (check.equals("mark")) {
//                    message = "This task is already marked as done";
//                } else {
//                    message = "This task is already marked as not done";
//                }
//                printMessageWithBorder(message);
//                return;
//            }
            else {
                // mark/unmark the task
                if (check.equals("mark")) {
                    if (taskList[taskNumber].getStatus()) {
                        printMessageWithBorder("This task is already marked as done");
                    } else {
                        taskList[taskNumber].setDone(true);
                        String message = "Nice! I've marked this task as done:" + System.lineSeparator() +
                                "\t" + taskList[taskNumber].getStatusIcon() + taskList[taskNumber].description;
                        printMessageWithBorder(message);
                    }
                } else {
                    if (!taskList[taskNumber].getStatus()) {
                        printMessageWithBorder("This task is already marked as not done");
                    } else {
                        taskList[taskNumber].setDone(false);
                        String message = "OK, I've marked this task as not done yet:" + System.lineSeparator() +
                                "\t" + taskList[taskNumber].getStatusIcon() + taskList[taskNumber].description;
                        printMessageWithBorder(message);
                    }
                }
            }
        }
    }
}
