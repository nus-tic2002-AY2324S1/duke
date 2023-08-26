import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo =
                "__          __         _            ____        _   \n"
                + "\\ \\        / /        | |          |  _ \\      | |  \n"
                + " \\ \\  /\\  / /__  _ __ | | ___   _  | |_) | ___ | |_ \n"
                + "  \\ \\/  \\/ / _ \\| '_ \\| |/ / | | | |  _ < / _ \\| __|\n"
                + "   \\  /\\  / (_) | | | |   <| |_| | | |_) | (_) | |_ \n"
                + "    \\/  \\/ \\___/|_| |_|_|\\_\\\\__, | |____/ \\___/ \\__|\n"
                + "                             __/ |                  \n"
                + "                            |___/                  \n";
        System.out.println("Wonky: Hello from\n" + logo);
        System.out.println("Wonky: I'm Wonky the Fairy.");
        System.out.println("Wonky: What can I do for you?");

        String line;
        Scanner in = new Scanner(System.in);

        while (!(line = in.nextLine()).equals("bye")) {
            System.out.println("Wonky: " + line);
        }
        System.out.println("Wonky: Bye! Thank you for using Wonky Bot. Hope to see you again soon!");
        in.close();
    }
}
