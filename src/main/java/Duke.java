import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String userInput = "";
        boolean isBye = false;

        String logo = "████████ ██ ███    ██  █████  \n" +
                "   ██    ██ ████   ██ ██   ██ \n" +
                "   ██    ██ ██ ██  ██ ███████ \n" +
                "   ██    ██ ██  ██ ██ ██   ██ \n" +
                "   ██    ██ ██   ████ ██   ██";
        System.out.println(logo);
        System.out.println("Hello! I'm TINA. \nHow can I help you?\n");
        System.out.println("Type your question below. (Type \"bye\" to exit)");
        System.out.println("Function Menu:");
        System.out.println("1.bye: exit");
        System.out.println("2.list: list out all tasks");
        System.out.println("3.mark [task number]: mark task as done");
        System.out.println("4.unmark [task number]: unmark task as not done");
        System.out.println("**************************************************\n");

        while (!isBye) {
            userInput = sc.nextLine();
            // start line
            output("**************************************************");

            // format user input
            String[] userInputArr = userInput.split(" ");
            Task curTask;

            switch (userInputArr[0].toLowerCase()) {
                case "bye":
                    isBye = true;
                    output("Bye. Hope to see you again.");
                    break;
                case "list":
                    int count = 1;
                    for (Task task : taskList) {
                        output(count + ".[" + task.isDone() + "] " + task.getName());
                        count++;
                    }
                    break;
                case "mark" :
                    curTask = taskList.get(Integer.parseInt(userInputArr[1]) - 1);
                    curTask.setDone(true);
                    output("Nice! I've marked this task as done:");
                    output("[" + curTask.isDone() +"]" + curTask.getName());
                    break;
                case "unmark" :
                    curTask = taskList.get(Integer.parseInt(userInputArr[1]) - 1);
                    curTask.setDone(false);
                    output("OK, I've marked this task as not done yet:");
                    output("[" + curTask.isDone() +"]" + curTask.getName());
                    break;
                default:
                    taskList.add(new Task(userInput));
                    output("added: " + userInput);
            }

            // end line
            output("**************************************************\n");
        }

    }

    public static void output(String str) {
        System.out.print("  ");
        System.out.println(str);
    }
}



