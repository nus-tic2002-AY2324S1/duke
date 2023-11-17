package Duke;
import Shelf.Shelf;
import Task.Text;

import java.io.IOException;
import java.util.*;

/**
 * Reads input and convert it to other forms usable by the rest of code.
 * Parse method check user input and identifies keywords to carry out specific command
 */
public class Parser {

    public static void Parse(Shelf tasklist) throws DukeException {
        String echo;
        Scanner in = new Scanner(System.in);
        Text.showWelcome();
        while(true){
            echo = in.nextLine();
            String[] input = InputParser(echo); //input[0] = command, input[1] = description, input[2] = others e.g. time,date/tags etc...
            try {
                switch (input[0]) {
                case "bye":
                    Text.printMessage(Text.Message.BYE);
                    return;
                case "list":
                    Shelf.listShelf();
                    break;
                case "mark":
                    Shelf.markTask(input);
                    break;
                case "unmark":
                    Shelf.markTask(input);
                    break;
                case "todo" :
                case "deadline":
                case "event":
                    if(input.length >= 3){
                        Shelf.addDateTask(input[0], input[1], input[2]);
                    }else{
                        Shelf.addSpecialTask(input[0], input[1]);
                    }
                    break;
                case "#":
                    Shelf.addTagslist(input[1], input[2]);
                    break;
                case "-":
                    Shelf.removeTagfromlist(input[1], input[2]);
                    break;
                case "find":
                    Shelf.findItem(input[1]);
                    break;
                case "delete":
                    Shelf.deleteTask(input[1]);
                    break;
                case "save":
                    Storage.CreateFile(Shelf.ShelftoString());
                    break;
                case "load":
                    tasklist = Storage.FileParser(Storage.loadFile("data"));
                    break;
                default:
                    Shelf.addItem(input[0]);
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
        if(input.charAt(0) == '#' || input.charAt(0) == '-'){ // parse input with tags
            String tags = words[0].substring(1);
            return new String[]{String.valueOf(input.charAt(0)), tags, words[1]};
        }
        if(words.length > 1){ //parse input with date and time
            String[] taskwithdates = words[1].split("/by ", 2);
            if(taskwithdates.length > 1){
                return new String[]{words[0],taskwithdates[0],taskwithdates[1]};
            }
        }
        return words;
    }

    public static String TypeParser(String typeshort){
        switch (typeshort){
            case "T":
                return "todo";
            case "D":
                return "deadline";
            case "E":
                return "event";
            default:
                return " ";
        }
    }

}