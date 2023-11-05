import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    public UI(){}
    /**
     * prints logo of the UI
     */
    public static void Logo(){
        String logo = " _ _ _              _ \n"
                + "|  _ _ \\   __    _ | | _    _ _\n"
                + "| |      / __ \\ | _   _ | / __ \\\n"
                + "| | _ _ | |__| |   | |   |  __ / \n"
                + "| _ _ / |_|  |_|   |_|    \\ __\\\n";
        System.out.println(logo);
    }
    /**
     * prints the welcome message at the start of the application run.
     */
    public static void Greeter(){
        Separator();
        Spacer();
        System.out.println("Hello! I am Cate");
        System.out.println("How may I help you?");
        Separator();
    }
    /**
     * print divider line
     */
    public static void Separator(){
        System.out.println("__________________________");
    }
    public static void Spacer(){
        System.out.println("    ");
    }
    /**
     * prints number of task
     */    public static void TaskCount(int i){
        System.out.println("The list is increasing , there is "+ i +" Tasks now");
    }/**
     * prints default error message
     */
    public static void ErrorDuke(){
        Separator();
        System.out.println("Cate does not know what this means");
        System.out.println("The available commands are : list , mark , unmark , todo , deadline , event , bye , find");
        Separator();
    }
    /**
     * scans input and return as string
     */
    public static String Scan(){
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        return line;
    }
    /**
     * returns flavour text response based on keyword input
     */
    public static void Response(Keyword input,ListTask Storage,Integer number){
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
                System.out.println(Storage.get(number));
                Separator();
                break;
            case UNMARK:
                Separator();
                System.out.println("Don't worry , Cate un-marks your Task");
                System.out.println(Storage.get(number));
                Separator();
                break;
            case TODO:
                Separator();
                System.out.println("Just do it");
                System.out.println(Storage.get(number));
                TaskCount(Storage.size());
                Separator();
                break;
            case DEADLINE:
                Separator();
                System.out.println("Time is ticking");
                System.out.println(Storage.get(number));
                TaskCount(Storage.size());
                Separator();
                break;
            case EVENT:
                Separator();
                System.out.println("Track the duration");
                System.out.println(Storage.get(number));
                TaskCount(Storage.size());
                Separator();
                break;
        }
    }
    /**
     * main application run function that loops
     *
     * uses parser to check for invalid keywords and returns error messages
     */
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
                if(Checker.getNoError())
                    switch (key) {
                        case DELETE:
                            Storage.removeTask(Integer.parseInt(words[1]) - 1);
                            Response(key,Storage,Integer.parseInt(words[1]) - 1);
                            break;
                        case BYE:
                            Response(key,Storage,0);
                            Power = false;
                            break;
                        case LIST:
                            Response(key,Storage,0);
                            break;
                        case MARK:
                            Selector = Storage.get(Integer.parseInt(words[1]) - 1);
                            Selector.mark(true);
                            Response(key,Storage,Integer.parseInt(words[1]) - 1);
                            break;
                        case UNMARK:
                            Selector = Storage.get(Integer.parseInt(words[1]) - 1);
                            Selector.mark(false);
                            Response(key,Storage,Integer.parseInt(words[1]) - 1);
                            break;
                        case TODO:
                            Selector = new Todo(line.substring(5));
                            Storage.add(Selector);
                            Response(key,Storage,Storage.size()-1);
                            break;
                        case DEADLINE:
                            Selector = new Deadline(line.substring(9, line.indexOf("/by")), line.substring(line.indexOf("/by") + 4));
                            Storage.add(Selector);
                            Response(key,Storage,Storage.size()-1);
                            break;
                        case EVENT:
                            Selector = new Event(line.substring(6, line.indexOf("/from")), line.substring(line.indexOf("/from") + 6, line.indexOf("/to")), line.substring(line.indexOf("/to") + 4));
                            Storage.add(Selector);
                            Response(key,Storage,Storage.size()-1);
                            break;
                        case FIND:
                            Storage.findTask(line.substring(5));
                        default:
                            throw new IllegalArgumentException();
                    }
            } catch (IllegalArgumentException e){
                ErrorDuke();
            }
        }
    }
}
