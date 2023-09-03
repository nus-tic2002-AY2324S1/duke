import java.util.Scanner;
public class Duke {
    private static String[] list = new String[100];
    private static int listCount =0;

    public static void addMessage(String msg){
        list[listCount] = msg;
        listCount++;
        System.out.println("added: " + msg);
    }
    public static void printList(){
        for (int i=0; i<listCount; i++){
            System.out.println( i+1 +". " + list[i]);
        }
    }

    public static void main(String[] args) {
        //String logo = " ____        _        \n"
        //        + "|  _ \\ _   _| | _____ \n"
        //        + "| | | | | | | |/ / _ \\\n"
        //        + "| |_| | |_| |   <  __/\n"
        //       + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);
        String line;
        Scanner in = new Scanner(System.in);

        System.out.println("Hello I'm Eve!\nWhat can I do for you?");

        for (;;){
            line = in.nextLine();
            if (line.equals("bye")) {
                System.out.println("Bye! Hope to see you again soon!");
                break;
            } else if (line.equals("list")) {
                printList();
            } else {
                addMessage(line);
            }
        }

    }
}
