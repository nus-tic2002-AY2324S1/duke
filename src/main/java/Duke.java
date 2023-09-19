import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static ArrayList<String> arrList = new ArrayList<>();

    public static void main(String[] args) {
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
        System.out.println("2.list: list out all inputs");
        System.out.println("**************************************************\n");

        Scanner sc = new Scanner(System.in);
        String userInput = "";
        boolean isBye = false;

        while (!isBye) {
            userInput = sc.nextLine();
            System.out.println("    **************************************************");

            switch (userInput.toLowerCase()) {
                case "bye":
                    isBye = true;
                    System.out.println("    Bye. Hope to see you again.");
                    break;
                case "list":
                    int count = 1;
                    for (String str : arrList) {
                        System.out.println("    " + count + ". " + str);
                        count++;
                    }
                    break;
                default:
                    arrList.add(userInput);
                    System.out.println("    added: " + userInput);
            }

            // end line
            System.out.println("    **************************************************\n");
        }

    }
}



