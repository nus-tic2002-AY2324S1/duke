import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) throws DukeException {
        welcome();

        Scanner sc = new Scanner(System.in);
        String userInput = "";
        boolean isBye = false;

        while (!isBye) {
            userInput = sc.nextLine();
            // start line
            outputln("**************************************************");

            // format user input
            String[] tokens = userInput.split(" ");
            Task curTask;
            String taskName;
            Task newTask;

            switch (tokens[0].toLowerCase()) {
                case "bye":
                    isBye = true;
                    outputln("Bye. Hope to see you again.");
                    break;
                case "list":
                    int count = 1;
                    for (Task task : taskList) {
                        outputln(count + "." + task.toString());
                        count++;
                    }
                    break;
                case "mark" :
                    try {
                        curTask = getCurrentTask(tokens);
                        curTask.setDone(true);
                        outputln("Nice! I've marked this task as done:");
                        outputln("[" + curTask.isDone() +"]" + curTask.getName());
                    } catch (Exception e) {
                        outputln(e.getMessage());
                    }
                    break;
                case "unmark" :
                    try {
                        curTask = getCurrentTask(tokens);
                        curTask.setDone(false);
                        outputln("OK, I've marked this task as not done yet:");
                        outputln("[" + curTask.isDone() +"]" + curTask.getName());
                    } catch (DukeException e) {
                        outputln(e.getMessage());
                    } catch (Exception e) {
                        outputln("OPPS!!! Please use correct syntax for unmarking: unmark [task number]");
                    }
                    break;
                case "todo":
                    try {
                        taskName = userInput.substring(5);
                        if (taskName.isEmpty()) {
                            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                        }
                        newTask = new Todo(taskName);
                        taskList.add(newTask);
                        outputln(newTask);
                    } catch (DukeException e) {
                        outputln(e.getMessage());
                    }
                    break;
                case "deadline":
                    taskName = userInput.substring(9, userInput.indexOf("/by") - 1);
                    String by = userInput.substring(userInput.indexOf("/by") + 4);
                    newTask = new Deadline(taskName, by);
                    taskList.add(newTask);
                    outputln(newTask);
                    break;
                case "event":
                    taskName = userInput.substring(6, userInput.indexOf("/from") - 1);
                    String from = userInput.substring(userInput.indexOf("/from") + 6, userInput.indexOf("/to"));
                    String to = userInput.substring(userInput.indexOf("/to") + 4);
                    newTask = new Event(taskName, from, to);
                    taskList.add(newTask);
                    outputln(newTask);
                    break;
                default:
                    outputln("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

            // end line
            outputln("**************************************************\n");
        }

    }

    public static Task getCurrentTask(String[] tokens) throws DukeException {
        int taskNum;
        String function = tokens[0];
        try {
            taskNum = Integer.parseInt(tokens[1]);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            String message = "OPPS!!! Please use correct syntax for " + function + "ing: " + function + " [task number]";
            throw new DukeException(message);
        }
        try {
            return taskList.get(taskNum - 1);
        } catch (IndexOutOfBoundsException  e) {
            throw new DukeException("OPPS!!! Cannot " + function + " an empty/invalid task");
        }
    }

    public static void outputln(Task newTask) {
        outputln("Got it. I've added this task:");
        outputln("  " + newTask.toString());
        outputln("Now you have " + taskList.size() + " tasks in the list.");
    }

    public static void outputln(String str) {
        System.out.print("  ");
        System.out.println(str);
    }

    public static void welcome() {
        String logo =   "████████ ██ ███    ██  █████  \n" +
                "   ██    ██ ████   ██ ██   ██ \n" +
                "   ██    ██ ██ ██  ██ ███████ \n" +
                "   ██    ██ ██  ██ ██ ██   ██ \n" +
                "   ██    ██ ██   ████ ██   ██";

        System.out.println(logo);
        System.out.println("Hello! I'm TINA. \nHow can I help you?\n");
        //System.out.println("Type your question below. (Type \"bye\" to exit)");
        System.out.println("Function Menu:");
        System.out.println("1.bye: exit");
        System.out.println("2.list: list out all tasks");
        System.out.println("3.mark [task number]: mark task as done");
        System.out.println("4.unmark [task number]: unmark task as not done");
        System.out.println("**************************************************\n");
    }
}



