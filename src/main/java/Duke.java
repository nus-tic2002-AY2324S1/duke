import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Objects;
import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        welcomeMessage();

        while(true) {
            String input = in.nextLine();
            if (Objects.equals(input, "bye")) {
                byeMessage();
                break;
            } else {
                echoMessage(input);
            }
        }
    }

    public static void welcomeMessage() {
        System.out.println("\t────────────────────────────────────────");
        System.out.println("\tHello! I'm Botzy!\n\tWhat can I do for you?");
        System.out.println("\t────────────────────────────────────────");
    }

    public static void byeMessage() {
        System.out.println("\t────────────────────────────────────────");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t────────────────────────────────────────");
    }

    public static void echoMessage(String args) {
        System.out.println("\t────────────────────────────────────────");
        System.out.println("\t" + args);
        System.out.println("\t────────────────────────────────────────");
    }
}
