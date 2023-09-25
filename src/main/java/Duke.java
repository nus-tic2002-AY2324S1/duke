import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String echo;
        Shelf tasklist = new Shelf();
        Scanner in = new Scanner(System.in);

        Text.printMessage(Text.Message.LOGO);
        Text.printMessage(Text.Message.GREETING);

        while(true){
            echo = in.nextLine();
            String[] input = InputParser(echo); //input[0] = first word, input[1] = rest of sentence

            switch (input[0]) {
                case "bye":
                    Text.printMessage(Text.Message.BYE);
                    return;
                case "list":
                    tasklist.listShelf();
                    break;
                case "mark":
                    tasklist.markTask(input);
                    break;
                case "unmark":
                    tasklist.markTask(input);
                    break;
                case "todo":
                    tasklist.addSpecialTask(input[1],input[0]);
                    break;
                case "deadline":
                    tasklist.addSpecialTask(input[1],input[0]);
                    break;
                case "event":
                    tasklist.addSpecialTask(input[1],input[0]);
                    break;
                default:
                    tasklist.addItem(input[0]);
            }
        }

    }
    public static String[] InputParser(String input) {
        String[] words = input.split(" ", 2);
        return words;
    }

}