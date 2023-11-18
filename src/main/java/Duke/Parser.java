package Duke;
import Shelf.ShelfEditor;
import Shelf.ShelfManager;
import Task.Text;

import java.io.IOException;
import java.util.*;

import static Shelf.ShelfManager.listItem;

/**
 * Reads input and convert it to other forms usable by the rest of code.
 * Parse method check user input and identifies keywords to carry out specific command
 */
public class Parser {
    public static void Parse(ShelfManager tasklist) throws DukeException {
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
                    ShelfManager.listShelf();
                    break;
                case "mark":
                    ShelfManager.markTask(input);
                    break;
                case "unmark":
                    ShelfManager.markTask(input);
                    break;
                case "todo" :
                case "deadline":
                case "event":
                    if(input.length >= 3){
                        ShelfManager.addDateTask(input[0], input[1], input[2]);
                    }else{
                        ShelfManager.addSpecialTask(input[0], input[1]);
                    }
                    break;
                case "#":
                    ShelfManager.addTagslist(input[1], input[2]);
                    break;
                case "-":
                    assert input.length > 2 : "Insufficient input for 'delete tag' command. Requires tag description and task NO";
                    ShelfManager.removeTagfromlist(input[1], input[2]);
                    break;
                case "find":
                    ShelfManager.findItem(input[1]);
                    break;
                case "delete":
                    ShelfManager.deleteTask(input[1]);
                    break;
                case "save":
                    String saveName = Text.showPrompt("What is the filename you want to save the current list to: ");
                    Storage.CreateFile(saveName, ShelfManager.ShelftoString());
                    break;
                case "load":
                    String loadName = Text.showPrompt("What is the name for the file you want to load: ");
                    tasklist = Storage.FileParser(Storage.loadFile(loadName));
                    break;
                case "edit":
                    if(!Text.fieldChecker(input)){
                        break;
                    }
                    System.out.println(listItem(Integer.parseInt(input[1])-1));
                    String prompt = Text.showPrompt("Choose what you what you want to edit [description, date]: ");
                    ShelfEditor.editSelector(prompt, input[1]);
                    break;
                default:
                    ShelfManager.addItem(input[0]);
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
            assert words.length > 1 : "Insufficient input for 'add tag' command. Requires tag description and task NO";
            if(words.length == 1){
                System.out.println("assertion fail");
                return new String[]{" "};
            }
            return new String[]{String.valueOf(input.charAt(0)), tags, words[1]};
        }
        if(words.length > 1){ //parse input with date and time
            String[] taskwithdates = words[1].split("/by ", 2);
            if(taskwithdates.length > 1){
                return new String[]{words[0],removeEndingWhitespace(taskwithdates[0]),taskwithdates[1]};
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
    public static String removeEndingWhitespace(String str) {
        if (str.endsWith(" ")) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }
}