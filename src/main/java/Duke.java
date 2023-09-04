import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        */

        Scanner in = new Scanner(System.in);
        ArrayList<String> addedInputs = new ArrayList<>();

        System.out.println("____________________________________________________________");
        System.out.println(
                " Hello! I'm DukeBot\n" +
                " What can I do for you?");
        System.out.println("____________________________________________________________");

        do {
            String input = in.nextLine();
            if (input.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (input.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < addedInputs.size(); i++) {
                    System.out.printf(" %d. %s%n", i + 1, addedInputs.get(i));
                }
                System.out.println("____________________________________________________________");
                continue;
            }

            addedInputs.add(input);
            System.out.println("____________________________________________________________");
            System.out.println(" added: " + input);
            System.out.println("____________________________________________________________");
        } while(true);
    }
}
