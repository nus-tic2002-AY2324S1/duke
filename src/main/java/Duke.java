import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) throws DukeException {
        String echo;
        Shelf tasklist = new Shelf();
        Scanner in = new Scanner(System.in);

        Text.printMessage(Text.Message.LOGO);
        Text.printMessage(Text.Message.GREETING);

        while(true){
            echo = in.nextLine();
            String[] input = InputParser(echo); //input[0] = first word, input[1] = rest of sentence
            try {
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
                    tasklist.addSpecialTask(input[1], input[0]);
                    break;
                case "deadline":
                    tasklist.addSpecialTask(input[1], input[0]);
                    break;
                case "event":
                    tasklist.addSpecialTask(input[1], input[0]);
                    break;
                case "delete":
                    tasklist.deleteTask(input[1]);
                    break;
                case "save":
                    FileSaver.CreateFile(tasklist.ShelftoString());
                    break;
                case "parse":
                    tasklist = FileParser(FileSaver.loadFile("data"));
                    break;

                    default:
                    tasklist.addItem(input[0]);
                }
            } catch (IndexOutOfBoundsException e){
                System.out.println("OOPS!!! You must have done something wrong xxx.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
    public static String[] InputParser(String input) {
        String[] words = input.split(" ", 2);
        return words;
    }

    public static Shelf FileParser(String shelfData) throws DukeException {
        Shelf newlist = new Shelf();
        String type = "";
        String marking = "";
        int line_no = 1;
        String[] split = shelfData.split("[|\n]");
        for (int i = 1; i-1 < split.length; i++) {
            if (i%3 == 0) {
                type = split[i-3];
                marking = split[i-2];
                newlist.addSpecialTask(split[i-1], type);
                System.out.println(split[i-1]);
                if(!marking.isEmpty()){
                    newlist.markTask(new String[]{"mark", Integer.toString(line_no)});
                }
                line_no++;
                type = "";
                marking = "";
            }
        }
        System.out.println("File loaded, Welcome Back Taskmaster!");
        return newlist;
    }

}