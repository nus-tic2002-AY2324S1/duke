import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    public UI(){}

    public static void Logo(){
        String logo = " _ _ _              _ \n"
                + "|  _ _ \\   __    _ | | _    _ _\n"
                + "| |      / __ \\ | _   _ | / __ \\\n"
                + "| | _ _ | |__| |   | |   |  __ / \n"
                + "| _ _ / |_|  |_|   |_|    \\ __\\\n";
        System.out.println(logo);
    }
    public static void Greeter(){
        Separator();
        Spacer();
        System.out.println("Hello! I am Cate");
        System.out.println("How may I help you?");
        Separator();
    }
    public static void Separator(){
        System.out.println("__________________________");
    }
    public static void Spacer(){
        System.out.println("    ");
    }
    public static void TaskCount(int i){
        System.out.println("The list is increasing , there is "+ i +" Tasks now");
    }
    public static void ErrorDuke(){
        Separator();
        System.out.println("Cate does not know what this means");
        System.out.println("The available commands are : list , mark , unmark , todo , deadline , event , bye");
        Separator();
    }
    public static String Scan(){
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        return line;
    }
    public static void Response(Keyword input,ListTask Storage){
        switch (input){
            case DELETE:
            case LIST:
                Storage.listAll(input);
                break;
            case BYE:
                Separator();
                System.out.println("Goodbye , See you next time '~'");
                Separator();
                break;
            case MARK:
                Separator();
                System.out.println("Excellent , Cate marks your Task");
                System.out.println(Storage.get(Storage.size()-1));
                Separator();
                break;
            case UNMARK:
                Separator();
                System.out.println("Don't worry , Cate un-marks your Task");
                System.out.println(Storage.get(Storage.size()-1));
                Separator();
                break;
            case TODO:
                Separator();
                System.out.println("Just do it");
                System.out.println(Storage.get(Storage.size()-1));
                TaskCount(Storage.size());
                Separator();
                break;
            case DEADLINE:
                Separator();
                System.out.println("Time is ticking");
                System.out.println(Storage.get(Storage.size()-1));
                TaskCount(Storage.size());
                Separator();
                break;
            case EVENT:
                Separator();
                System.out.println("Track the duration");
                System.out.println(Storage.get(Storage.size()-1));
                TaskCount(Storage.size());
                Separator();
                break;
        }
    }
    public void Run(ListTask Storage,Parser Checker){
        Logo();
        Greeter();
        Task Selector;
        boolean Power=true;
        while(Power){
            try {
                String line = Scan();
                String[] words = line.split(" ");
                Keyword key = Keyword.valueOf(words[0].toUpperCase());
                Checker.checkError(key,line,Storage);
                if(Checker.getNoError()) //checks for argument errors
                    switch (key) {
                        case DELETE:
                            Storage.removeTask(Integer.parseInt(words[1]) - 1);
                            Response(key,Storage);
                            break;
                        case BYE:
                            Response(key,Storage);
                            Power = false;
                            break;
                        case LIST:
                            Response(key,Storage);
                            break;
                        case MARK:
                            Selector = Storage.get(Integer.parseInt(words[1]) - 1);
                            Selector.mark(true);
                            Response(key,Storage);
                            break;
                        case UNMARK:
                            Selector = Storage.get(Integer.parseInt(words[1]) - 1);
                            Selector.mark(false);
                            Response(key,Storage);
                            break;
                        case TODO:
                            Selector = new Todo(line.substring(5, line.length()));
                            Storage.add(Selector);
                            Response(key,Storage);
                            break;
                        case DEADLINE:
                            Selector = new Deadline(line.substring(9, line.indexOf("/by")), line.substring(line.indexOf("/by") + 4, line.length()));
                            Storage.add(Selector);
                            Response(key,Storage);
                            break;
                        case EVENT:
                            Selector = new Event(line.substring(6, line.indexOf("/from")), line.substring(line.indexOf("/from") + 6, line.indexOf("/to")), line.substring(line.indexOf("/to") + 4, line.length()));
                            Storage.add(Selector);
                            Response(key,Storage);
                            break;
                        case FIND:

                        default:
                            throw new IllegalArgumentException();
                    }
            } catch (IllegalArgumentException e){
                ErrorDuke();
            }
        }
    }
}
