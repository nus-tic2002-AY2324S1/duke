import java.util.Scanner;


public class Duke {

    static String name = "Tweety";
    static String indent = "    ";

    private static void print(String msg) {
        System.out.println(indent + "____________________________________________________________");

        String[] lines = msg.split("\n");
        for (String line: lines) {
            System.out.println(indent + line);
        }

        System.out.println(indent + "____________________________________________________________");
    }

    public static void main(String[] args) {

        String greeting = "Hello I'm " + name + "\n" + "What can I do for you?";
        String goodbye = "Bye. Hope to see you again soon!";
        print(greeting);

        Scanner in = new Scanner(System.in);
        String msg ="";

        while (!msg.equals("bye")) {

            msg = in.nextLine();
            print(msg);
        }

        print(goodbye);
    }
}
