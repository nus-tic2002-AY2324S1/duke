import java.time.LocalDateTime;
import java.util.Date;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n\n" + logo);

        System.out.println("Hello! I am Duke , your chatbot, set a name for me!!");
        String name;
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter a name for your chatbot (enter to set to default 'Duke'): ");
        name = input.nextLine();
        if(name.isBlank()){
            name = "Duke";
        }
        System.out.println("----".repeat(10));
        String greeting = getGreeting();
        System.out.println(greeting+"! I'am "+name + ", Your personal chatbot. ");
        System.out.println("What can I do for you today?");
        System.out.println("----".repeat(10));

        while(true){
            System.out.print("Please input your instruction: \n");
            String instruction = input.nextLine();
            printService(instruction);
            if(instruction.equals("bye")){
                break;
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

    public static void printService(String message){
        if(message.equals("bye")){
            message = "Bye. Hope to see you again soon!";
        }
        System.out.println("----".repeat(10));
        System.out.println(message);
        System.out.println("----".repeat(10));
    }
}
