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
        System.out.println(line);
        System.out.println(logo);
        System.out.println(line);
        System.out.println(hello);
        System.out.println(line);
        Scanner userName = new Scanner(System.in);
        String answerName = userName.nextLine();
        System.out.println(line);
        System.out.println("   Hi " + answerName + ", How can I help you today?");
        System.out.println(line);
        Scanner userAnswer = new Scanner(System.in);
        while (true) {
            boolean exitW = false;
            String answer2 = userAnswer.nextLine();
            System.out.println(line);
            switch (answer2) {
                case "list":
                    System.out.println("   list");
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
            }
            if (exitW) {
                break;
            }
        }

        System.out.println(line);
        System.out.println(crab);
    }
}
