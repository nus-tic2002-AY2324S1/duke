import javax.swing.*;
import java.util.Arrays;
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
    public static void TED(){
        ArrayList<Task> Storage = new ArrayList<>();
        Task Selector;
        boolean Power=true;
        while(Power){
            String line = Scan();
            String[] words = line.split(" ");
            switch(words[0]){
                case "bye" : Separator();
                    System.out.println("Goodbye , See you next time '~'");
                    Separator();
                    Power=false;
                    break;
                case "list" : Separator();
                    System.out.println("Here are your list of Tasks");
                    for(int i=0;i<Storage.size();i++){
                        System.out.println(i+1 + "." + Storage.get(i));
                    }
                    System.out.println("Work harder, there is " + Storage.size() + " more task now");
                    Separator();
                    break;
                case "mark" : Selector = Storage.get(Integer.parseInt(words[1])-1);
                    Selector.mark(true);
                    System.out.println("Excellent , Cate marks your Task");
                    System.out.println(Selector);
                    break;
                case "unmark" : Selector = Storage.get(Integer.parseInt(words[1])-1);
                    Selector.mark(false);
                    System.out.println("Don't worry , Cate un-marks your Task");
                    System.out.println(Selector);
                    break;
                case "todo" :
                    Selector = new Todo(line.substring(5,line.length()));
                    Storage.add(Selector);
                    Separator();
                    System.out.println("Just do it");
                    System.out.println(Selector);
                    TaskCount(Storage.size());
                    Separator();
                    break;
                case "deadline" :
                    Selector = new Deadline(line.substring(9,line.indexOf("/by")),line.substring(line.indexOf("/by")+4,line.length()));
                    Storage.add(Selector);
                    Separator();
                    System.out.println("Time is ticking");
                    System.out.println(Selector);
                    TaskCount(Storage.size());
                    Separator();
                    break;
                case "event" :
                    Selector = new Event(line.substring(6,line.indexOf("/from")),line.substring(line.indexOf("/from")+6,line.indexOf("/to")),line.substring(line.indexOf("/to")+4,line.length()));
                    Storage.add(Selector);
                    Separator();
                    System.out.println("Track the duration");
                    System.out.println(Selector);
                    TaskCount(Storage.size());
                    Separator();
                    break;
            }
        }
    }
}

