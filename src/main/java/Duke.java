public class Duke {
    public static void greetings(){
        printDashes();
        System.out.println("Hello I'm Tim\nWhat can I do for you?");
        printDashes();
    }
    public static void printDashes(){
        System.out.println();
        System.out.println("____________________________________________________________");
    }

    public static void goodbyeGreet(){
        System.out.println("Bye. Hope to see you again soon!");
        printDashes();
    }
    public static void main(String[] args) {
        String logo =
                    " _______                 \n"
                +   "|__   __| [ ]  __  __    \n"
                +   "   | |    | | |  \\/  |  \n"
                +   "   | |    | | | |\\/| |  \n"
                +   "   |_|    |_| |_|  |_|   \n";
        System.out.println("Hello from\n" + logo);
        greetings();
        goodbyeGreet();
    }
}
