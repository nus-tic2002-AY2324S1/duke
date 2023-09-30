import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Task[] taskList = new Task[100];
        int index = 0;

        printMessageWithBorder("Hello! I'm Bott!\n\tWhat can I do for you?");

        while(true) {
            String input = in.nextLine();
            String[] stringArray = input.split(" ");
            String command = stringArray[0];

            switch (command) {
                case "hello" : {
                    printMessageWithBorder("Hello! Nice to meet you.");
                    break;
                }
                case "bye" : {
                    printMessageWithBorder("Bye. Hope to see you again soon!");
                    return;
                }
                case "list" : {
                    printTaskList(taskList);
                    break;
                }
                case "mark" : {
                    // insert something
                    int taskIndex = Integer.parseInt(stringArray[1])-1;

                    taskList[taskIndex].isDone = true;
                    printMessageWithBorder("Nice! I've marked this task as done:\n\t"
                            + taskList[taskIndex].printItemWithStatus());
                    break;
                }
                case "unmark" : {
                    // insert something
                    int taskIndex = Integer.parseInt(stringArray[1])-1;

                    taskList[taskIndex].isDone = false;
                    printMessageWithBorder("OK! I've marked this task as not done yet:\n\t"
                            + taskList[taskIndex].printItemWithStatus());
                    break;
                }
                case "todo" : {
                    String description = createDescription(input, "todo");

                    taskList[index] = new Todo(description);
                    printEchoMessage(taskList[index].printItemWithStatus(), (index+1));
                    index++;
                    break;
                }
                case "deadline" : {
                    String description = createDescription(input, "deadline");
                    String by = createByDate(input);

                    taskList[index] = new Deadline(description, by);
                    printEchoMessage(taskList[index].printItemWithStatus(), (index+1));
                    index++;
                    break;
                }
                case "event" : {
                    String description = createDescription(input, "event");
                    String from = createFromDate(input);
                    String to = createToDate(input);

                    taskList[index] = new Event(description, from, to);
                    printEchoMessage(taskList[index].printItemWithStatus(), (index+1));
                    index++;
                    break;
                }
                default : {
                    printMessageWithBorder("I don't quite understand that. Please try again.");
                }
            }
        }
    }

    public static void printMessageWithBorder(String message) {
        System.out.println("\t─────────────────────────────────────────────");
        System.out.println("\t" + message);
        System.out.println("\t─────────────────────────────────────────────");
    }

    public static void printEchoMessage(String message, int index) {
        printMessageWithBorder("Got it! I've added this task:\n\t"
                + message + "\n\t"
                + String.format("Now you have %d task in the list", index));
    }

    public static void printTaskList(Task[] taskList) {
        System.out.println("\t─────────────────────────────────────────────"
                + "\n\tHere are the tasks in your list:");
        for (int i = 0; taskList[i] != null; i++) {
            System.out.println("\t" + (i+1) + ". " + taskList[i].printItemWithStatus());
        }
        System.out.println("\t─────────────────────────────────────────────");
    }

    public static String createDescription(String input, String command) {
        int beginningIndex = 0;
        int endingIndex = 0;

        switch (command) {
            case "todo": {
                beginningIndex = 5;
                endingIndex = input.length();
                break;
            }
            case "deadline": {
                beginningIndex = 9;
                endingIndex = input.indexOf(" /by");
                break;
            }
            case "event": {
                beginningIndex = 6;
                endingIndex = input.indexOf(" /from");
                break;
            }
        }
        return input.substring(beginningIndex, endingIndex);
    }

    public static String createByDate(String input) {
        int beginningIndex = input.indexOf("/by") + 4;
        int endingIndex = input.length();

        return input.substring(beginningIndex, endingIndex);
    }

    public static String createFromDate(String input) {
        int beginningIndex = input.indexOf("/from") + 6;
        int endingIndex = input.indexOf(" /to");

        return input.substring(beginningIndex, endingIndex);
    }

    public static String createToDate(String input) {
        int beginningIndex = input.indexOf("/to") + 4;
        int endingIndex = input.length();

        return input.substring(beginningIndex, endingIndex);
    }
}
