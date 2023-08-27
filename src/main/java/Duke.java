public class Duke {
    public static void main(String[] args) {
        greet();
    }

    private static void greet(){
        String myChatBotName = "Luna";

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
                  " _                    \n"
                + "| |    _   _ _  __     __\n"
                + "| |   | | | | | __  \\/ __ \\\n"
                + "| |___| |_| | |   | | |__| |\n"
                + "|_____|\\__,_|_|   |_|_|  |_|\n";
        return logo;
    }
}
