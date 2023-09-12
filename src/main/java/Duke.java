import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String echo = new String("test");

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String greeting = "____________________________________________________________\n" +
                " Hello! I'm CLARA \n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";

        String bye = "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";

        System.out.println(greeting);

        ArrayList<String> shelf = new ArrayList<String>();
        while(true){
            echo = in.nextLine();
            if(echo.equals("bye")){
                System.out.println("Goodbye");
                break;
            }else if(echo.equals("list")){
                for(int i = 0; i < shelf.size(); i++){
                    System.out.println(
                            "____________________________________________________________\n" +
                                    Integer.toString(i+1)+ ": " + shelf.get(i) + "\n" +
                                    "____________________________________________________________\n"
                    );
                }
            } else {
                addItems(shelf, echo);
                System.out.println(
                        "____________________________________________________________\n" +
                                "added: " + echo + "\n" +
                                "____________________________________________________________\n"
                );
            }
        }
    }

    public static void addItems(ArrayList<String> shelf, String item){
    shelf.add(item);
    }

}