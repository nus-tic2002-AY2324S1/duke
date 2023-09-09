import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String botName = "Angel";
        System.out.println("Hello! I'm " + botName);
        System.out.println("What can I do for you?");
        
        Scanner in = new Scanner(System.in);
        boolean typing = true;

        while(typing){
            String line = in.nextLine();

            if(line.equals("bye")){
                System.out.println("Bye! Hope I'll get to see you soon! :)");
                typing = false;
            }
            else{
                System.out.println("You said: " + line);
            }

        }

    }
}
