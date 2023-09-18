import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Logo();
        Greeter();
        //Echo();
        List();
    }
    public static void Logo(){
        String logo = " _ _               _\n"
                + "|  __\\    __    _ | | _\n"
                + "| |     / __ \\ | _   _ |  \n"
                + "| |_ _ | |__| |   | |\n"
                + "| _ _/ |_|  |_|   |_|\n";
        System.out.println(logo);
    }
    public static void Greeter(){
        Separator();
        Spacer();
        System.out.println("Hello! I am Cat");
        System.out.println("How may I help you?");
        Separator();
    }
    public static void Separator(){
        System.out.println("__________________________");
    }
    public static void Spacer(){
        System.out.println("    ");
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
}
