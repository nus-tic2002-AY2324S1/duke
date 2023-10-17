import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>(100);
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
                    if (taskList.isEmpty()) {
                        printMessageWithBorder("Your task list is empty.");
                    } else {
                        printTaskList(taskList);
                    }
                    break;
                }
                case "mark" : {
                    int taskIndex;

                    // condition for "mark" command without any number
                    try {
                        taskIndex = Integer.parseInt(stringArray[1]) - 1;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        printMessageWithBorder("Please include the task number that you'd like to mark as done.");
                        break;
                    } catch (NumberFormatException e) {
                        printMessageWithBorder("Please only input a number.");
                        break;
                    }

                    // condition for "mark" command with out-of-bounds number
                    try {
                        taskList.get(taskIndex).isDone = true;
                    } catch (IndexOutOfBoundsException e) {
                        printMessageWithBorder("Please enter a valid number, i.e. within the list.");
                        break;
                    }

                    printMessageWithBorder("Nice! I've marked this task as done:\n\t"
                            + taskList.get(taskIndex).printItemWithStatus());
                    break;
                }
                case "unmark" : {
                    int taskIndex;

                    // condition for "unmark" command without any number
                    try {
                        taskIndex = Integer.parseInt(stringArray[1]) - 1;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        printMessageWithBorder("Please include the task number that you'd like to mark as not done.");
                        break;
                    } catch (NumberFormatException e) {
                        printMessageWithBorder("Please only input a number.");
                        break;
                    }

                    // condition for "mark" command with out-of-bounds number
                    try {
                        taskList.get(taskIndex).isDone = false;
                    } catch (IndexOutOfBoundsException e) {
                        printMessageWithBorder("Please enter a valid number, i.e. within the list.");
                        break;
                    }

                    printMessageWithBorder("OK! I've marked this task as not done yet:\n\t"
                            + taskList.get(taskIndex).printItemWithStatus());
                    break;
                }
                case "todo" : {
                    String description;

                    try {
                        description = createDescription(input, "todo");
                    } catch (StringIndexOutOfBoundsException e) {
                        printMessageWithBorder("Please follow this format:\n\t" +
                                "\"todo\" \"your description\"");
                        break;
                    }

                    taskList.add(index, new Todo(description));
                    printEchoMessage(taskList.get(index).printItemWithStatus(), (index+1));
                    index++;
                    break;
                }
                case "deadline" : {
                    String by;
                    String description;

                    try {
                        description = createDescription(input, "deadline");
                        by = createByDate(input);
                    } catch (StringIndexOutOfBoundsException e) {
                        printMessageWithBorder("Please follow this format:\n\t" +
                                "\"deadline\" \"your description\" \"/by\" \"date/time\"");
                        break;
                    }

                    taskList.add(index, new Deadline(description, by));
                    printEchoMessage(taskList.get(index).printItemWithStatus(), (index + 1));
                    index++;
                    break;
                }
                case "event" : {
                    String description;
                    String from;
                    String to;

                    try {
                        description = createDescription(input, "event");
                        from = createFromDate(input);
                        to = createToDate(input);
                    } catch (StringIndexOutOfBoundsException e) {
                        printMessageWithBorder("Please follow this format:\n\t" +
                                "\"event\" \"your description\" \"/from\" \"date/time\" \"/to\" \"date/time\"");
                        break;
                    }

                    taskList.add(index, new Event(description, from, to));
                    printEchoMessage(taskList.get(index).printItemWithStatus(), (index+1));
                    index++;
                    break;
                }
                case "delete" : {
                    int taskIndex;

                    try {
                        taskIndex = Integer.parseInt(stringArray[1]) - 1;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        printMessageWithBorder("Please include the task number that you'd like to mark as done.");
                        break;
                    } catch (NumberFormatException e) {
                        printMessageWithBorder("Please only input a number.");
                        break;
                    }

                    try {
                        printMessageWithBorder("Noted! I will remove this task:\n\t"
                                + taskList.get(taskIndex).printItemWithStatus()
                                + String.format("\n\tNow you have %d task in the list", index-1));
                        taskList.remove(taskIndex);
                    } catch (IndexOutOfBoundsException e) {
                        printMessageWithBorder("Please enter a valid number, i.e. within the list.");
                        break;
                    }
                    
                    index--;
                    break;
                }
                default : {
                    printMessageWithBorder("I don't quite understand that. Please try again.");
                }
            }
        }
    }

    public static void printMessageWithBorder(String message) {
        System.out.println("\t─────────────────────────────────────────────────────────────────");
        System.out.println("\t" + message);
        System.out.println("\t─────────────────────────────────────────────────────────────────");
    }

    public static void printEchoMessage(String message, int index) {
        printMessageWithBorder("Got it! I've added this task:\n\t"
                + message + "\n\t"
                + String.format("Now you have %d task in the list", index));
    }

    public static void printTaskList(ArrayList<Task> taskList) {
        System.out.println("\t─────────────────────────────────────────────────────────────────"
                + "\n\tHere are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("\t" + (i+1) + ". " + taskList.get(i).printItemWithStatus());
        }
        System.out.println("\t─────────────────────────────────────────────────────────────────");
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