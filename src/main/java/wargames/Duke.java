package wargames;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Joshua.printGreetings();

        String input;

        do {
            System.out.print(">> ");
            input = getUserInput();

            if (input.equals("list")){
                Joshua.printMyList();
            }
            else {
                Joshua.addTaskToList(input);
            }
        } while (!input.equals("bye"));

        Joshua.joshuaSays("\nGOODBYE.");
    }

    public static String getUserInput() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        line = line.toLowerCase();
        return line;
    }

}
