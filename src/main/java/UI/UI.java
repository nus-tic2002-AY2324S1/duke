package UI;
import parser.Keyword;
import parser.Parser;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.util.Scanner;

public class UI {
    /**
     * Constructor for UI
     */
    public UI(){}
    /**
     * prints logo of the UI
     */
    public static void logo(){
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
    public static void greeter(){
        Separator();
        spacer();
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
    /**
     * print blank space line
     */
    public static void spacer(){
        System.out.println("    ");
    }
    /**
     * prints number of task
     *
     * @param i number of task
     */
    public static void taskCount(int i){
        System.out.println("The list is increasing , there is "+ i +" Tasks now");
    }
    /**
     * prints default error message
     */
    public static void errorDuke(){
        Separator();
        System.out.println("Cate does not know what this means");
        System.out.println("The available commands are : list , mark , unmark , todo , deadline , event , bye , find , view");
        Separator();
    }
    /**
     * @return string input from scans
     */
    public static String scan(){
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        return line;
    }
    /**
     * returns flavour text response based on keyword input
     *
     * @param input Keyword used for switch case
     * @param list list of tasks
     * @param number task number in list
     */
    public static void response(Keyword input, ListTask list, Integer number){
        switch (input){
            case SORT:
            case DELETE:
            case LIST:
                list.listAll(input);
                break;
            case BYE:
                Separator();
                System.out.println("Goodbye , See you next time '~'");
                Separator();
                break;
            case MARK:
                Separator();
                System.out.println("Excellent , Cate marks your Task");
                System.out.println(list.get(number));
                Separator();
                break;
            case UNMARK:
                Separator();
                System.out.println("Don't worry , Cate un-marks your Task");
                System.out.println(list.get(number));
                Separator();
                break;
            case TODO:
                Separator();
                System.out.println("Just do it");
                System.out.println(list.get(number));
                taskCount(list.size());
                Separator();
                break;
            case DEADLINE:
                Separator();
                System.out.println("Time is ticking");
                System.out.println(list.get(number));
                taskCount(list.size());
                Separator();
                break;
            case EVENT:
                Separator();
                System.out.println("Track the duration");
                System.out.println(list.get(number));
                taskCount(list.size());
                Separator();
                break;
        }
    }
    /**
     * main application run function that loops
     * uses parse to check for invalid keywords and returns error messages
     *
     * @param list List class used to run UI
     */
    public void run(ListTask list){
        logo();
        greeter();
        Task Selector;
        boolean Power=true;
        while(Power){
            try {
                String line = scan();
                String[] words = line.split(" ");
                Keyword key = Keyword.valueOf(words[0].toUpperCase());
                if(Parser.isCorrect(key,line,list))
                    switch (key) {
                        case VIEW:
                            list.viewSchedule(words[1]);
                            break;
                        case SORT:
                            list.sortSchedule();
                            response(key,list,0);
                            break;
                        case DELETE:
                            list.removeTask(Integer.parseInt(words[1]) - 1);
                            response(key,list,Integer.parseInt(words[1]) - 1);
                            break;
                        case BYE:
                            response(key,list,0);
                            Power = false;
                            break;
                        case LIST:
                            response(key,list,0);
                            break;
                        case MARK:
                            Selector = list.get(Integer.parseInt(words[1]) - 1);
                            Selector.mark(true);
                            response(key,list,Integer.parseInt(words[1]) - 1);
                            break;
                        case UNMARK:
                            Selector = list.get(Integer.parseInt(words[1]) - 1);
                            Selector.mark(false);
                            response(key,list,Integer.parseInt(words[1]) - 1);
                            break;
                        case TODO:
                            Selector = new Todo(line.substring(5));
                            list.add(Selector);
                            response(key,list,list.size()-1);
                            break;
                        case DEADLINE:
                            String byDateTime= line.substring(line.indexOf("/by")+4);
                            Selector = new Deadline(line.substring(9, line.indexOf("/by")), Parser.constructDateTime(byDateTime));
                            list.add(Selector);
                            response(key,list,list.size()-1);
                            break;
                        case EVENT:
                            String fromDateTime= line.substring(line.indexOf("/from")+6,line.indexOf("/to"));
                            String toDateTime= line.substring(line.indexOf("/to")+4);
                            Selector = new Event(line.substring(6, line.indexOf("/from")), Parser.constructDateTime(fromDateTime), Parser.constructDateTime(toDateTime));
                            list.add(Selector);
                            response(key,list,list.size()-1);
                            break;
                        case FIND:
                            list.findTask(line.substring(5));
                            break;
                        default:
                            throw new IllegalArgumentException();
                    }
            } catch (IllegalArgumentException e){
                errorDuke();
            }
        }
    }
}
