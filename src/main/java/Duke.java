import java.util.Arrays;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = "__________              .__    .__ \n"
                + "\\______   \\__ __   ____ |  |__ |__|\n"
                + " |     ___/  |  \\_/ ___\\|  |  \\|  |\n"
                + " |    |   |  |  /\\  \\___|   Y  \\  |\n"
                + " |____|   |____/  \\___  >___|  /__|\n"
                + "                      \\/     \\/    \n";
        System.out.println("Hello! I'm \n" + logo);
        System.out.println("What can I do for you?");

        String echo = "";
        String[] list = new String[100];
        Scanner typed = new Scanner(System.in);
        String[] instructions;
        int count = 0;
        int store = 0; //counter for updates array
        String from = "";
        String to = "";
        String[] by = new String[100];
        String[] event = new String[100];
        String[] event2 = new String[100];

        while(!echo.matches("bye(.*)")){
            echo = typed.nextLine();
            if(!echo.equals("bye") && !echo.equals("list")){
                if(echo.contains("/from") && echo.contains("/to")){
                    event = echo.split("/from");
                    event2 = event[1].split("/to");
                    from = event2[0];
                    to = event2[1];
                    list[count] = "[E]" + "[ ] " + event[0] + "(from:" + from + " to:" + to + ")";
                    //list[i-1][4] change for mark&unmark [X] [ ]
                    //i is counter for printing 1. 2. 3.
                    System.out.println("Got it. I've added this task:");
                    System.out.println(list[count]);
                    count++;
                    System.out.println("Now you have " + count + " tasks in the list.");
                }
                else if(echo.contains("/by")){
                    by = echo.split("/by");
                    list[count] = "[D][ ] " + by[0] + "(by:" + by[1] + ")";
                    System.out.println("Got it. I've added this task:");
                    System.out.println(list[count]);
                    count++;
                    System.out.println("Now you have " + count + " tasks in the list.");
                }
                else if(!echo.matches("mark(.*)") && !echo.matches("unmark(.*)")){
                    list[count] = "[T][ ] " + echo;
                    System.out.println("Got it. I've added this task:");
                    System.out.println(list[count]);
                    count++;
                    System.out.println("Now you have " + count + " tasks in the list.");
                }
                else if(echo.matches("mark(.*)") && echo.contains(" ") && list[0] != null){
                    instructions = echo.split(" ");
                    store = Integer.parseInt(instructions[1]); //position number start from 1
                    if(list.length >= store){
                        list[store-1] = list[store-1].replace("[ ]", "[X]");
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(list[store-1]);
                    }
                }
                else if(echo.matches("unmark(.*)") && echo.contains(" ") && list[0] != null){
                    instructions = echo.split(" ");
                    store = Integer.parseInt(instructions[1]); //position number start from 1
                    if(list.length >= store){
                        list[store-1] = list[store-1].replace("[X]", "[ ]");
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(list[store-1]);
                    }
                }

            }else if(echo.matches("list")){
                for(int i = 1; i<= list.length; i++){
                    if(list[i-1]!=null) {
                        System.out.println(i + "." + list[i - 1]);
                    }else{
                        break;
                    }
                }
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
