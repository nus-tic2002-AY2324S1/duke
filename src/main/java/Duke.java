import java.util.Scanner;
import java.util.ArrayList;


public class Duke {

    static String name = "Tweety";
    static String indent = "    ";

    private static void printLine() {
        System.out.println(indent + "____________________________________________________________");
    }

    private static void print(String msg) {
        printLine();
        String[] lines = msg.split("\n");
        for (String line: lines) {
            System.out.println(indent + line);
        }
        printLine();
    }

    private static void printList(ArrayList<String> list) {

        printLine();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(indent + (i+1) + ". " + list.get(i));
        }

        printLine();
    }

    public static void main(String[] args) {

        String greeting = "Hello I'm " + name + "\n" + "What can I do for you?";
        String goodbye = "Bye. Hope to see you again soon!";

        ArrayList<String> msges = new ArrayList<>();
        print(greeting);

        Scanner in = new Scanner(System.in);
        String msg ="";

        while (!msg.equals("bye")) {

            msg = in.nextLine();
            if (msg.equals("list")) {
                printList(msges);
            } else {
                msges.add(msg);
                print("added: " + msg);
            }
        }

        print(goodbye);
    }
}
