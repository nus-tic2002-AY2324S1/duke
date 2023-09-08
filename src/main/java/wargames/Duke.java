package main.java;
import java.util.Scanner;
import main.java.MyList;

public class Duke {
    public static void main(String[] args) {
        joshuaSays("GREETINGS PROFESSOR FALKEN.\n\n"
                    + "SHALL WE PLAY A GAME?");

        String input;
        MyList list_obj = new MyList();
        do {
            System.out.print(">> ");
            input = getUserInput();
            joshuaSays(input);
        } while (!input.equals("bye"));

        joshuaSays("\nGOODBYE.");
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
