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
            ArrayList<String> funcStrList = null;
            try {
                UserInput input = new UserInput(tokens);
                funcStrList = input.handleTokens();
            } catch (DukeException e) {
                outputln(e.getMessage());
            }

            if (funcStrList != null) {
                Task curTask;
                String taskName;
                Task newTask;

                switch (funcStrList.get(0)) {
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
                            curTask = getCurrentTask(funcStrList.get(1));
                            curTask.setDone(true);
                            outputln("Nice! I've marked this task as done:");
                            outputln("[" + curTask.isDone() +"]" + curTask.getName());
                        } catch (DukeException e) {
                            outputln(e.getMessage());
                        }
                        break;
                    case "unmark" :
                        try {
                            curTask = getCurrentTask(funcStrList.get(1));
                            curTask.setDone(false);
                            outputln("OK, I've marked this task as not done yet:");
                            outputln("[" + curTask.isDone() +"]" + curTask.getName());
                        } catch (DukeException e) {
                            outputln(e.getMessage());
                        }
                        break;
                    case "todo":
                        newTask = new Todo(funcStrList.get(1));
                        taskList.add(newTask);
                        outputln(newTask);
                        break;
                    case "deadline":
                        newTask = new Deadline(funcStrList.get(1), funcStrList.get(2));
                        taskList.add(newTask);
                        outputln(newTask);
                        break;
                    case "event":
                        newTask = new Event(funcStrList.get(1), funcStrList.get(2), funcStrList.get(3));
                        taskList.add(newTask);
                        outputln(newTask);
                        break;
                    default:
                        outputln("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }

            // end line
            outputln("**************************************************\n");
        }

    }

    public static Task getCurrentTask(String taskNum) throws DukeException {
        try {
            return taskList.get(Integer.parseInt(taskNum) - 1);
        } catch (IndexOutOfBoundsException  e) {
            throw new DukeException("OOPS!!! It is an invalid task");
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



