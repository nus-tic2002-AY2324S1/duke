import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Logo();
        Greeter();
        //Echo();
        //List();
        //MarkDone();
        TED();

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
                ListTask(input,Storage);
                break;
            case BYE:
                Separator();
                System.out.println("Goodbye , See you next time '~'");
                Separator();
                break;
            case LIST:
                ListTask(input,Storage);
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
    public enum Keyword {
        BYE,LIST,MARK,UNMARK,TODO,DEADLINE,EVENT,DELETE
    }
    public static boolean NoTextError(Keyword k,String line,ArrayList<Task> Storage){

        String[] words = line.split(" ");
        try{
            switch(k){
                case DELETE:
                    if(words.length==1)
                        throw new MissingArgumentException("Nothing selected to delete");
                    if(Integer.parseInt(words[1])<=0)
                        throw new MissingArgumentException("Cate cant find imaginary tasks");
                    if(Storage.size()<Integer.parseInt(words[1]))
                        throw new MissingArgumentException("Cate is unable to delete whats outside the list , try again");
                    break;
                case LIST:
                    if(Storage.isEmpty()){
                        throw new MissingArgumentException("Oops , List is Empty");
                    }
                    break;
                case MARK:
                    if(Integer.parseInt(words[1])<=0)
                        throw new MissingArgumentException("If its not a real number, it wont be marked");
                    if(Storage.size()<Integer.parseInt(words[1]))
                        throw new MissingArgumentException("Task number is larger than the list , add more task");
                    if(Storage.get(Integer.parseInt(words[1])-1).isDone)
                        throw new MissingArgumentException("Cate has already marked it as done");
                    break;
                case UNMARK:
                    if(Integer.parseInt(words[1])<=0)
                        throw new MissingArgumentException("Only real numbers please");
                    if(Storage.size()<Integer.parseInt(words[1]))
                        throw new MissingArgumentException("Task number is not within the list , try again");
                    if(!Storage.get(Integer.parseInt(words[1])-1).isDone)
                        throw new MissingArgumentException("Mark it as done first, Cate is unable to Unmark");
                    break;
                case TODO:
                    if(words.length==1)
                        throw new MissingArgumentException("Nothing Todo");
                    break;
                case DEADLINE:
                    if(words.length==1)
                        throw new MissingArgumentException("Where is the Deadline context and due time?");
                    if(words[1].equals("/by"))
                        throw new MissingArgumentException("Theres no content for Deadline");
                    if(!line.contains("/by"))
                        throw new MissingArgumentException("its not a Deadline without an expiry");
                    if(words[words.length-1].equals("/by"))
                        throw new MissingArgumentException("Deadline due time is blank");
                    break;
                case EVENT:
                    if(words.length==1)
                        throw new MissingArgumentException("What Event is this? there is no content , start time and end time");
                    if(words[1].equals("/from"))
                        throw new MissingArgumentException("Event is missing context");
                    if(words[1].equals("/to"))
                        throw new MissingArgumentException("Event is missing context");
                    if(!line.contains("/from"))
                        throw new MissingArgumentException("Event cant start without a start time , add /from");
                    if(!line.contains("/to"))
                        throw new MissingArgumentException("Does the Event not end? , add /to");
                    if(line.contains("/from /to"))
                        throw new MissingArgumentException("Event start time is blank");
                    if(words[words.length-1].equals("/to"))
                        throw new MissingArgumentException("Event end time is blank");
                    if(line.indexOf("/from")>line.indexOf("/to"))
                        throw new MissingArgumentException("Start time first followed by end time");
                    break;
            }
        } catch (MissingArgumentException e){
            Separator();
            System.out.println(e);
            Separator();
            return false;
        }
        return true;
    }
    public static void TED(){
        ArrayList<Task> Storage = new ArrayList<>();
        Task Selector;
        boolean Power=true;
        while(Power){
            try {
                String line = Scan();
                String[] words = line.split(" ");
                Keyword key = Keyword.valueOf(words[0].toUpperCase());
                if(NoTextError(key,line,Storage)) //checks for argument errors
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
