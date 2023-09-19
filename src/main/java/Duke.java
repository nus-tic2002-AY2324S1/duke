import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "████████ ██ ███    ██  █████  \n" +
                "   ██    ██ ████   ██ ██   ██ \n" +
                "   ██    ██ ██ ██  ██ ███████ \n" +
                "   ██    ██ ██  ██ ██ ██   ██ \n" +
                "   ██    ██ ██   ████ ██   ██";
        System.out.println(logo);
        System.out.println("Hello! I'm TINA. \nHow can I help you?\n");
        System.out.println("Type your question below. (Type \"bye\" to exit)");
        System.out.println("**************************************************\n");

        Scanner sc = new Scanner(System.in);
        String userInput = "";

        while (true) {
            userInput = sc.nextLine();
            System.out.println("    **************************************************");
            if (userInput.equalsIgnoreCase("bye")) {
                break;
            }
            System.out.println("    " + userInput);
            System.out.println("    **************************************************\n");
        }

        System.out.println("    Bye. Hope to see you again.\n");
    }
}



