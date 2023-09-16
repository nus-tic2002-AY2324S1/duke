import java.util.ArrayList;

public class Conversation {
    final static int INDENT_RESPONSE = 5;
    final static int INDENT_LINE = 4;
    public static void echo(String input){
        line();
        indentation(INDENT_RESPONSE);
        System.out.println(input);
        line();
    }

    public static void echo(ArrayList<String> inputs){
        line();
        for (var input : inputs) {
            indentation(INDENT_RESPONSE);
            System.out.println(input);
        }
        line();
    }

    public static void echoForGreet(ArrayList<String> inputs){
        for (var input : inputs) {
            indentation(INDENT_RESPONSE);
            System.out.println(input);
        }
        line();
    }

    public static void indentation(int n) {
        System.out.print(" ".repeat(n));
    }

    public static void line() {
        String star = "*";
        String line = star.repeat(80);
        indentation(INDENT_LINE);
        System.out.println(line);
    }

}
