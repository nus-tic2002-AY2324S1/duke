import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Objects;
import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] itemList = new String[100];
        int index = 0;

        welcomeMessage();

        while(true) {
            String input = in.nextLine();
            if (Objects.equals(input, "bye")) {
                byeMessage();
                break;
            } else if (Objects.equals(input, "list")) {
                printItemList(itemList);
            } else {
                itemList[index] = input;
                index++;
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
        System.out.println("\tadded: " + args);
        System.out.println("\t────────────────────────────────────────");
    }

    public static void printItemList(String[] itemList) {
        System.out.println("\t────────────────────────────────────────");
        for (int i = 0; itemList[i] != null; i++) {
            System.out.println("\t" + (i+1) + ". " + itemList[i]);
        }
        System.out.println("\t────────────────────────────────────────");
    }
}
