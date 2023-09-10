import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n\n" + logo);

        printMessage("Hello! I am Duke , your chatbot, set a name for me!!");
        String name;
        Scanner input = new Scanner(System.in);
        printMessage("Please enter a name for your chatbot (enter to set to default 'Duke'): ");
        name = input.nextLine();
        if(name.isBlank()){
            name = "Duke";
        }
        printLine();
        String greeting = getGreeting();
        printMessage(greeting+"! I'am "+name + ", Your personal chatbot. ");
        printMessage("What can I do for you today?");
        printLine();

        List<String> taskList = new ArrayList<>();
        while(true){
            printMessage("Please input command: ");
            String command = input.nextLine();
            String[] task = command.split(" ");
            if(command.equalsIgnoreCase("bye")){
                printLine();
                printMessage("Bye. Hope to see you again soon!");
                printLine();
                break;
            }else if (command.equalsIgnoreCase("list")){
                if (!taskList.isEmpty()){
                    int idx = 1;
                    printLine();
                    printMessage("Here are the tasks in your list:");
                    for (String item:taskList) {
                        printMessage(idx+". "+item);
                        idx++;
                    }
                    printLine();
                }else{
                    printLine();
                    printMessage(command);
                    printLine();
                }
            }else if(task.length>1){
                taskList.add("[ ] "+command);
                printLine();
                System.out.println("added: "+command);
                printLine();
            }else {
                printLine();
                printMessage(command);
                printLine();
            }
        }

    }

    public static String getGreeting(){
        LocalDateTime currentTime = LocalDateTime.now();
        int hour = currentTime.getHour();
        if(hour < 12){
            return "Good morning";
        }else if(hour < 18){
            return "Good afternoon";
        }else {
            return "Good evening";
        }
    }

    public static void printLine(){
        System.out.println("----".repeat(10));
    }

    public static void printMessage(String message){
        System.out.println(message);
    }
}
