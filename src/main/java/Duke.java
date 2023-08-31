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
    public static void addList(List<String> listTodo, String answer) {
        listTodo.add(answer);
    }

    public static void main(String[] args) {
        System.out.println(line);
        System.out.println(logo);
        System.out.println(hello);
        System.out.println(line);
        Scanner userName = new Scanner(System.in);
        String answerName = userName.nextLine();
        System.out.println(line);
        System.out.println("   Hi " + answerName + ", How can I help you today?");
        System.out.println(line);
        Scanner userAnswer = new Scanner(System.in);
        List<String> list = new ArrayList<>();
        while (true) {
            boolean exitW = false;
            String answer2 = userAnswer.nextLine();
            String answer1 = answer2.toLowerCase();
            System.out.println(line);

            switch (answer1) {
                case "list":
                    int i = 0;
                    System.out.println("   Here are the tasks in your list:");
                    for (String s : list) {
                        i++;
                        System.out.println("   " + i + ". " + s);
                    }
                    System.out.println(line);
                    break;
                case "blah":
                    System.out.println("   blah");
                    System.out.println(line);
                    break;
                case "bye":
                    System.out.println("   Bye " + answerName + ", hope to see you again soon!");
                    exitW = true;
                    break;
                default:
                    addList(list, answer2);
                    System.out.println("   added: " + answer2 + " - to your list");
                    System.out.println(line);
            }
            if (exitW) {
                break;
            }
        }
        System.out.println(line);
        System.out.println(crab);
    }
}
