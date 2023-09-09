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
        String[] taskList = new String[100];
        int items = 0;

        while(typing){
            String line = in.nextLine();

            if(line.equals("bye")){
                System.out.println("Bye! Hope I'll get to see you soon! :)");
                typing = false;
            }
            else if(line.equals("list")){
                System.out.println("Here's what you've added to your task list so far.");
                for (int i=1; i<=items; i++){
                    System.out.println(i + ". " + taskList[i-1]);
                }
            }
            else{
                taskList[items] = line;
                items++;
                System.out.println(line + " has been added to your list!");
            }

        }

    }
}
