import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String echo = new String("test");

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String greeting = "____________________________________________________________\n" +
                " Hello! I'm CLARA \n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";

        String bye = "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";

        System.out.println(greeting + bye);
    }
}