import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static final String logo = "   ____                  _      __   __\n" +
            "  / ___|  _ __    __ _  | |__   \\ \\ / /\n" +
            " | |     | '__|  / _` | | '_ \\   \\ V / \n" +
            " | |___  | |    | (_| | | |_) |   | |  \n" +
            "  \\____| |_|     \\__,_| |_.__/    |_|  \n" +
            "                                       \n";
    public static final String crab = "              __       __\n" +
            "             /           \\\n" +
            "            (  / @   @ \\  )\n" +
            "             \\(_ _\\_/_ _)/\n" +
            "           (\\ `-/     \\-' /)\n" +
            "            \"===\\     /===\"\n" +
            "             .==')___(`==.       \n" +
            "              .='     `=.";
    public static final String line = "  _____________________________________";
    public static final String hello = "   Hello! I'm CrabY 🦀 \n" + "   What is your name?";

    public static void main(String[] args) {
        System.out.println(line + "\n" + logo + "\n" + hello + "\n" + line);
        Scanner userName = new Scanner(System.in);
        String answerName = userName.nextLine();
        System.out.println(line + "\n" + "   Hi " + answerName + ", How can I help you today?" + "\n" + line);
        Scanner userAnswer = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();

        while (true) {
            boolean exitW = false;
            String answer2 = userAnswer.nextLine();
            String answer1 = answer2.split(" ")[0];
            answer1 = answer1.toLowerCase();
            System.out.println(line);
            switch (answer1) {
                case "list":
                    int i = 0;
                    System.out.println("   Here are the tasks in your list:");
                    for (Task s : tasks) {
                        i++;
                        System.out.println("   " + i + ". " + s);
                    }
                    System.out.println(line);
                    break;
                case "blah":
                    System.out.println("   blah \n" + line);
                    break;
                case "bye":
                    System.out.println("   Bye " + answerName + ", hope to see you again soon!");
                    exitW = true;
                    break;
                case "mark":
                    int a = (Integer.parseInt(answer2.split(" ")[1])) - 1;
                    if (a >= tasks.size() || a < 0){
                        System.out.println("   Opp, something wrong! Your list only have 1 to " + tasks.size() + " tasks. \n" + "   Please try again! \n" + line);
                        break;
                    }
                    tasks.get(a).setDone(true);
                    System.out.println("Nice! I've marked this task as done:" + "\n" + "   " + tasks.get(a) + "\n" + line);
                    break;
                case "unmark":
                    int b = (Integer.parseInt(answer2.split(" ")[1])) - 1;
                    if (b >= tasks.size() || b < 0){
                        System.out.println("   Opp, something wrong! Your list only have 1 to " + tasks.size() + "tasks. \n" + "   Please try again! \n" + line);
                        break;
                    }
                    tasks.get(b).setDone(false);
                    System.out.println("   OK, I've marked this task as not done yet:" + "\n" + "   " + tasks.get(b) + "\n" + line);
                    break;
                default:
                    tasks.add(new Task(answer2));
                    System.out.println("   added: " + answer2 + " - to your list. \n" + line);
            }
            if (exitW) {
                break;
            }
        }
        System.out.println(line + "\n" + crab);
    }
}
