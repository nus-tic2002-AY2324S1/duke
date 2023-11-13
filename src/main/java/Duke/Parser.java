package Duke;

import Duke.DukeException;
import Task.Shelf;
import Task.Text;

import java.io.IOException;
import java.util.Scanner;

public class Parser {

    public static void Parse(Shelf tasklist) throws DukeException {
        String echo;
        Scanner in = new Scanner(System.in);

        while(true){
            echo = in.nextLine();
            String[] input = InputParser(echo); //input[0] = first word, input[1] = rest of sentence
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
                    if(!input[2].isEmpty()){
                        Shelf.addDateTask(input[1], input[0], input[2]);
                        System.out.println("test");
                    }else{
                        Shelf.addSpecialTask(input[1], input[0]);
                    }
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
        if(words.length > 1){
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