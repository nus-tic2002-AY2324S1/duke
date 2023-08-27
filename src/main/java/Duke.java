public class Duke {
    public static void main(String[] args) {
        greet();
    }

    private static void greet(){
        String myChatBotName = "Duke";

        System.out.println(line());
        System.out.println(logo());
        System.out.println(line());
        System.out.printf("Hello! I'm %s\n", myChatBotName);
        System.out.println("What can I do for you?");
        System.out.println(line());
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static String line () {
        String horizontalBox = "─";
        return horizontalBox.repeat(80);
    }

    private static String logo(){
        String logo =
                " ____        _        \n"
                        + "|  _ \\ _   _| | _____ \n"
                        + "| | | | | | | |/ / _ \\\n"
                        + "| |_| | |_| |   <  __/\n"
                        + "|____/ \\__,_|_|\\_\\___|\n";
        return logo;
    }
}
