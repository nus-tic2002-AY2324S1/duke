
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    private static Storage storage;
    private static ListTask listofitems;
    public static void main(String[] args) {
        Parser parse;
        storage = new Storage();
        //Echo();
        //List();
        //MarkDone();
        //TED();
       try {
            listofitems = storage.load();
        } catch (FileNotFoundException e) {
            listofitems = new ListTask();
        }
/*
        Task t = new Todo("fly kite");
        listofitems.AddTask(t);
        t = new Deadline("return kite","june 2023");
        listofitems.AddTask(t);
        listofitems.ListAll(Keyword.LIST);
        try{
        storage.save(listofitems);}
        catch (IOException e) {
           System.out.println("error save");
        } */
        listofitems.ListAll(Keyword.LIST);
    }
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
    public static String Scan(){
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        return line;
    }
    public static void Echo(){
        while(true){
            String line = Scan();
            if(line.contains("bye")){
                Separator();
                System.out.println("Goodbye , See you next time ;)");
                Separator();
                break;
            } else {
                Separator();
                System.out.println(line);
                Separator();
            }
        }
    }
    public static void List(){
        ArrayList<String> Storage = new ArrayList<>();

        while(true){
            String line = Scan();
            if(line.contains("bye")){
                Separator();
                System.out.println("Goodbye , See you next time ;)");
                Separator();
                break;
            } else if (line.equals("list")){
                Separator();
                System.out.println("Here are your list of Tasks");
                for(int i=0;i<Storage.size();i++){
                System.out.println(i+1 + ". " + Storage.get(i));
                }
                Separator();
            } else {
                Storage.add(line);
                Separator();
                System.out.println("Added: " + line);
                Separator();
            }
        }
    }
    public static void MarkDone(){
        ArrayList<Task> Storage = new ArrayList<>();

        while(true){
            String line = Scan();
            String[] words = line.split(" ");
            if(words[0].equals("bye")){
                Separator();
                System.out.println("Goodbye , See you next time ;)");
                Separator();
                break;
            } else if (words[0].equals("list")){
                Separator();
                System.out.println("Here are your list of Tasks");
                for(int i=0;i<Storage.size();i++){
                    System.out.println(i+1 + "." + Storage.get(i));
                }
                Separator();
            } else if (words[0].equals("mark")){
                Task Marker = Storage.get(Integer.parseInt(words[1])-1);
                Marker.mark(true);
                System.out.println("Excellent , Cate marks your Task");
                System.out.println(Marker);

            } else if (words[0].equals("unmark")){
                Task Marker = Storage.get(Integer.parseInt(words[1])-1);
                Marker.mark(false);
                System.out.println("Dont worry , Cate un-marks your Task");
                System.out.println(Marker);
            } else {
                Storage.add(new Task(line));
                Separator();
                System.out.println("Added: " + line);
                Separator();
            }
        }
    }
    public static void ErrorDuke(){
        Separator();
        System.out.println("Cate does not know what this means");
        System.out.println("The available commands are : list , mark , unmark , todo , deadline , event , bye");
        Separator();
    }
    public static void Response(Keyword input,ArrayList<Task> Storage){
        switch (input){
            case DELETE:
            case LIST:
                ListTask(input,Storage);
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
    public static void ListTask(Keyword k,ArrayList<Task> Storage){
        Separator();
        System.out.println("Here is your list of Tasks");
        for (int i = 0; i < Storage.size(); i++) {
            System.out.println(i + 1 + "." + Storage.get(i));
        }
        if(Storage.isEmpty())
            System.out.println("Well done , the List is now empty");
        else if(k==Keyword.LIST)
            System.out.println("Work harder, there is " + Storage.size() + " more task now");
        else if (k==Keyword.DELETE)
            System.out.println("Great! there is only " + Storage.size() + " task in the List now");
        Separator();
    }
    public static ArrayList<Task> RemoveTask(ArrayList<Task> Storage ,int number){
        ArrayList<Task> StorageUpdate = new ArrayList<>();
        for(int i=0;i< Storage.size();i++){
            if(i != number)
            StorageUpdate.add(Storage.get(i));
            else {
                Separator();
                System.out.println("Cate has deleted this Task:");
                System.out.println(Storage.get(i));
            }
        }
        return StorageUpdate;
    }

    public static void TED(){
        Duke.Logo();
        Duke.Greeter();
        ArrayList<Task> Storage = new ArrayList<>();
        Task Selector;
        boolean Power=true;
        while(Power){
            try {
                String line = Scan();
                String[] words = line.split(" ");
                Keyword key = Keyword.valueOf(words[0].toUpperCase());
                Parser Checker = new Parser(key,line,Storage);
                if(Checker.getNoError()) //checks for argument errors
                switch (key) {
                    case DELETE:
                        Storage = RemoveTask(Storage,Integer.parseInt(words[1]) - 1);
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
                    default:
                        throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e){
                ErrorDuke();
            }
        }
    }
}
