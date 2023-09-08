import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("GREETINGS PROFESSOR FALKEN.\n\n"
                            + "SHALL WE PLAY A GAME?\n");
        String input;
        do {
            System.out.print(">> ");
            input = getUserInput();
            joshuaSays(input);
        } while (!input.equals("bye"));

        System.out.println("\nGOODBYE.\n");
    }

    public static String getUserInput(){
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        line = line.toLowerCase();
        return line;
    }

    public static void joshuaSays(String str){
        str = str.toUpperCase();
        System.out.println(str + "\n");
    }
}
