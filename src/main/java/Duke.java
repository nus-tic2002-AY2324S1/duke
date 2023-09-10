import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\n"+
                "Hello! I'm venni\n" +
                "What can I do for you?\n"
        );

        String input = scanner.nextLine();
        while(!input.equalsIgnoreCase("bye")){
            System.out.println(
                    "    ____________________________________________________________\n" +
                    "     " + input +
                    "    ____________________________________________________________\n" );
            input = scanner.nextLine();
        }

        if (input.equalsIgnoreCase("bye")){
            System.out.println("Bye. Hope to see you again soon!\n" +
                                "____________________________________________________________");
        }
    }
}
