public class Ui {
    public static void printHeader(){
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
    }
    public static void printBye(){
        System.out.println("Bye. Hope to see you again soon!\n" +
                "____________________________________________________________");
    }

    public static void markNotDone(){
        System.out.println("    ____________________________________________________________\n"+
                "    OK, I've marked this task as not done yet: ");
        System.out.print("       ");
    }

    public static void markDone(){
        System.out.println("    ____________________________________________________________\n"+
                "    Nice! I've marked this task as done: ");
        System.out.print("       ");
    }

    public static void printLine(){
        System.out.println("    ____________________________________________________________");
    }

    public static void printRemoveMsg(){
        System.out.println(
                "    ____________________________________________________________\n" +
                        "     Noted. I've removed this task:");
        System.out.print("       ");
    }

    
}
