import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
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
                    curTask = taskList.get(Integer.parseInt(tokens[1]) - 1);
                    curTask.setDone(true);
                    outputln("Nice! I've marked this task as done:");
                    outputln("[" + curTask.isDone() +"]" + curTask.getName());
                    break;
                case "unmark" :
                    curTask = taskList.get(Integer.parseInt(tokens[1]) - 1);
                    curTask.setDone(false);
                    outputln("OK, I've marked this task as not done yet:");
                    outputln("[" + curTask.isDone() +"]" + curTask.getName());
                    break;
                case "todo":
                    taskName = userInput.substring(5);
                    newTask = new Todo(taskName);
                    taskList.add(newTask);
                    outputln(newTask);
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
                    outputln(userInput);
            }

            // end line
            outputln("**************************************************\n");
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



