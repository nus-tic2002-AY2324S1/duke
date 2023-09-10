import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        int inputCount =0;
        String[] actions = new String[100];
        Scanner scanner = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\n"+
                "Hello! I'm venni\n" +
                "What can I do for you?\n"
        );

        String input = scanner.nextLine();
        // TO ADD: in case of input is null or space
        while(!input.trim().equalsIgnoreCase("bye")){
            if (!input.trim().equals("list")){
                System.out.println(
                        "    ____________________________________________________________\n" +
                                "     added: " + input + "\n" +
                                "    ____________________________________________________________\n" );
                actions[inputCount]= input;
                inputCount++;
            }else{
                System.out.println("    ____________________________________________________________");
                for (int i = 1; i <= inputCount; i++){
                    System.out.println("    "+ i +". "+actions[i-1] );
                }
                System.out.println("    ____________________________________________________________");
            }
            input = scanner.nextLine();
        }


        System.out.println("Bye. Hope to see you again soon!\n" +
                            "____________________________________________________________");

    }
}
